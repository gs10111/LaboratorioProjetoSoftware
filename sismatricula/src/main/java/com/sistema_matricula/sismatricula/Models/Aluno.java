package com.sistema_matricula.sismatricula.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

/**
 * Classe que representa um aluno do sistema.
 */
@Entity
public class Aluno extends UsuarioBase {

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
                 EPerfilUsuario tipo, List<Matricula> matriculas) {
        super(id, descricao, email, senha, login, tipo);
        this.matriculas = matriculas;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    /**
     * Solicita a matrícula em uma disciplina.
     * @param disciplina disciplina desejada
     * @return void
     */
    public void solicitarMatricula(Disciplina disciplina) {
        if (disciplina.verificarQuantidadeMaximaAlunos()) {
            System.out.println("A disciplina já atingiu o número máximo de alunos.");
            return;
        }

        if (!validarLimiteDisciplinas()) {
            System.out.println("O aluno já atingiu o limite de disciplinas permitidas.");
            return;
        }

        Matricula novaMatricula = new Matricula(this, disciplina);
        matriculas.add(novaMatricula);
        disciplina.adicionarAluno(this);
        System.out.println("Matrícula realizada com sucesso na disciplina: " + disciplina.getNome());
    }

    /**
     * Cancela a matrícula em uma disciplina.
     * @param disciplina disciplina a cancelar
     * @return void
     */
    public void cancelarMatricula(Matricula matricula) {
        matricula.cancelarMatricula();
    }
    
    /**
     * Lista as disciplinas matriculadas pelo aluno.
     * @return String com as disciplinas
     */
    public String listarDisciplinasMatriculadas() {
        if (matriculas.isEmpty()) {
            return "O aluno não está matriculado em nenhuma disciplina.";
        }

        StringBuilder disciplinas = new StringBuilder("Disciplinas matriculadas:\n");
        for (Matricula matricula : matriculas) {
            disciplinas.append("- ").append(matricula.getDisciplina().getNome()).append("\n");
        }
        return disciplinas.toString();
    }
    
    /**
     * Verifica se o limite de disciplinas foi atingido.
     * @return boolean indicando se o limite foi atingido
     */
    public boolean validarLimiteDisciplinas() {
        int obrigatorias = 0;
        int optativas = 0;

        for (Matricula matricula : matriculas) {
            if (matricula.getDisciplina().getTipo() == ETipoDisciplina.OBRIGATORIA) {
                obrigatorias++;
            } else if (matricula.getDisciplina().getTipo() == ETipoDisciplina.OPTATIVA) {
                optativas++;
            }
        }

        return obrigatorias < MAX_DISCIPLINAS_OBRIGATORIAS && optativas < MAX_DISCIPLINAS_OPTATIVAS;
    }
} 