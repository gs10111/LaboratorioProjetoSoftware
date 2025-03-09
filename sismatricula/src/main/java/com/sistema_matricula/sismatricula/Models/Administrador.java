package com.sistema_matricula.sismatricula.Models;

import com.sistema_matricula.sismatricula.Services.AdministradorService;

import jakarta.persistence.Entity;

/**
 * Classe que representa um usuário do tipo Administrador.
 */
@Entity
public class Administrador extends UsuarioBase {

    public Administrador() {
        super();
    }

    public Administrador(Long id, String descricao, String email, String senha, String login, EPerfilUsuario tipo) {
        super(id, descricao, email, senha, login, tipo);
    }

    /**
     * Cria um novo usuário.
     * @param usuario usuário a ser criado
     * @return void
     */
    public void criarUsuario(UsuarioBase usuario) {
        AdministradorService administradorService = new AdministradorService();
        administradorService.criarUsuario(usuario);
    }

    /**
     * Altera o perfil de um usuário.
     * @param usuario usuário que terá o perfil alterado
     * @return void
     */
    public void alterarPerfilUsuario(EPerfilUsuario novoPerfil) {
        this.setTipo(novoPerfil);
    }
} 