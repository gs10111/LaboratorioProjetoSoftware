package com.sistemaMatricula.sistema.model;

import com.sistemaMatricula.sistema.enums.EPerfilUsuario;
import jakarta.persistence.*;

/**
 * Classe que representa um Administrador no sistema
 */
@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Administrador extends UsuarioBase {

    /**
     * Construtor padrão
     */
    public Administrador() {
        setTipo(EPerfilUsuario.ADMINISTRADOR);
    }
} 