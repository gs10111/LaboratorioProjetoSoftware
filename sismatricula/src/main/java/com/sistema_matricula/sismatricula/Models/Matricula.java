package com.sistema_matricula.sismatricula.Models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

/**
 * Classe que representa a matrícula de um aluno em uma disciplina.
 */
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Aluno aluno;
    
    @ManyToOne
    private Disciplina disciplina;
    
    private LocalDate dataMatricula;
    
    @Enumerated(EnumType.STRING)
    private EEstadoMatricula situacao;
    
    @ManyToOne
    private PeriodoMatricula periodoMatricula;

    public Matricula() {
    }

    public Matricula(Long id, Aluno aluno, Disciplina disciplina, LocalDate dataMatricula, 
                     EEstadoMatricula situacao, PeriodoMatricula periodoMatricula) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.dataMatricula = dataMatricula;
        this.situacao = situacao;
        this.periodoMatricula = periodoMatricula;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public Disciplina getDisciplina() {
        return disciplina;
    }
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    public LocalDate getDataMatricula() {
        return dataMatricula;
    }
    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }
    public EEstadoMatricula getSituacao() {
        return situacao;
    }
    public void setSituacao(EEstadoMatricula situacao) {
        this.situacao = situacao;
    }
    public PeriodoMatricula getPeriodoMatricula() {
        return periodoMatricula;
    }
    public void setPeriodoMatricula(PeriodoMatricula periodoMatricula) {
        this.periodoMatricula = periodoMatricula;
    }

    /**
     * Efetiva a matrícula (caso período e regras permitam).
     * @return void
     */
    public void efetivarMatricula() {
        // TODO: implementar
    }

    /**
     * Cancela a matrícula (caso período e regras permitam).
     * @return void
     */
    public void cancelarMatricula() {
        // TODO: implementar
    }

    /**
     * Notifica o sistema de cobrança para efetuar cobrança do aluno.
     * @return void
     */
    public void notificarSistemaCobranca() {
        // TODO: implementar
    }
    
    /**
     * Verifica se o período de matrícula está válido.
     * @return boolean indicando se o período é válido
     */
    public boolean validarPeriodoMatricula() {
        // TODO: implementar
        return false;
    }
    
    /**
     * Verifica se a disciplina tem limite de alunos disponível.
     * @return boolean indicando se há vagas disponíveis
     */
    public boolean validarLimiteDisciplina() {
        // TODO: implementar
        return false;
    }
} 