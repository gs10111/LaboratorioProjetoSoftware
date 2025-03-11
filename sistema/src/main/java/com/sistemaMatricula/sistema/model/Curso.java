package com.sistemaMatricula.sistema.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa um Curso no sistema
 */
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private int totalCreditos;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Disciplina> disciplinas = new ArrayList<>();
    
    /**
     * Método para adicionar uma disciplina ao curso
     * @param disciplina A disciplina a ser adicionada
     * @return true se a disciplina foi adicionada com sucesso, false caso contrário
     */
    public boolean adicionarDisciplina(Disciplina disciplina) {
        if (!this.disciplinas.contains(disciplina)) {
            this.disciplinas.add(disciplina);
            disciplina.setCurso(this);
            return true;
        }
        return false;
    }
    
    /**
     * Método para remover uma disciplina do curso
     * @param disciplina A disciplina a ser removida
     * @return true se a disciplina foi removida com sucesso, false caso contrário
     */
    public boolean removerDisciplina(Disciplina disciplina) {
        if (this.disciplinas.contains(disciplina)) {
            this.disciplinas.remove(disciplina);
            disciplina.setCurso(null);
            return true;
        }
        return false;
    }
    
    /**
     * Método para validar se o total de créditos é válido
     * @return true se o total de créditos é válido, false caso contrário
     */
    public boolean validarCreditos() {
        return this.totalCreditos > 0;
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

    public int getTotalCreditos() {
        return totalCreditos;
    }

    public void setTotalCreditos(int totalCreditos) {
        this.totalCreditos = totalCreditos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
} 