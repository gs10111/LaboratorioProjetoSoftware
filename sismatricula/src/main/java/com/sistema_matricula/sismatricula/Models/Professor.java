package com.sistema_matricula.sismatricula.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

/**
 * Classe que representa um professor do sistema.
 */
@Entity
public class Professor extends UsuarioBase {

    private String siape;
    
    @OneToMany(mappedBy = "professorResponsavel")
    private List<Disciplina> disciplinasMinistradas;

    public Professor() {
        super();
    }

    public Professor(Long id, String descricao, String email, String senha, String login, EPerfilUsuario tipo,
                     String siape, List<Disciplina> disciplinasMinistradas) {
        super(id, descricao, email, senha, login, tipo);
        this.siape = siape;
        this.disciplinasMinistradas = disciplinasMinistradas;
    }

    // Getters e Setters
    public String getSiape() {
        return siape;
    }
    public void setSiape(String siape) {
        this.siape = siape;
    }
    public List<Disciplina> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }
    public void setDisciplinasMinistradas(List<Disciplina> disciplinasMinistradas) {
        this.disciplinasMinistradas = disciplinasMinistradas;
    }
    
    /**
     * Adiciona uma disciplina ao professor.
     * @param disciplina disciplina a ser adicionada
     * @return String indicando o sucesso ou falha da operação
     */
    public void adicionarDisciplinaMinistrada(Disciplina disciplina) {
        if (disciplina != null && !disciplinasMinistradas.contains(disciplina)) {
            disciplinasMinistradas.add(disciplina);
        } else {
            throw new IllegalStateException("A disciplina já está no currículo ou é nula.");
        }
    }
} 