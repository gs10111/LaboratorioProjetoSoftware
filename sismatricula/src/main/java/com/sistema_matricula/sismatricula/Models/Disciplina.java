package com.sistema_matricula.sismatricula.Models;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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

    @OneToMany(mappedBy = "disciplina")
    private List<Matricula> matriculas;
    
    private static final int NUMERO_MAX_ALUNOS = 60;
    private static final int NUMERO_MIN_ALUNOS = 3;
    
    @ManyToOne
    private Professor professorResponsavel;

    public Disciplina() {
    }

    public Disciplina(Long id, String nome, ETipoDisciplina tipo, EEstadoDisciplina status,
                      Professor professorResponsavel) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.status = status;
        this.professorResponsavel = professorResponsavel;
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
    
    /**
     * Retorna a lista de matrículas da disciplina
     * @return lista de matrículas
     */
    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    
    /**
     * Define a lista de matrículas da disciplina
     * @param matriculas lista de matrículas
     */
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
    
    /**
     * Obtém a lista de alunos matriculados nesta disciplina
     * considerando apenas matrículas com situação EFETIVADA
     * @return lista de alunos com matrícula efetivada
     */
    public List<Aluno> getAlunosMatriculados() {
        if (matriculas == null) {
            return List.of();
        }
        return matriculas.stream()
                .filter(m -> m.getSituacao() == EEstadoMatricula.EFETIVADA)
                .map(Matricula::getAluno)
                .collect(Collectors.toList());
    }
    
    /**
     * Retorna o número atual de alunos matriculados com matrícula efetivada
     * @return quantidade de alunos matriculados
     */
    public int getNumeroAlunosMatriculados() {
        return getAlunosMatriculados().size();
    }
    
    /**
     * Ativa a disciplina, se condições forem atendidas.
     */
    public void ativarDisciplina() {
        // Verifica se a disciplina atende aos requisitos mínimos
        if (professorResponsavel != null && verificarQuantidadeAlunos()) {
            this.status = EEstadoDisciplina.ATIVA;
        } else {
            throw new IllegalStateException("A disciplina não atende aos requisitos mínimos para ser ativada.");
        }
    }

    /**
     * Cancela a disciplina.
     */
    public void cancelarDisciplina() {
        // Verificar se há regras para cancelamento
        this.status = EEstadoDisciplina.CANCELADA;
        
        // Notificar os alunos matriculados sobre o cancelamento
        for (Matricula matricula : matriculas) {
            if (matricula.getSituacao() == EEstadoMatricula.EFETIVADA) {
                matricula.cancelarMatricula();
            }
        }
    }
    
    /**
     * Adiciona um aluno à disciplina através de uma matrícula
     * @param aluno aluno a ser adicionado
     */
    public void adicionarAluno(Aluno aluno) {
        // Verificar se a disciplina já atingiu o limite de alunos
        if (verificarQuantidadeMaximaAlunos()) {
            throw new IllegalStateException("A disciplina já atingiu o número máximo de alunos.");
        }
        
        // Verificar se o aluno já está matriculado
        boolean alunoJaMatriculado = getAlunosMatriculados().contains(aluno);
        if (alunoJaMatriculado) {
            throw new IllegalStateException("O aluno já está matriculado nesta disciplina.");
        }
        
        // A criação da matrícula deve ser feita em outro lugar, como no serviço
    }
    
    /**
     * Remove um aluno da disciplina cancelando sua matrícula
     * @param aluno aluno a ser removido
     */
    public void removerAluno(Aluno aluno) {
        // Procurar a matrícula do aluno
        matriculas.stream()
                .filter(m -> m.getAluno().equals(aluno) && m.getSituacao() == EEstadoMatricula.EFETIVADA)
                .findFirst()
                .ifPresent(Matricula::cancelarMatricula);
    }

    /**
     * Verifica se a quantidade de alunos atende aos requisitos.
     * @return true se a quantidade for válida, false caso contrário
     */
    public boolean verificarQuantidadeAlunos() {
        int qtdAlunos = getNumeroAlunosMatriculados();
        return qtdAlunos >= NUMERO_MIN_ALUNOS;
    }
    
    /**
     * Verifica se a disciplina atingiu o limite máximo de alunos.
     * @return true se atingiu, false caso contrário
     */
    public boolean verificarQuantidadeMaximaAlunos() {
        return getNumeroAlunosMatriculados() >= NUMERO_MAX_ALUNOS;
    }
} 