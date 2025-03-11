package com.sistemaMatricula.sistema.model;

import com.sistemaMatricula.sistema.enums.EStatusDisciplina;
import com.sistemaMatricula.sistema.enums.ETipoDisciplina;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa uma Disciplina no sistema
 */
@Entity
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ETipoDisciplina tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatusDisciplina status;
    
    public static final int NUMERO_MAX_ALUNOS = 60;
    public static final int NUMERO_MIN_ALUNOS = 3;
    
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professorResponsavel;
    
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matricula> alunosMatriculados = new ArrayList<>();
    
    /**
     * Método para ativar a disciplina
     */
    public void ativarDisciplina() {
        this.status = EStatusDisciplina.ATIVA;
    }
    
    /**
     * Método para cancelar a disciplina
     */
    public void cancelarDisciplina() {
        this.status = EStatusDisciplina.CANCELADA;
    }
    
    /**
     * Método para adicionar um aluno à disciplina
     * @param aluno O aluno a ser adicionado
     * @return true se o aluno foi adicionado com sucesso, false caso contrário
     */
    public boolean adicionarAluno(Aluno aluno) {
        if (this.alunosMatriculados.size() >= NUMERO_MAX_ALUNOS) {
            return false;
        }
        
        for (Matricula m : this.alunosMatriculados) {
            if (m.getAluno().equals(aluno)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Método para verificar se a disciplina tem o número mínimo de alunos
     * @return true se a disciplina tem pelo menos o número mínimo de alunos, false caso contrário
     */
    public boolean verificarNumeroMinimoAlunos() {
        return this.alunosMatriculados.size() >= NUMERO_MIN_ALUNOS;
    }
    
    /**
     * Método para verificar se a disciplina está cheia
     * @return true se a disciplina está cheia, false caso contrário
     */
    public boolean verificarDisciplinaCheia() {
        return this.alunosMatriculados.size() >= NUMERO_MAX_ALUNOS;
    }
    
    /**
     * Método para listar todos os alunos matriculados na disciplina
     * @return Uma string com os nomes dos alunos matriculados
     */
    public String listarAlunos() {
        StringBuilder result = new StringBuilder("Alunos matriculados em " + this.nome + ":\n");
        for (Matricula m : this.alunosMatriculados) {
            result.append(m.getAluno().getNome()).append("\n");
        }
        return result.toString();
    }

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

    public EStatusDisciplina getStatus() {
        return status;
    }

    public void setStatus(EStatusDisciplina status) {
        this.status = status;
    }

    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(Professor professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Matricula> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void setAlunosMatriculados(List<Matricula> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
} 