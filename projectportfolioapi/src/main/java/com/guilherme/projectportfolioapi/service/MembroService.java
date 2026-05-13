package com.guilherme.projectportfolioapi.service;

import com.guilherme.projectportfolioapi.dto.request.MembroRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import java.util.List;
import java.util.UUID;

public interface MembroService {
    MembroResponseDTO criarMembro(MembroRequestDTO dto);
    List<MembroResponseDTO> listarMembros();
    MembroResponseDTO buscarPorId(Long id);
    MembroResponseDTO atualizarMembro(Long id, MembroRequestDTO dto);
    void deletarMembro(Long id);
}