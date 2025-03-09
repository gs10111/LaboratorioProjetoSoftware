package com.sistema_matricula.sismatricula.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Classe abstrata que representa um usuário genérico do sistema.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UsuarioBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String email;
    private String senha;
    private String login;
    @Enumerated(EnumType.STRING)
    private EPerfilUsuario tipo;

    /**
     * Construtor padrão.
     */
    public UsuarioBase() {
    }

    /**
     * Construtor completo.
     * 
     * @param id        identificador do usuário
     * @param descricao descrição do usuário
     * @param email     email do usuário
     * @param senha     senha do usuário
     * @param login     login do usuário
     * @param tipo      tipo de perfil do usuário
     */
    public UsuarioBase(Long id, String descricao, String email, String senha, String login, EPerfilUsuario tipo) {
        this.id = id;
        this.descricao = descricao;
        this.email = email;
        this.senha = senha;
        this.login = login;
        this.tipo = tipo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public EPerfilUsuario getTipo() {
        return tipo;
    }
    public void setTipo(EPerfilUsuario tipo) {
        this.tipo = tipo;
    }

    /**
     * Método para autenticação do usuário.
     */
    public abstract void autenticar();
    
    /**
     * Valida a senha do usuário.
     * @param senha senha a ser validada
     * @return true se a senha for válida, false caso contrário
     */
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }
    
    /**
     * Valida o login do usuário.
     * @param login login a ser validado
     * @return true se o login for válido, false caso contrário
     */
    public boolean validarLogin(String login) {
        return this.login.equals(login);
    }
}
