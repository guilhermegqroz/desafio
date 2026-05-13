package com.guilherme.projectportfolioapi.controller;

import com.guilherme.projectportfolioapi.dto.request.MembroRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import com.guilherme.projectportfolioapi.service.MembroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/membros")
@RequiredArgsConstructor
public class MembroController {

    private final MembroService membroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MembroResponseDTO criarMembro(@RequestBody MembroRequestDTO dto) {
        return membroService.criarMembro(dto);
    }

    @GetMapping
    public List<MembroResponseDTO> listarMembros() {
        return membroService.listarMembros();
    }

    @GetMapping("/{id}")
    public MembroResponseDTO buscarMembroPorId(@PathVariable Long id) {
        return membroService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public MembroResponseDTO atualizarMembro(
            @PathVariable Long id,
            @RequestBody MembroRequestDTO dto
    ) {
        return membroService.atualizarMembro(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMembro(@PathVariable Long id) {
        membroService.deletarMembro(id);
    }
}