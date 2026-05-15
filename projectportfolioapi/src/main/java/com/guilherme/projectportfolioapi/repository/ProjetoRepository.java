package com.guilherme.projectportfolioapi.repository;

import com.guilherme.projectportfolioapi.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}