package com.sistemaMatricula.sistema.model;

import com.sistemaMatricula.sistema.enums.EPerfilUsuario;
import jakarta.persistence.*;

/**
 * Classe que representa uma Secretaria no sistema
 */
@Entity
@Table(name = "secretarias")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Secretaria extends UsuarioBase {

    /**
     * Construtor padrão
     */
    public Secretaria() {
        setTipo(EPerfilUsuario.SECRETARIA);
    }

    /**
     * Método para validar se uma disciplina pode ser cadastrada
     * @param disciplina A disciplina a ser validada
     * @return true se a disciplina pode ser cadastrada, false caso contrário
     */
    public boolean validarDisciplinaParaCadastro(Disciplina disciplina) {
        return disciplina != null && disciplina.getNome() != null && 
               !disciplina.getNome().isEmpty() && disciplina.getProfessorResponsavel() != null;
    }
} 