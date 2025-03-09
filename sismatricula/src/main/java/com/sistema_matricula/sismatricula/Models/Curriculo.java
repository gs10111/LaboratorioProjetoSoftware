package com.sistema_matricula.sismatricula.Models;

import java.util.List;

/**
 * Classe que representa o currículo (conjunto de disciplinas) de um semestre.
 */
public class Curriculo {

    private Long id;
    private String nome;
    private List<Disciplina> disciplinas;

    public Curriculo() {
    }

    public Curriculo(Long id, String nome, List<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
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
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    /**
     * Adiciona uma disciplina ao currículo.
     * @param disciplina disciplina a ser adicionada
     */
    public void adicionarDisciplina(Disciplina disciplina) {
        // TODO: implementar
    }

    /**
     * Remove uma disciplina do currículo.
     * @param disciplina disciplina a ser removida
     */
    public void removerDisciplina(Disciplina disciplina) {
        // TODO: implementar
    }
} 