package com.sistemaMatricula.sistema.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa um Currículo no sistema
 */
@Entity
@Table(name = "curriculos")
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "curriculo_disciplinas",
        joinColumns = @JoinColumn(name = "curriculo_id"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas = new ArrayList<>();
    
    /**
     * Método para adicionar uma disciplina ao currículo
     * @param disciplina A disciplina a ser adicionada
     * @return true se a disciplina foi adicionada com sucesso, false caso contrário
     */
    public boolean adicionarDisciplina(Disciplina disciplina) {
        if (!this.disciplinas.contains(disciplina)) {
            return this.disciplinas.add(disciplina);
        }
        return false;
    }
    
    /**
     * Método para remover uma disciplina do currículo
     * @param disciplina A disciplina a ser removida
     * @return true se a disciplina foi removida com sucesso, false caso contrário
     */
    public boolean removerDisciplina(Disciplina disciplina) {
        return this.disciplinas.remove(disciplina);
    }
    
    /**
     * Método para validar se todas as disciplinas obrigatórias estão no currículo
     * @return true se o currículo é válido, false caso contrário
     */
    public boolean validarSeDisciplinasObrigatorias() {
        return !this.disciplinas.isEmpty();
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
        Curriculo curriculo = (Curriculo) o;
        return Objects.equals(id, curriculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
} 