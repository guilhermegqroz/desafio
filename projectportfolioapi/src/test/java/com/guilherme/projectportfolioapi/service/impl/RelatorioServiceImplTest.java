package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.dto.response.PortfolioResumoDTO;
import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.entity.ProjetoMembro;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import com.guilherme.projectportfolioapi.repository.ProjetoMembroRepository;
import com.guilherme.projectportfolioapi.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RelatorioServiceImplTest {

        @InjectMocks
        private RelatorioServiceImpl service;

        @Mock
        private ProjetoRepository projetoRepository;

        @Mock
        private ProjetoMembroRepository projetoMembroRepository;

        private Projeto projeto1;

        private Projeto projeto2;

        @BeforeEach
        void setup() {

                projeto1 = Projeto.builder()
                                .id(1L)
                                .nome("Projeto 1")
                                .status(StatusProjeto.ENCERRADO)
                                .orcamentoTotal(new BigDecimal("1000"))
                                .dataInicio(LocalDate.of(2024, 1, 1))
                                .dataRealTermino(LocalDate.of(2024, 1, 11))
                                .build();

                projeto2 = Projeto.builder()
                                .id(2L)
                                .nome("Projeto 2")
                                .status(StatusProjeto.EM_ANALISE)
                                .orcamentoTotal(new BigDecimal("2000"))
                                .dataInicio(LocalDate.of(2024, 2, 1))
                                .dataRealTermino(LocalDate.of(2024, 2, 20))
                                .build();
        }

        @Test
        void deveGerarResumoPortfolio() {

                ProjetoMembro membro1 = ProjetoMembro.builder()
                                .membroId(1L)
                                .build();

                ProjetoMembro membro2 = ProjetoMembro.builder()
                                .membroId(2L)
                                .build();

                ProjetoMembro membroDuplicado = ProjetoMembro.builder()
                                .membroId(1L)
                                .build();

                when(projetoRepository.findAll())
                                .thenReturn(List.of(projeto1, projeto2));

                when(projetoMembroRepository.findAll())
                                .thenReturn(List.of(
                                                membro1,
                                                membro2,
                                                membroDuplicado));

                PortfolioResumoDTO response = service.gerarResumoPortfolio();

                assertEquals(
                                1L,
                                response.getQuantidadeProjetosPorStatus()
                                                .get(StatusProjeto.ENCERRADO));

                assertEquals(
                                1L,
                                response.getQuantidadeProjetosPorStatus()
                                                .get(StatusProjeto.EM_ANALISE));

                assertEquals(
                                new BigDecimal("1000"),
                                response.getTotalOrcadoPorStatus()
                                                .get(StatusProjeto.ENCERRADO));

                assertEquals(
                                new BigDecimal("2000"),
                                response.getTotalOrcadoPorStatus()
                                                .get(StatusProjeto.EM_ANALISE));

                assertEquals(
                                10.0,
                                response.getMediaDuracaoProjetosEncerrados());

                assertEquals(
                                2L,
                                response.getTotalMembrosUnicosAlocados());
        }

        @Test
        void deveRetornarMediaZeroQuandoNaoExistirProjetoEncerrado() {

                projeto1.setStatus(StatusProjeto.EM_ANALISE);
                projeto2.setStatus(StatusProjeto.EM_ANDAMENTO);

                when(projetoRepository.findAll())
                                .thenReturn(List.of(projeto1, projeto2));

                when(projetoMembroRepository.findAll())
                                .thenReturn(List.of());

                PortfolioResumoDTO response = service.gerarResumoPortfolio();

                assertEquals(
                                0.0,
                                response.getMediaDuracaoProjetosEncerrados());
        }
}