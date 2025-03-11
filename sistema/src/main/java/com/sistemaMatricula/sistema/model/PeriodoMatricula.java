package com.sistemaMatricula.sistema.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe que representa um Período de Matrícula no sistema
 */
@Entity
@Table(name = "periodos_matricula")
public class PeriodoMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate dataInicio;
    
    @Column(nullable = false)
    private LocalDate dataFim;
    
    @OneToMany(mappedBy = "periodoMatricula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matricula> matriculas = new ArrayList<>();
    
    /**
     * Método para verificar se o período está aberto
     * @return true se o período está aberto, false caso contrário
     */
    public boolean estaAberto() {
        LocalDate hoje = LocalDate.now();
        return !hoje.isBefore(dataInicio) && !hoje.isAfter(dataFim);
    }
    
    /**
     * Método para validar uma data
     * @param data A data a ser validada
     * @return true se a data é válida, false caso contrário
     */
    public boolean validarData(LocalDate data) {
        return data != null && !data.isBefore(LocalDate.now());
    }
    
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
    
    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodoMatricula that = (PeriodoMatricula) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
} 