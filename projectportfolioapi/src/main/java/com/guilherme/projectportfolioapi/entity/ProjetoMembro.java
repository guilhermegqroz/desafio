package com.guilherme.projectportfolioapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projeto_membro")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoMembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    private String membroId;
}