package com.guilherme.projectportfolioapi.repository;

import com.guilherme.projectportfolioapi.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MembroRepository extends JpaRepository<Membro, Long> {
}