package com.guilherme.projectportfolioapi.repository;

import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    Page<Projeto> findByNomeContainingIgnoreCase(
            String nome,
            Pageable pageable
    );

    Page<Projeto> findByStatus(
            StatusProjeto status,
            Pageable pageable
    );
}