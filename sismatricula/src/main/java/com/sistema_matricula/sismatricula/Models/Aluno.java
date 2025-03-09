package com.sistema_matricula.sismatricula.Models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

/**
 * Classe que representa um aluno do sistema.
 */
@Entity
public class Aluno extends UsuarioBase {

    private String ra; // Registro Acadêmico
    private static final int MAX_DISCIPLINAS_OBRIGATORIAS = 4;
    private static final int MAX_DISCIPLINAS_OPTATIVAS = 2;
    
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Matricula> matriculas;

    public Aluno() {
        super();
    }

    /**
     * Construtor completo.
     */
    public Aluno(Long id, String descricao, String email, String senha, String login, 
                 EPerfilUsuario tipo, String ra, List<Matricula> matriculas) {
        super(id, descricao, email, senha, login, tipo);
        this.ra = ra;
        this.matriculas = matriculas;
    }

    // Getters e Setters
    public String getRa() {
        return ra;
    }
    public void setRa(String ra) {
        this.ra = ra;
    }
    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public void autenticar() {
        // TODO: Implementar lógica de autenticação para Aluno
    }

    /**
     * Solicita a matrícula em uma disciplina.
     * @param disciplina disciplina desejada
     * @return void
     */
    public void solicitarMatricula(Disciplina disciplina) {
        // TODO: implementar
    }

    /**
     * Cancela a matrícula em uma disciplina.
     * @param disciplina disciplina a cancelar
     * @return void
     */
    public void cancelarMatricula(Disciplina disciplina) {
        // TODO: implementar
    }
    
    /**
     * Lista as disciplinas matriculadas pelo aluno.
     * @return String com as disciplinas
     */
    public String listarDisciplinasMatriculadas() {
        // TODO: implementar
        return "";
    }
    
    /**
     * Verifica se o limite de disciplinas foi atingido.
     * @return boolean indicando se o limite foi atingido
     */
    public boolean validarLimiteDisciplinas() {
        // TODO: implementar
        return false;
    }
} 