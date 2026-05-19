package com.guilherme.projectportfolioapi.service;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;

import java.util.List;

public interface ProjetoService {

    ProjetoResponseDTO criar(ProjetoRequestDTO dto);

    List<ProjetoResponseDTO> listar();

    ProjetoResponseDTO buscarPorId(Long id);

    ProjetoResponseDTO atualizar(Long id, ProjetoRequestDTO dto);

    void deletar(Long id);
    
    ProjetoResponseDTO atualizarStatus(Long id, String status);
}