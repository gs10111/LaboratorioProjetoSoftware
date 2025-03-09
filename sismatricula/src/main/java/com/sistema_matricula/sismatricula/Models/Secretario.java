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

    /**
     * Gera um currículo.
     * @param curriculo currículo a ser gerado
     */
    public void gerarCurriculo(Curriculo curriculo) {
        if (curriculo != null) {
            //@TODO Adicionar lógica de validação para gerar o currículo
            System.out.println("Currículo gerado: " + curriculo.getNome());
        } else {
            throw new IllegalArgumentException("Currículo não pode ser nulo.");
        }
    }

    /**
     * Cadastra uma nova disciplina no sistema.
     * @param disciplina disciplina a ser cadastrada
     */
    public void cadastrarDisciplina(Disciplina disciplina) {
        if (disciplina != null) {
            // Validar se a disciplina atende aos critérios
            if (validarDisciplinaParaCadastro(disciplina)) {
                // Adicionar lógica para salvar a disciplina no banco de dados ou no repositório
                System.out.println("Disciplina cadastrada: " + disciplina.getNome());
            } else {
                throw new IllegalStateException("Disciplina não atende aos requisitos para cadastro.");
            }
        } else {
            throw new IllegalArgumentException("Disciplina não pode ser nula.");
        }
    }

    /**
     * Cancela uma disciplina.
     * @param disciplina disciplina a ser cancelada
     */
    public void cancelarDisciplina(Disciplina disciplina) {
        if (disciplina != null) {
            // Validar se a disciplina está ativa e pode ser cancelada
            if (disciplina.getStatus() == EEstadoDisciplina.ATIVA) {
                disciplina.setStatus(EEstadoDisciplina.CANCELADA);
                // Adicionar lógica para atualizar a disciplina no banco de dados
                System.out.println("Disciplina cancelada: " + disciplina.getNome());
            } else {
                throw new IllegalStateException("A disciplina não está ativa para cancelamento.");
            }
        } else {
            throw new IllegalArgumentException("Disciplina não pode ser nula.");
        }
    }
    
    /**
     * Verifica se uma disciplina atende aos requisitos de cadastro.
     * @param disciplina disciplina a ser verificada
     * @return boolean indicando se a disciplina é válida
     */
    public boolean validarDisciplinaParaCadastro(Disciplina disciplina) {
        if (disciplina == null) {
            return false;
        }
        boolean quantidadeAlunosValida = disciplina.verificarQuantidadeAlunos();
        boolean professorValido = disciplina.getProfessorResponsavel() != null;

        return quantidadeAlunosValida && professorValido;
    }
}
