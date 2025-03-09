package com.sistema_matricula.sismatricula.Models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

/**
 * Classe que representa uma disciplina no sistema.
 */
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    @Enumerated(EnumType.STRING)
    private ETipoDisciplina tipo;
    
    @Enumerated(EnumType.STRING)
    private EEstadoDisciplina status;
    
    private static final int NUMERO_MAX_ALUNOS = 60;
    private static final int NUMERO_MIN_ALUNOS = 3;
    
    @ManyToOne
    private Professor professorResponsavel;
    
    @OneToMany(mappedBy = "disciplina")
    private List<Aluno> alunosMatriculados;

    public Disciplina() {
    }

    public Disciplina(Long id, String nome, ETipoDisciplina tipo, EEstadoDisciplina status,
                      Professor professorResponsavel, List<Aluno> alunosMatriculados) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.status = status;
        this.professorResponsavel = professorResponsavel;
        this.alunosMatriculados = alunosMatriculados;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public ETipoDisciplina getTipo() {
        return tipo;
    }
    public void setTipo(ETipoDisciplina tipo) {
        this.tipo = tipo;
    }
    public EEstadoDisciplina getStatus() {
        return status;
    }
    public void setStatus(EEstadoDisciplina status) {
        this.status = status;
    }
    public static int getNumeroMaxAlunos() {
        return NUMERO_MAX_ALUNOS;
    }
    public static int getNumeroMinAlunos() {
        return NUMERO_MIN_ALUNOS;
    }
    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }
    public void setProfessorResponsavel(Professor professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }
    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }
    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }
    
    /**
     * Ativa a disciplina, se condições forem atendidas.
     */
    public void ativarDisciplina() {
        // TODO: implementar
    }

    /**
     * Cancela a disciplina.
     */
    public void cancelarDisciplina() {
        // TODO: implementar
    }
    
    /**
     * Adiciona um aluno à disciplina
     * @param aluno aluno a ser adicionado
     */
    public void adicionarAluno(Aluno aluno) {
        // TODO: implementar
    }
    
    /**
     * Remove um aluno da disciplina
     * @param aluno aluno a ser removido
     */
    public void removerAluno(Aluno aluno) {
        // TODO: implementar
    }

    /**
     * Verifica se a quantidade de alunos atende aos requisitos.
     * @return true se a quantidade for válida, false caso contrário
     */
    public boolean verificarQuantidadeAlunos() {
        // TODO: implementar
        return false;
    }
    
    /**
     * Verifica se a disciplina atingiu o limite máximo de alunos.
     * @return true se atingiu, false caso contrário
     */
    public boolean verificarQuantidadeMaximaAlunos() {
        return alunosMatriculados.size() >= NUMERO_MAX_ALUNOS;
    }
} 