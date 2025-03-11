package com.sistemaMatricula.sistema.model;

import com.sistemaMatricula.sistema.enums.EPerfilUsuario;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Professor no sistema
 */
@Entity
@Table(name = "professores")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Professor extends UsuarioBase {

    @OneToMany(mappedBy = "professorResponsavel", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinasMinistrando = new ArrayList<>();
    
    /**
     * Construtor padrão
     */
    public Professor() {
        setTipo(EPerfilUsuario.PROFESSOR);
    }
    
    /**
     * Método para listar todas as disciplinas que o professor ministra
     * @return Uma string com as informações das disciplinas
     */
    public String listarAlunosPorDisciplina(Disciplina disciplina) {
        if (disciplinasMinistrando.contains(disciplina)) {
            return disciplina.listarAlunos();
        }
        return "Você não é o professor responsável por esta disciplina.";
    }
    
    /**
     * Método para adicionar uma disciplina à lista de disciplinas ministradas
     * @param disciplina A disciplina a ser adicionada
     * @return Uma string descrevendo o resultado da operação
     */
    public String adicionarDisciplinaMinistrada(Disciplina disciplina) {
        if (!disciplinasMinistrando.contains(disciplina)) {
            disciplinasMinistrando.add(disciplina);
            disciplina.setProfessorResponsavel(this);
            return "Disciplina adicionada com sucesso!";
        }
        return "Você já está ministrando esta disciplina.";
    }
    
    public List<Disciplina> getDisciplinasMinistrando() {
        return disciplinasMinistrando;
    }
    
    public void setDisciplinasMinistrando(List<Disciplina> disciplinasMinistrando) {
        this.disciplinasMinistrando = disciplinasMinistrando;
    }
} 