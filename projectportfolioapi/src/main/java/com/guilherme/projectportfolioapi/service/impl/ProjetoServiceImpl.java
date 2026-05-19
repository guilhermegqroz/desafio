package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.enums.ClassificacaoRisco;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import com.guilherme.projectportfolioapi.exception.ResourceNotFoundException;
import com.guilherme.projectportfolioapi.mapper.ProjetoMapper;
import com.guilherme.projectportfolioapi.repository.ProjetoRepository;
import com.guilherme.projectportfolioapi.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final ProjetoMapper projetoMapper;

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

        Projeto projetoSalvo = projetoRepository.save(projeto);

        return projetoMapper.toDTO(projetoSalvo, calcularRisco(projetoSalvo));
    }

    @Override
    public List<ProjetoResponseDTO> listar() {

        return projetoRepository.findAll()
                .stream()
                .map(projeto -> projetoMapper.toDTO(
                        projeto,
                        calcularRisco(projeto)
                ))
                .toList();
    }

    @Override
    public ProjetoResponseDTO buscarPorId(Long id) {

        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projeto não encontrado"
                ));

        return projetoMapper.toDTO(projeto, calcularRisco(projeto));
    }

    @Override
    public ProjetoResponseDTO atualizar(Long id, ProjetoRequestDTO dto) {

        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projeto não encontrado"
                ));

        projeto.setNome(dto.getNome());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setPrevisaoTermino(dto.getPrevisaoTermino());
        projeto.setDataRealTermino(dto.getDataRealTermino());
        projeto.setOrcamentoTotal(dto.getOrcamentoTotal());
        projeto.setDescricao(dto.getDescricao());
        projeto.setGerenteId(dto.getGerenteId());

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return projetoMapper.toDTO(
                projetoAtualizado,
                calcularRisco(projetoAtualizado)
        );
    }

    @Override
    public void deletar(Long id) {

        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projeto não encontrado"
                ));

        if (projeto.getStatus() == StatusProjeto.INICIADO
                || projeto.getStatus() == StatusProjeto.EM_ANDAMENTO
                || projeto.getStatus() == StatusProjeto.ENCERRADO) {

            throw new IllegalArgumentException(
                    "Não é permitido excluir projetos nesse status"
            );
        }

        projetoRepository.delete(projeto);
    }

    private ClassificacaoRisco calcularRisco(Projeto projeto) {

        long meses = ChronoUnit.MONTHS.between(
                projeto.getDataInicio(),
                projeto.getPrevisaoTermino()
        );

        BigDecimal orcamento = projeto.getOrcamentoTotal();

        if (orcamento.compareTo(new BigDecimal("100000")) <= 0
                && meses <= 3) {

            return ClassificacaoRisco.BAIXO;
        }

        if ((orcamento.compareTo(new BigDecimal("100000")) > 0
                && orcamento.compareTo(new BigDecimal("500000")) <= 0)
                || (meses > 3 && meses <= 6)) {

            return ClassificacaoRisco.MEDIO;
        }

        return ClassificacaoRisco.ALTO;
    }

    private void validarTransicaoStatus(
            StatusProjeto atual,
            StatusProjeto novo
    ) {

        if (novo == StatusProjeto.CANCELADO) {
            return;
        }

        if (atual == StatusProjeto.EM_ANALISE
                && novo != StatusProjeto.ANALISE_REALIZADA) {

            throw new IllegalArgumentException("Transição inválida");
        }

        if (atual == StatusProjeto.ANALISE_REALIZADA
                && novo != StatusProjeto.ANALISE_APROVADA) {

            throw new IllegalArgumentException("Transição inválida");
        }

        if (atual == StatusProjeto.ANALISE_APROVADA
                && novo != StatusProjeto.INICIADO) {

            throw new IllegalArgumentException("Transição inválida");
        }

        if (atual == StatusProjeto.INICIADO
                && novo != StatusProjeto.PLANEJADO) {

            throw new IllegalArgumentException("Transição inválida");
        }

        if (atual == StatusProjeto.PLANEJADO
                && novo != StatusProjeto.EM_ANDAMENTO) {

            throw new IllegalArgumentException("Transição inválida");
        }

        if (atual == StatusProjeto.EM_ANDAMENTO
                && novo != StatusProjeto.ENCERRADO) {

            throw new IllegalArgumentException("Transição inválida");
        }
    }

    @Override
    public ProjetoResponseDTO atualizarStatus(Long id, String status) {

        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Projeto não encontrado"
                        )
                );

        StatusProjeto novoStatus =
                StatusProjeto.valueOf(status.toUpperCase());

        validarTransicaoStatus(
                projeto.getStatus(),
                novoStatus
        );

        projeto.setStatus(novoStatus);

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return projetoMapper.toDTO(
                projetoAtualizado,
                calcularRisco(projetoAtualizado)
        );
    }
}