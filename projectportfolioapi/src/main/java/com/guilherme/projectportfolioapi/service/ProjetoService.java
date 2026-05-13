package com.guilherme.projectportfolioapi.service;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import java.util.List;
import java.util.UUID;

public interface ProjetoService {

    ProjetoResponseDTO criarProjeto(ProjetoRequestDTO dto);
    List<ProjetoResponseDTO> listarProjetos();
    ProjetoResponseDTO buscarPorId(UUID id);

}