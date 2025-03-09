package com.sistema_matricula.sismatricula.Models;

import jakarta.persistence.Entity;

/**
 * Classe que representa um usuário do tipo Secretário.
 */
@Entity
public class Secretario extends UsuarioBase {

    public Secretario() {
        super();
    }

    public Secretario(Long id, String descricao, String email, String senha, String login, EPerfilUsuario tipo) {
        super(id, descricao, email, senha, login, tipo);
    }

    @Override
    public void autenticar() {
        // TODO: Implementar lógica de autenticação para Secretário
    }

    /**
     * Gera um currículo.
     * @param curriculo currículo a ser gerado
     * @return void
     */
    public void gerarCurriculo(Curriculo curriculo) {
        // TODO: implementar
    }

    /**
     * Cadastra uma nova disciplina no sistema.
     * @param disciplina disciplina a ser cadastrada
     * @return void
     */
    public void cadastrarDisciplina(Disciplina disciplina) {
        // TODO: implementar
    }

    /**
     * Cancela uma disciplina.
     * @param disciplina disciplina a ser cancelada
     * @return void
     */
    public void cancelarDisciplina(Disciplina disciplina) {
        // TODO: implementar
    }
    
    /**
     * Verifica se uma disciplina atende aos requisitos de cadastro.
     * @param disciplina disciplina a ser verificada
     * @return boolean indicando se a disciplina é válida
     */
    public boolean validarDisciplinaParaCadastro(Disciplina disciplina) {
        // TODO: implementar
        return false;
    }
} 