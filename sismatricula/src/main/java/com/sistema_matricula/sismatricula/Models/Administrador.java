package com.sistema_matricula.sismatricula.Models;

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

    @Override
    public void autenticar() {
        // TODO: Implementar lógica de autenticação para Administrador
    }

    /**
     * Cria um novo usuário.
     * @param usuario usuário a ser criado
     * @return void
     */
    public void criarUsuario(UsuarioBase usuario) {
        // TODO: implementar
    }

    /**
     * Remove um usuário.
     * @param usuario usuário a ser removido
     * @return void
     */
    public void removerUsuario(UsuarioBase usuario) {
        // TODO: implementar
    }

    /**
     * Altera o perfil de um usuário.
     * @param usuario usuário que terá o perfil alterado
     * @return void
     */
    public void alterarPerfilUsuario(UsuarioBase usuario) {
        // TODO: implementar
    }
} 