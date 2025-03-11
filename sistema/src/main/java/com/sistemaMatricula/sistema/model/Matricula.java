package com.sistemaMatricula.sistema.model;

import com.sistemaMatricula.sistema.enums.EStatusMatricula;
import jakarta.persistence.*;
import java.util.Objects;

/**
 * Classe que representa uma Matrícula no sistema
 */
@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodo_matricula_id", nullable = false)
    private PeriodoMatricula periodoMatricula;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatusMatricula status;
    
    /**
     * Método para efetivar a matrícula
     */
    public void efetivarMatricula() {
        this.status = EStatusMatricula.EFETIVADA;
    }
    
    /**
     * Método para cancelar a matrícula
     */
    public void cancelarMatricula() {
        this.status = EStatusMatricula.CANCELADA;
    }
    
    /**
     * Método para notificar o sistema de cobranças
     */
    public void notificarSistemaCobranca() {
        System.out.println("Notificando sistema de cobrança para o aluno " + 
                          aluno.getNome() + " na disciplina " + 
                          disciplina.getNome());
    }
    
    /**
     * Método para validar se o período de matrícula é válido
     * @return true se o período de matrícula é válido, false caso contrário
     */
    public boolean validarPeriodoMatricula() {
        return periodoMatricula != null && periodoMatricula.estaAberto();
    }
    
    /**
     * Método para validar se o limite de disciplinas foi atingido
     * @return true se está dentro do limite, false caso contrário
     */
    public boolean validarLimiteDisciplinas() {
        return aluno.validarLimiteDisciplinas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public PeriodoMatricula getPeriodoMatricula() {
        return periodoMatricula;
    }

    public void setPeriodoMatricula(PeriodoMatricula periodoMatricula) {
        this.periodoMatricula = periodoMatricula;
    }

    public EStatusMatricula getStatus() {
        return status;
    }

    public void setStatus(EStatusMatricula status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(id, matricula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
} 