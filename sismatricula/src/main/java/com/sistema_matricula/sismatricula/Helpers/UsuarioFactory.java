package com.sistema_matricula.sismatricula.Helpers;

import com.sistema_matricula.sismatricula.Models.*;

/**
 * Classe de fábrica para criação de diferentes tipos de usuários.
 */
public class UsuarioFactory {

    /**
     * Cria um usuário com base em um perfil específico.
     * 
     * @param tipo tipo de perfil do usuário (ALUNO, PROFESSOR, etc.)
     * @param descricao descrição do usuário
     * @param login login do usuário
     * @param senha senha do usuário
     * @return instância de UsuarioBase correspondente
     */
    public static UsuarioBase criarUsuario(EPerfilUsuario tipo, String descricao, String login, String senha) {
        switch (tipo) {
            case ALUNO:
                return new Aluno();
            case PROFESSOR:
                return new Professor();
            case SECRETARIA:
                return new Secretario();
            case ADMINISTRADOR:
                return new Administrador();
            default:
                throw new IllegalArgumentException("Perfil de usuário inválido: " + tipo);
        }
    }
} 