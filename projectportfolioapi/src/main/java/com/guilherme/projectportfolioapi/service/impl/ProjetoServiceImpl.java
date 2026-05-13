package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
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

    @Override
    public ProjetoResponseDTO criarProjeto(ProjetoRequestDTO dto) {
        return null;
    }

    @Override
    public List<ProjetoResponseDTO> listarProjetos() {
        return List.of();
    }

    @Override
    public ProjetoResponseDTO buscarPorId(UUID id) {
        return null;
    }
}