package com.guilherme.projectportfolioapi.controller;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criar(@RequestBody ProjetoRequestDTO dto) {
        return ResponseEntity.ok(
                projetoService.criar(dto)
        );
    }

    @GetMapping
    public ResponseEntity<List<ProjetoResponseDTO>> listar() {
        return ResponseEntity.ok(projetoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                projetoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProjetoRequestDTO dto) {
        return ResponseEntity.ok(projetoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}