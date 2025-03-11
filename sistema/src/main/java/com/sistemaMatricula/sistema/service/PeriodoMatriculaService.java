package com.sistemaMatricula.sistema.service;

import com.sistemaMatricula.sistema.model.PeriodoMatricula;
import com.sistemaMatricula.sistema.repository.PeriodoMatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações relacionadas a períodos de matrícula
 */
@Service
public class PeriodoMatriculaService {
    
    @Autowired
    private PeriodoMatriculaRepository periodoMatriculaRepository;
    
    /**
     * Busca um período de matrícula pelo ID
     * @param id O ID do período de matrícula
     * @return Um Optional contendo o período de matrícula, se encontrado
     */
    public Optional<PeriodoMatricula> buscarPorId(Long id) {
        return periodoMatriculaRepository.findById(id);
    }
    
    /**
     * Busca o período de matrícula atual
     * @return Um Optional contendo o período de matrícula atual, se encontrado
     */
    public Optional<PeriodoMatricula> buscarPeriodoAtual() {
        return periodoMatriculaRepository.findPeriodoMatriculaAtual(LocalDate.now());
    }
    
    /**
     * Lista todos os períodos de matrícula cadastrados
     * @return Uma lista de períodos de matrícula
     */
    public List<PeriodoMatricula> listarTodos() {
        return periodoMatriculaRepository.findAll();
    }
    
    /**
     * Salva um período de matrícula no sistema
     * @param periodoMatricula O período de matrícula a ser salvo
     * @return O período de matrícula salvo, se o cadastro for bem-sucedido
     */
    public Optional<PeriodoMatricula> salvar(PeriodoMatricula periodoMatricula) {
        if (periodoMatricula.getDataInicio() != null && periodoMatricula.getDataFim() != null) {
            if (periodoMatricula.getDataInicio().isBefore(periodoMatricula.getDataFim())) {
                return Optional.of(periodoMatriculaRepository.save(periodoMatricula));
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * Remove um período de matrícula do sistema
     * @param id O ID do período de matrícula a ser removido
     */
    public void remover(Long id) {
        periodoMatriculaRepository.deleteById(id);
    }
    
    /**
     * Verifica se existe um período de matrícula aberto no momento
     * @return true se existe um período de matrícula aberto, false caso contrário
     */
    public boolean existePeriodoAberto() {
        return periodoMatriculaRepository.existsPeriodoMatriculaAtual(LocalDate.now());
    }
} 