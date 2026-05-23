package com.guilherme.projectportfolioapi.repository;

import com.guilherme.projectportfolioapi.entity.ProjetoMembro;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjetoMembroRepository extends JpaRepository<ProjetoMembro, Long> {

    long countByProjetoId(Long projetoId);

    @Query("""
            SELECT COUNT(pm)
            FROM ProjetoMembro pm
            JOIN pm.projeto p
            WHERE pm.membroId = :membroId
            AND p.status NOT IN :status
            """)
    long countProjetosAtivosDoMembro(Long membroId, List<StatusProjeto> status);
}