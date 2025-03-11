package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.enums.EStatusMatricula;
import com.sistemaMatricula.sistema.model.Aluno;
import com.sistemaMatricula.sistema.model.Disciplina;
import com.sistemaMatricula.sistema.model.Matricula;
import com.sistemaMatricula.sistema.model.PeriodoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Matrículas
 */
@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    
    /**
     * Busca matrículas por aluno
     * @param aluno O aluno
     * @return Uma lista de matrículas do aluno
     */
    List<Matricula> findByAluno(Aluno aluno);
    
    /**
     * Busca matrículas por disciplina
     * @param disciplina A disciplina
     * @return Uma lista de matrículas da disciplina
     */
    List<Matricula> findByDisciplina(Disciplina disciplina);
    
    /**
     * Busca matrículas por período de matrícula
     * @param periodoMatricula O período de matrícula
     * @return Uma lista de matrículas do período
     */
    List<Matricula> findByPeriodoMatricula(PeriodoMatricula periodoMatricula);
    
    /**
     * Busca matrículas por status
     * @param status O status da matrícula
     * @return Uma lista de matrículas com o status especificado
     */
    List<Matricula> findByStatus(EStatusMatricula status);
    
    /**
     * Busca matrículas por aluno e disciplina
     * @param aluno O aluno
     * @param disciplina A disciplina
     * @return Um Optional contendo a matrícula, se encontrada
     */
    Optional<Matricula> findByAlunoAndDisciplina(Aluno aluno, Disciplina disciplina);
    
    /**
     * Busca matrículas por aluno e período de matrícula
     * @param aluno O aluno
     * @param periodoMatricula O período de matrícula
     * @return Uma lista de matrículas do aluno no período especificado
     */
    List<Matricula> findByAlunoAndPeriodoMatricula(Aluno aluno, PeriodoMatricula periodoMatricula);
    
    /**
     * Conta o número de alunos matriculados em uma disciplina
     * @param disciplina A disciplina
     * @return O número de alunos matriculados na disciplina
     */
    @Query("SELECT COUNT(m) FROM Matricula m WHERE m.disciplina = :disciplina AND m.status = 'EFETIVADA'")
    long countAlunosMatriculadosByDisciplina(Disciplina disciplina);
} 