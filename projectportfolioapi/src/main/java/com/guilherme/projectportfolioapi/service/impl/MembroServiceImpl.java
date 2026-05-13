package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.dto.request.MembroRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import com.guilherme.projectportfolioapi.repository.MembroRepository;
import com.guilherme.projectportfolioapi.service.MembroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MembroServiceImpl implements MembroService {

    private final MembroRepository membroRepository;

    @Override
    public MembroResponseDTO criarMembro(MembroRequestDTO dto) {
        return null;
    }

    @Override
    public List<MembroResponseDTO> listarMembros() {
        return List.of();
    }

    @Override
    public MembroResponseDTO buscarPorId(UUID id) {
        return null;
    }
}