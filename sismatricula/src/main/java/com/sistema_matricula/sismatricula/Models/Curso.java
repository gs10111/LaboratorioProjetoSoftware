package com.sistema_matricula.sismatricula.Models;

import java.util.List;

/**
 * Classe que representa um curso na universidade.
 */
public class Curso {

    private Long id;
    private String nome;
    private int totalCreditos;
    private List<Disciplina> disciplinas;

    public Curso() {
    }

    public Curso(Long id, String nome, int totalCreditos, List<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.totalCreditos = totalCreditos;
        this.disciplinas = disciplinas;
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

    /**
     * Adiciona uma disciplina ao curso.
     * @param disciplina disciplina a ser adicionada
     */
    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        } else {
            throw new IllegalStateException("A disciplina já está no currículo ou é nula.");
        }
    }

    /**
     * Remove uma disciplina do curso.
     * @param disciplina disciplina a ser removida
     */
    public void removerDisciplina(Disciplina disciplina) {
        if (disciplinas.contains(disciplina)) {
            disciplinas.remove(disciplina);
        } else {
            throw new IllegalStateException("A disciplina não está no currículo.");
        }
    }
} 