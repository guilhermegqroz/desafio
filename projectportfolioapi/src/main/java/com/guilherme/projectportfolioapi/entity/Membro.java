package com.guilherme.projectportfolioapi.entity;

import com.guilherme.projectportfolioapi.enums.Atribuicao;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "membros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Atribuicao atribuicao;

}