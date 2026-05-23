package com.guilherme.projectportfolioapi.service;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjetoService {

    ProjetoResponseDTO criar(ProjetoRequestDTO dto);

    Page<ProjetoResponseDTO> listar(String nome, String status, Pageable pageable);

    ProjetoResponseDTO buscarPorId(Long id);

    ProjetoResponseDTO atualizar(Long id, ProjetoRequestDTO dto);

    void deletar(Long id);
    
    ProjetoResponseDTO atualizarStatus(Long id, String status);

    void associarMembro(Long projetoId, Long membroId);
}