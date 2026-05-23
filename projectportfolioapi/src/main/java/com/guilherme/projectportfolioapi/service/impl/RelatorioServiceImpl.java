package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.dto.response.PortfolioResumoDTO;
import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import com.guilherme.projectportfolioapi.repository.ProjetoMembroRepository;
import com.guilherme.projectportfolioapi.repository.ProjetoRepository;
import com.guilherme.projectportfolioapi.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RelatorioServiceImpl implements RelatorioService {

        private final ProjetoRepository projetoRepository;

        private final ProjetoMembroRepository projetoMembroRepository;

        @Override
        public PortfolioResumoDTO gerarResumoPortfolio() {

                List<Projeto> projetos = projetoRepository.findAll();

                Map<StatusProjeto, Long> quantidadeProjetosPorStatus = new EnumMap<>(StatusProjeto.class);

                Map<StatusProjeto, BigDecimal> totalOrcadoPorStatus = new EnumMap<>(StatusProjeto.class);

                for (Projeto projeto : projetos) {

                        StatusProjeto status = projeto.getStatus();

                        quantidadeProjetosPorStatus.put(
                                        status,
                                        quantidadeProjetosPorStatus
                                                        .getOrDefault(status, 0L) + 1);

                        totalOrcadoPorStatus.put(
                                        status,
                                        totalOrcadoPorStatus
                                                        .getOrDefault(
                                                                        status,
                                                                        BigDecimal.ZERO)
                                                        .add(projeto.getOrcamentoTotal()));
                }

                List<Projeto> projetosEncerrados = projetos.stream()
                                .filter(projeto -> projeto.getStatus() == StatusProjeto.ENCERRADO)
                                .toList();

                double mediaDuracaoProjetosEncerrados = projetosEncerrados.stream()
                                .mapToLong(projeto -> ChronoUnit.DAYS.between(
                                                projeto.getDataInicio(),
                                                projeto.getDataRealTermino()))
                                .average()
                                .orElse(0.0);

                long totalMembrosUnicosAlocados = projetoMembroRepository
                                .findAll()
                                .stream()
                                .map(projetoMembro -> projetoMembro.getMembroId())
                                .distinct()
                                .count();

                return PortfolioResumoDTO.builder()
                                .quantidadeProjetosPorStatus(
                                                quantidadeProjetosPorStatus)
                                .totalOrcadoPorStatus(
                                                totalOrcadoPorStatus)
                                .mediaDuracaoProjetosEncerrados(
                                                mediaDuracaoProjetosEncerrados)
                                .totalMembrosUnicosAlocados(
                                                totalMembrosUnicosAlocados)
                                .build();
        }

}
