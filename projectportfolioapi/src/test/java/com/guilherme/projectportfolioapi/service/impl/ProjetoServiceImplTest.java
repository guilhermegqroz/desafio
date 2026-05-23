package com.guilherme.projectportfolioapi.service.impl;

import com.guilherme.projectportfolioapi.client.MembroClientService;
import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import com.guilherme.projectportfolioapi.exception.NegocioException;
import com.guilherme.projectportfolioapi.exception.ResourceNotFoundException;
import com.guilherme.projectportfolioapi.mapper.ProjetoMapper;
import com.guilherme.projectportfolioapi.repository.ProjetoMembroRepository;
import com.guilherme.projectportfolioapi.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetoServiceImplTest {

        @InjectMocks
        private ProjetoServiceImpl service;

        @Mock
        private ProjetoRepository projetoRepository;

        @Mock
        private ProjetoMapper projetoMapper;

        @Mock
        private ProjetoMembroRepository projetoMembroRepository;

        @Mock
        private MembroClientService membroClientService;

        private Projeto projeto;

        private ProjetoRequestDTO requestDTO;

        private ProjetoResponseDTO responseDTO;

        @BeforeEach
        void setup() {

                projeto = Projeto.builder()
                                .id(1L)
                                .nome("Projeto Teste")
                                .descricao("Descricao")
                                .orcamentoTotal(new BigDecimal("1000"))
                                .dataInicio(LocalDate.now())
                                .previsaoTermino(LocalDate.now().plusMonths(2))
                                .status(StatusProjeto.EM_ANALISE)
                                .gerenteId(1L)
                                .build();

                requestDTO = new ProjetoRequestDTO();

                requestDTO.setNome("Projeto Teste");
                requestDTO.setDescricao("Descricao");
                requestDTO.setOrcamentoTotal(new BigDecimal("1000"));
                requestDTO.setDataInicio(LocalDate.now());
                requestDTO.setPrevisaoTermino(LocalDate.now().plusMonths(2));
                requestDTO.setGerenteId(1L);

                responseDTO = new ProjetoResponseDTO();
        }

        @Test
        void deveCriarProjetoComSucesso() {

                when(projetoRepository.save(any(Projeto.class)))
                                .thenReturn(projeto);

                when(projetoMapper.toDTO(any(), any()))
                                .thenReturn(responseDTO);

                ProjetoResponseDTO response = service.criar(requestDTO);

                assertNotNull(response);

                verify(projetoRepository).save(any(Projeto.class));
        }

        @Test
        void deveListarProjetos() {

                when(projetoRepository.findAll(any(PageRequest.class)))
                                .thenReturn(new PageImpl<>(List.of(projeto)));

                when(projetoMapper.toDTO(any(), any()))
                                .thenReturn(responseDTO);

                var response = service.listar(
                                null,
                                null,
                                PageRequest.of(0, 10));

                assertEquals(1, response.getTotalElements());
        }

        @Test
        void deveListarProjetosPorNome() {

                when(projetoRepository.findByNomeContainingIgnoreCase(
                                eq("Projeto"),
                                any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(projeto)));

                when(projetoMapper.toDTO(any(), any()))
                                .thenReturn(responseDTO);

                var response = service.listar(
                                "Projeto",
                                null,
                                PageRequest.of(0, 10));

                assertEquals(1, response.getTotalElements());
        }

        @Test
        void deveListarProjetosPorStatus() {

                when(projetoRepository.findByStatus(
                                eq(StatusProjeto.EM_ANALISE),
                                any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(projeto)));

                when(projetoMapper.toDTO(any(), any()))
                                .thenReturn(responseDTO);

                var response = service.listar(
                                null,
                                "EM_ANALISE",
                                PageRequest.of(0, 10));

                assertEquals(1, response.getTotalElements());
        }

        @Test
        void deveBuscarProjetoPorId() {

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                when(projetoMapper.toDTO(any(), any()))
                                .thenReturn(responseDTO);

                ProjetoResponseDTO response = service.buscarPorId(1L);

                assertNotNull(response);

                verify(projetoRepository).findById(1L);
        }

        @Test
        void deveLancarExcecaoQuandoProjetoNaoEncontrado() {

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.empty());

                assertThrows(
                                ResourceNotFoundException.class,
                                () -> service.buscarPorId(1L));
        }

        @Test
        void deveAtualizarProjeto() {

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                when(projetoRepository.save(any()))
                                .thenReturn(projeto);

                when(projetoMapper.toDTO(any(), any()))
                                .thenReturn(responseDTO);

                ProjetoResponseDTO response = service.atualizar(1L, requestDTO);

                assertNotNull(response);

                verify(projetoRepository).save(any());
        }

        @Test
        void deveDeletarProjeto() {

                projeto.setStatus(StatusProjeto.EM_ANALISE);

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                service.deletar(1L);

                verify(projetoRepository).delete(projeto);
        }

        @Test
        void naoDeveDeletarProjetoIniciado() {

                projeto.setStatus(StatusProjeto.INICIADO);

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                assertThrows(
                                NegocioException.class,
                                () -> service.deletar(1L));
        }

        @Test
        void naoDeveDeletarProjetoEmAndamento() {

                projeto.setStatus(StatusProjeto.EM_ANDAMENTO);

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                assertThrows(
                                NegocioException.class,
                                () -> service.deletar(1L));
        }

        @Test
        void naoDeveDeletarProjetoEncerrado() {

                projeto.setStatus(StatusProjeto.ENCERRADO);

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                assertThrows(
                                NegocioException.class,
                                () -> service.deletar(1L));
        }

        @Test
        void deveAtualizarStatus() {

                projeto.setStatus(StatusProjeto.EM_ANALISE);

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                when(projetoRepository.save(any()))
                                .thenReturn(projeto);

                when(projetoMapper.toDTO(any(), any()))
                                .thenReturn(responseDTO);

                ProjetoResponseDTO response = service.atualizarStatus(1L, "ANALISE_REALIZADA");

                assertNotNull(response);

                verify(projetoRepository).save(any());
        }

        @Test
        void naoDeveAtualizarStatusInvalido() {

                projeto.setStatus(StatusProjeto.ENCERRADO);

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                assertThrows(
                                NegocioException.class,
                                () -> service.atualizarStatus(1L, "EM_ANALISE"));
        }

        @Test
        void naoDeveEncerrarProjetoSemMembros() {

                projeto.setStatus(StatusProjeto.EM_ANDAMENTO);

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                when(projetoMembroRepository.countByProjetoId(1L))
                                .thenReturn(0L);

                assertThrows(
                                NegocioException.class,
                                () -> service.atualizarStatus(1L, "ENCERRADO"));
        }

        @Test
        void deveAssociarMembro() {

                MembroResponseDTO membro = new MembroResponseDTO();

                membro.setAtribuicao("FUNCIONARIO");

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                when(membroClientService.buscarMembro(1L))
                                .thenReturn(membro);

                when(projetoMembroRepository.countByProjetoId(1L))
                                .thenReturn(1L);

                when(projetoMembroRepository.countProjetosAtivosDoMembro(
                                eq(1L),
                                anyList())).thenReturn(1L);

                service.associarMembro(1L, 1L);

                verify(projetoMembroRepository).save(any());
        }

        @Test
        void naoDeveAssociarMembroNaoFuncionario() {

                MembroResponseDTO membro = new MembroResponseDTO();

                membro.setAtribuicao("TERCEIRO");

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                when(membroClientService.buscarMembro(1L))
                                .thenReturn(membro);

                assertThrows(
                                NegocioException.class,
                                () -> service.associarMembro(1L, 1L));
        }

        @Test
        void naoDeveAssociarMaisDe10Membros() {

                MembroResponseDTO membro = new MembroResponseDTO();

                membro.setAtribuicao("FUNCIONARIO");

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                when(membroClientService.buscarMembro(1L))
                                .thenReturn(membro);

                when(projetoMembroRepository.countByProjetoId(1L))
                                .thenReturn(10L);

                assertThrows(
                                NegocioException.class,
                                () -> service.associarMembro(1L, 1L));
        }

        @Test
        void naoDeveAssociarMembroEmMaisDe3Projetos() {

                MembroResponseDTO membro = new MembroResponseDTO();

                membro.setAtribuicao("FUNCIONARIO");

                when(projetoRepository.findById(1L))
                                .thenReturn(Optional.of(projeto));

                when(membroClientService.buscarMembro(1L))
                                .thenReturn(membro);

                when(projetoMembroRepository.countByProjetoId(1L))
                                .thenReturn(1L);

                when(projetoMembroRepository.countProjetosAtivosDoMembro(
                                eq(1L),
                                anyList())).thenReturn(3L);

                assertThrows(
                                NegocioException.class,
                                () -> service.associarMembro(1L, 1L));
        }
}