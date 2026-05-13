package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.dto.request.MembroRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import com.guilherme.projectportfolioapi.entity.Membro;
import com.guilherme.projectportfolioapi.mapper.MembroMapper;
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
    private final MembroMapper membroMapper;

    @Override
    public MembroResponseDTO criarMembro(MembroRequestDTO dto) {
        Membro membro = membroMapper.toEntity(dto);
        Membro membroSalvo = membroRepository.save(membro);
        return membroMapper.toDTO(membroSalvo);
    }

    @Override
    public List<MembroResponseDTO> listarMembros() {
        return membroRepository.findAll()
                .stream()
                .map(membroMapper::toDTO)
                .toList();
    }

    @Override
    public MembroResponseDTO buscarPorId(UUID id) {
        Membro membro = membroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado"));
        return membroMapper.toDTO(membro);
    }
}