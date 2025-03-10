package com.sistema_matricula.sismatricula.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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
    
    @OneToOne
    private PeriodoMatricula periodoMatricula;

    public Matricula(Aluno aluno, Disciplina disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.dataMatricula = LocalDate.now();
        this.situacao = EEstadoMatricula.EFETIVADA;
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
        if (validarPeriodoMatricula() && validarLimiteDisciplina()) {
            this.situacao = EEstadoMatricula.EFETIVADA;
            System.out.println("Matrícula efetivada com sucesso.");
        } else {
            System.out.println("Erro ao efetivar matrícula: Regras não atendidas.");
        }
    }

    /**
     * Cancela a matrícula (caso período e regras permitam).
     * @return void
     */
    public void cancelarMatricula() {
        if (this.situacao == EEstadoMatricula.EFETIVADA) {
            this.situacao = EEstadoMatricula.CANCELADA;
            System.out.println("Matrícula cancelada com sucesso.");
        } else {
            System.out.println("Erro: Matrícula não pode ser cancelada.");
        }
    }

    /**
     * Notifica o sistema de cobrança para efetuar cobrança do aluno.
     * @return void
     */
    public void notificarSistemaCobranca() {
        if (this.situacao == EEstadoMatricula.EFETIVADA) {
            System.out.println("Notificando sistema de cobrança para o aluno " + aluno.getDescricao());
            // Aqui você integraria com o sistema de cobrança.
        } else {
            System.out.println("Erro: Matrícula não efetivada. Não é possível notificar cobrança.");
        }
    }
    
    /**
     * Verifica se o período de matrícula está válido.
     * @return boolean indicando se o período é válido
     */
    public boolean validarPeriodoMatricula() {
        LocalDate hoje = LocalDate.now();
        // Supondo que o PeríodoMatricula tenha um intervalo de datas
        if (periodoMatricula != null) {
            return !hoje.isBefore(periodoMatricula.getDataInicio()) && !hoje.isAfter(periodoMatricula.getDataFim());
        }
        return false;
    }
    
    /**
     * Verifica se a disciplina tem limite de alunos disponível.
     * @return boolean indicando se há vagas disponíveis
     */
    public boolean validarLimiteDisciplina() {
        if (disciplina != null) {
            return disciplina.getNumeroAlunosMatriculados() < Disciplina.getNumeroMaxAlunos();
        }
        return false;
    }
} 