package com.sistemaMatricula.sistema.model;

import com.sistemaMatricula.sistema.enums.EPerfilUsuario;
import jakarta.persistence.*;
import java.util.Objects;

/**
 * Classe abstrata que representa um usuário base no sistema
 */
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UsuarioBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EPerfilUsuario tipo;

    /**
     * Método para validar a senha do usuário
     * @param senha A senha a ser validada
     * @return true se a senha for válida, false caso contrário
     */
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }

    /**
     * Método para validar o login do usuário
     * @param login O login a ser validado
     * @return true se o login for válido, false caso contrário
     */
    public boolean validarLogin(String login) {
        return this.login.equals(login);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EPerfilUsuario getTipo() {
        return tipo;
    }

    public void setTipo(EPerfilUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioBase that = (UsuarioBase) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
} 