package com.sistema_matricula.sismatricula.Models;

import java.time.LocalDate;

/**
 * Classe que representa o período de matrícula.
 */
public class PeriodoMatricula {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public PeriodoMatricula() {
    }

    public PeriodoMatricula(Long id, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Verifica se o período de matrícula está aberto.
     * @return true se estiver aberto, false caso contrário
     */
    public boolean estaAberto() {
        // TODO: implementar lógica real
        return false;
    }
} 