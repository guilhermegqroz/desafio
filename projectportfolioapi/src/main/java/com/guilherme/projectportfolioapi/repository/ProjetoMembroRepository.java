package com.guilherme.projectportfolioapi.repository;

import com.guilherme.projectportfolioapi.entity.ProjetoMembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjetoMembroRepository
        extends JpaRepository<ProjetoMembro, Long> {

    long countByProjetoId(Long projetoId);

    @Query(
            "SELECT COUNT(pm) " +
                    "FROM ProjetoMembro pm " +
                    "JOIN pm.projeto p " +
                    "WHERE pm.membroId = :membroId " +
                    "AND p.status NOT IN (" +
                    "com.guilherme.projectportfolioapi.enums.StatusProjeto.ENCERRADO, " +
                    "com.guilherme.projectportfolioapi.enums.StatusProjeto.CANCELADO)"
    )
    long countProjetosAtivosDoMembro(
            String membroId
    );
}