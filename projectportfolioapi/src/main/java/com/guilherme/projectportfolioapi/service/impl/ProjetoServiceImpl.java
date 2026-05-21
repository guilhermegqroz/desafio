package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.client.MembroClientService;
import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.entity.ProjetoMembro;
import com.guilherme.projectportfolioapi.enums.ClassificacaoRisco;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import com.guilherme.projectportfolioapi.exception.NegocioException;
import com.guilherme.projectportfolioapi.exception.ResourceNotFoundException;
import com.guilherme.projectportfolioapi.mapper.ProjetoMapper;
import com.guilherme.projectportfolioapi.repository.ProjetoMembroRepository;
import com.guilherme.projectportfolioapi.repository.ProjetoRepository;
import com.guilherme.projectportfolioapi.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private static final BigDecimal LIMITE_BAIXO = new BigDecimal("100000");
 
    private static final BigDecimal LIMITE_MEDIO = new BigDecimal("500000");

    private final ProjetoRepository projetoRepository;
    private final ProjetoMapper projetoMapper;
    private final ProjetoMembroRepository projetoMembroRepository;
    private final MembroClientService membroClientService;

    @Override
    public ProjetoResponseDTO criar(ProjetoRequestDTO dto) {

        Projeto projeto = Projeto.builder()
                .nome(dto.getNome())
                .dataInicio(dto.getDataInicio())
                .previsaoTermino(dto.getPrevisaoTermino())
                .dataRealTermino(dto.getDataRealTermino())
                .orcamentoTotal(dto.getOrcamentoTotal())
                .descricao(dto.getDescricao())
                .gerenteId(dto.getGerenteId())
                .status(StatusProjeto.EM_ANALISE)
                .build();

        Projeto projetoSalvo =
                projetoRepository.save(projeto);

        return toDTO(projetoSalvo);
    }

    @Override
    public Page<ProjetoResponseDTO> listar(String nome, String status, Pageable pageable) {

        Page<Projeto> projetos;

        if (StringUtils.hasText(nome)) {

            projetos = projetoRepository
                    .findByNomeContainingIgnoreCase(
                            nome,
                            pageable
                    );

        } else if (StringUtils.hasText(status)) {

            StatusProjeto statusEnum =
                    StatusProjeto.valueOf(status.toUpperCase());

            projetos = projetoRepository
                    .findByStatus(statusEnum, pageable);

        } else {

            projetos = projetoRepository.findAll(pageable);
        }

        return projetos.map(this::toDTO);
    }

    @Override
    public ProjetoResponseDTO buscarPorId(Long id) {

        Projeto projeto = buscarProjeto(id);

        return toDTO(projeto);
    }

    @Override
    public ProjetoResponseDTO atualizar(Long id, ProjetoRequestDTO dto) {

        Projeto projeto = buscarProjeto(id);

        atualizarProjeto(projeto, dto);

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return toDTO(projetoAtualizado);
    }

    @Override
    public void deletar(Long id) {

        Projeto projeto = buscarProjeto(id);

        validarExclusaoProjeto(projeto);

        projetoRepository.delete(projeto);
    }

    @Override
    public ProjetoResponseDTO atualizarStatus(Long id, String status) {

        Projeto projeto = buscarProjeto(id);

        StatusProjeto novoStatus = StatusProjeto.valueOf(status.toUpperCase());

        validarTransicaoStatus(projeto, novoStatus);

        validarProjetoEncerradoComMembros(
                id,
                novoStatus
        );

        projeto.setStatus(novoStatus);

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return toDTO(projetoAtualizado);
    }

    @Override
    public void associarMembro(Long projetoId, String membroId) {

        Projeto projeto = buscarProjeto(projetoId);

        MembroResponseDTO membro = membroClientService.buscarMembro(membroId);

        validarFuncionario(membro);

        validarLimiteMembrosProjeto(projetoId);

        validarLimiteProjetosMembro(membroId);

        ProjetoMembro projetoMembro =
                ProjetoMembro.builder()
                        .projeto(projeto)
                        .membroId(membroId)
                        .build();

        projetoMembroRepository.save(projetoMembro);
    }

    private Projeto buscarProjeto(Long id) {

        return projetoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Projeto não encontrado."
                        )
                );
    }

    private ProjetoResponseDTO toDTO(Projeto projeto) {

        return projetoMapper.toDTO(
                projeto,
                calcularRisco(projeto)
        );
    }

    private void atualizarProjeto(Projeto projeto, ProjetoRequestDTO dto) {
        projeto.setNome(dto.getNome());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setPrevisaoTermino(dto.getPrevisaoTermino());
        projeto.setDataRealTermino(dto.getDataRealTermino());
        projeto.setOrcamentoTotal(dto.getOrcamentoTotal());
        projeto.setDescricao(dto.getDescricao());
        projeto.setGerenteId(dto.getGerenteId());
    }

    private void validarExclusaoProjeto(Projeto projeto) {

        if (projeto.getStatus() == StatusProjeto.INICIADO
                || projeto.getStatus() == StatusProjeto.EM_ANDAMENTO
                || projeto.getStatus() == StatusProjeto.ENCERRADO) {

            throw new NegocioException(
                    "Não é permitido excluir projetos nesse status."
            );
        }
    }

    private void validarTransicaoStatus(Projeto projeto, StatusProjeto novoStatus) {
        if (!projeto.getStatus()
                .podeTransicionarPara(novoStatus)) {

            throw new NegocioException(
                    "Transição de status inválida."
            );
        }
    }

    private void validarProjetoEncerradoComMembros(Long projetoId, StatusProjeto novoStatus) {

        if (novoStatus == StatusProjeto.ENCERRADO) {

            long totalMembros =
                    projetoMembroRepository
                            .countByProjetoId(projetoId);

            if (totalMembros < 1) {

                throw new NegocioException(
                        "Projeto deve possuir ao menos 1 membro."
                );
            }
        }
    }

    private void validarFuncionario(MembroResponseDTO membro) {

        if (!membro.getAtribuicao()
                .equalsIgnoreCase("FUNCIONARIO")) {

            throw new NegocioException(
                    "Apenas FUNCIONARIOS podem ser associados."
            );
        }
    }

    private void validarLimiteMembrosProjeto(Long projetoId) {

        long totalMembros =
                projetoMembroRepository
                        .countByProjetoId(projetoId);

        if (totalMembros >= 10) {

            throw new NegocioException(
                    "Projeto já possui 10 membros."
            );
        }
    }

    private void validarLimiteProjetosMembro(String membroId) {

        long projetosAtivos =
                projetoMembroRepository
                        .countProjetosAtivosDoMembro(
                                membroId,
                                List.of(
                                        StatusProjeto.ENCERRADO,
                                        StatusProjeto.CANCELADO
                                )
                        );

        if (projetosAtivos >= 3) {

            throw new NegocioException(
                    "Membro já participa de 3 projetos ativos."
            );
        }
    }

    private ClassificacaoRisco calcularRisco(Projeto projeto) {

        long meses = ChronoUnit.MONTHS.between(
                projeto.getDataInicio(),
                projeto.getPrevisaoTermino()
        );

        BigDecimal orcamento =
                projeto.getOrcamentoTotal();

        boolean baixoRisco =
                orcamento.compareTo(LIMITE_BAIXO) <= 0
                        && meses <= 3;

        boolean medioRisco =
                (orcamento.compareTo(LIMITE_BAIXO) > 0
                        && orcamento.compareTo(LIMITE_MEDIO) <= 0)
                        || (meses > 3 && meses <= 6);

        if (baixoRisco) {
            return ClassificacaoRisco.BAIXO;
        }

        if (medioRisco) {
            return ClassificacaoRisco.MEDIO;
        }

        return ClassificacaoRisco.ALTO;
    }
}