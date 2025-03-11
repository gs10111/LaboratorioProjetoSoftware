package com.sistemaMatricula.sistema.model;

import com.sistemaMatricula.sistema.enums.EPerfilUsuario;
import com.sistemaMatricula.sistema.enums.EStatusMatricula;
import com.sistemaMatricula.sistema.enums.ETipoDisciplina;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Aluno no sistema
 */
@Entity
@Table(name = "alunos")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Aluno extends UsuarioBase {

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matricula> matriculas = new ArrayList<>();
    
    private static final int MAX_DISCIPLINAS_OBRIGATORIAS = 4;
    private static final int MAX_DISCIPLINAS_OPTATIVAS = 2;

    /**
     * Construtor padrão
     */
    public Aluno() {
        setTipo(EPerfilUsuario.ALUNO);
    }
    
    /**
     * Método para solicitar matrícula em uma disciplina
     * @param disciplina A disciplina em que o aluno deseja se matricular
     * @return true se a matrícula foi solicitada com sucesso, false caso contrário
     */
    public boolean solicitarMatricula(Disciplina disciplina) {
        if (disciplina.getTipo().equals(ETipoDisciplina.OBRIGATORIA) && 
            contarDisciplinasPorTipo(ETipoDisciplina.OBRIGATORIA) >= MAX_DISCIPLINAS_OBRIGATORIAS) {
            return false;
        }
        
        if (disciplina.getTipo().equals(ETipoDisciplina.OPTATIVA) && 
            contarDisciplinasPorTipo(ETipoDisciplina.OPTATIVA) >= MAX_DISCIPLINAS_OPTATIVAS) {
            return false;
        }
        
        if (disciplina.getAlunosMatriculados().size() >= Disciplina.NUMERO_MAX_ALUNOS) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Método para listar todas as disciplinas matriculadas
     * @return Uma lista de disciplinas em que o aluno está matriculado
     */
    public String listarDisciplinasMatriculadas() {
        StringBuilder result = new StringBuilder("Disciplinas matriculadas:\n");
        for (Matricula m : this.matriculas) {
            result.append(m.getDisciplina().getNome()).append(" - ")
                  .append(m.getStatus()).append("\n");
        }
        return result.toString();
    }
    
    /**
     * Método para validar se o limite de disciplinas foi atingido
     * @return true se está dentro do limite, false caso contrário
     */
    public boolean validarLimiteDisciplinas() {
        return contarDisciplinasPorTipo(ETipoDisciplina.OBRIGATORIA) <= MAX_DISCIPLINAS_OBRIGATORIAS &&
               contarDisciplinasPorTipo(ETipoDisciplina.OPTATIVA) <= MAX_DISCIPLINAS_OPTATIVAS;
    }
    
    /**
     * Método privado para contar quantas disciplinas de um tipo específico o aluno está matriculado
     * @param tipo O tipo de disciplina a ser contado
     * @return O número de disciplinas do tipo especificado
     */
    private int contarDisciplinasPorTipo(ETipoDisciplina tipo) {
        int count = 0;
        for (Matricula m : this.matriculas) {
            if (m.getDisciplina().getTipo().equals(tipo) && m.getStatus().equals(EStatusMatricula.EFETIVADA)) {
                count++;
            }
        }
        return count;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
    
    public static int getMaxDisciplinasObrigatorias() {
        return MAX_DISCIPLINAS_OBRIGATORIAS;
    }
    
    public static int getMaxDisciplinasOptativas() {
        return MAX_DISCIPLINAS_OPTATIVAS;
    }
} 