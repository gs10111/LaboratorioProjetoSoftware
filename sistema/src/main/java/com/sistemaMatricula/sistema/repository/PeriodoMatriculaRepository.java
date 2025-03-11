package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.model.PeriodoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Períodos de Matrícula
 */
@Repository
public interface PeriodoMatriculaRepository extends JpaRepository<PeriodoMatricula, Long> {
    
    /**
     * Busca um período de matrícula atual
     * @return Um Optional contendo o período de matrícula atual, se encontrado
     */
    @Query("SELECT p FROM PeriodoMatricula p WHERE :hoje BETWEEN p.dataInicio AND p.dataFim")
    Optional<PeriodoMatricula> findPeriodoMatriculaAtual(LocalDate hoje);
    
    /**
     * Verifica se existe um período de matrícula atual
     * @return true se existe um período de matrícula atual, false caso contrário
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PeriodoMatricula p WHERE :hoje BETWEEN p.dataInicio AND p.dataFim")
    boolean existsPeriodoMatriculaAtual(LocalDate hoje);
    
    /**
     * Busca períodos de matrícula por data de início
     * @param dataInicio A data de início
     * @return Uma lista de períodos de matrícula com a data de início especificada
     */
    Optional<PeriodoMatricula> findByDataInicio(LocalDate dataInicio);
    
    /**
     * Busca períodos de matrícula por data de fim
     * @param dataFim A data de fim
     * @return Uma lista de períodos de matrícula com a data de fim especificada
     */
    Optional<PeriodoMatricula> findByDataFim(LocalDate dataFim);
} 