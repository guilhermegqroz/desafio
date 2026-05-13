package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.entity.Membro;
import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.mapper.ProjetoMapper;
import com.guilherme.projectportfolioapi.repository.MembroRepository;
import com.guilherme.projectportfolioapi.repository.ProjetoRepository;
import com.guilherme.projectportfolioapi.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final MembroRepository membroRepository;
    private final ProjetoMapper projetoMapper;

    @Override
    public ProjetoResponseDTO criarProjeto(ProjetoRequestDTO dto) {
        Membro gerente = membroRepository.findById(dto.getGerenteId())
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado"));
        Projeto projeto = projetoMapper.toEntity(dto, gerente);
        Projeto projetoSalvo = projetoRepository.save(projeto);
        return projetoMapper.toDTO(projetoSalvo);
    }

    @Override
    public List<ProjetoResponseDTO> listarProjetos() {
        return projetoRepository.findAll()
                .stream()
                .map(projetoMapper::toDTO)
                .toList();
    }

    @Override
    public ProjetoResponseDTO buscarPorId(UUID id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        return projetoMapper.toDTO(projeto);
    }
}