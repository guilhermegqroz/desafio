package com.guilherme.projectportfolioapi.repository;

import com.guilherme.projectportfolioapi.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {
}