package com.guilherme.projectportfolioapi.controller;
import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

	private final ProjetoService projetoService;

	@PostMapping
	public ResponseEntity<ProjetoResponseDTO> criar(@RequestBody ProjetoRequestDTO dto) {
		return ResponseEntity.ok(projetoService.criar(dto));
	}
	@GetMapping
	public ResponseEntity<Page<ProjetoResponseDTO>> listar(
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String status,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy
	) {
			Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
			return ResponseEntity.ok(projetoService.listar(nome, status, pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjetoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(projetoService.buscarPorId(id));
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

	@PatchMapping("/{id}/status")
	public ResponseEntity<ProjetoResponseDTO> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
		return ResponseEntity.ok(projetoService.atualizarStatus(id, status));
	}
	
	@PostMapping("/{projetoId}/membros/{membroId}")
	public ResponseEntity<Void> associarMembro(@PathVariable Long projetoId, @PathVariable String membroId) {
		projetoService.associarMembro(projetoId, membroId);
		return ResponseEntity.ok().build();
	}
}