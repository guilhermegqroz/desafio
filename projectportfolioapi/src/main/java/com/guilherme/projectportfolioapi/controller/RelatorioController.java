package com.guilherme.projectportfolioapi.controller;

import com.guilherme.projectportfolioapi.dto.response.PortfolioResumoDTO;
import com.guilherme.projectportfolioapi.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/resumo")
    public PortfolioResumoDTO gerarResumoPortfolio() {
        return relatorioService.gerarResumoPortfolio();
    }
}