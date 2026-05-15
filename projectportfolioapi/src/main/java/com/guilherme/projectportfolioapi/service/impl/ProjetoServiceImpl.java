package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.entity.Membro;
import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.enums.ClassificacaoRisco;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import com.guilherme.projectportfolioapi.mapper.ProjetoMapper;
import com.guilherme.projectportfolioapi.repository.MembroRepository;
import com.guilherme.projectportfolioapi.repository.ProjetoRepository;
import com.guilherme.projectportfolioapi.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.guilherme.projectportfolioapi.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final MembroRepository membroRepository;
    private final ProjetoMapper projetoMapper;

    @Override
    public ProjetoResponseDTO criar(ProjetoRequestDTO dto) {

        Membro gerente = membroRepository.findById(dto.getGerenteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Gerente não encontrado"
                ));

        Projeto projeto = Projeto.builder()
                .nome(dto.getNome())
                .dataInicio(dto.getDataInicio())
                .previsaoTermino(dto.getPrevisaoTermino())
                .dataRealTermino(dto.getDataRealTermino())
                .orcamentoTotal(dto.getOrcamentoTotal())
                .descricao(dto.getDescricao())
                .gerente(gerente)
                .status(StatusProjeto.EM_ANALISE)
                .build();

        Projeto projetoSalvo = projetoRepository.save(projeto);

        return projetoMapper.toDTO(
                projetoSalvo,
                calcularRisco(projetoSalvo)
        );
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

        return projetoMapper.toDTO(
                projeto,
                calcularRisco(projeto)
        );
    }

    @Override
    public ProjetoResponseDTO atualizar(Long id, ProjetoRequestDTO dto) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projeto não encontrado"
                ));

        Membro gerente = membroRepository.findById(dto.getGerenteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Gerente não encontrado"
                ));

        projeto.setNome(dto.getNome());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setPrevisaoTermino(dto.getPrevisaoTermino());
        projeto.setDataRealTermino(dto.getDataRealTermino());
        projeto.setOrcamentoTotal(dto.getOrcamentoTotal());
        projeto.setDescricao(dto.getDescricao());
        projeto.setGerente(gerente);

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

            throw new ResourceNotFoundException(
                    "Não é permitido excluir projetos nesse status"
            );
        }

        projetoRepository.delete(projeto);
    }

    private ClassificacaoRisco calcularRisco(Projeto projeto) {

        if (projeto.getOrcamentoTotal().compareTo(new BigDecimal("100000")) > 0) {
            return ClassificacaoRisco.ALTO;
        }

        return ClassificacaoRisco.BAIXO;
    }
}