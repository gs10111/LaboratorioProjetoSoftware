package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.enums.EStatusDisciplina;
import com.sistemaMatricula.sistema.enums.ETipoDisciplina;
import com.sistemaMatricula.sistema.model.Curso;
import com.sistemaMatricula.sistema.model.Disciplina;
import com.sistemaMatricula.sistema.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositório para operações de CRUD de Disciplinas
 */
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    
    /**
     * Busca todas as disciplinas de um curso
     * @param curso O curso
     * @return Uma lista de disciplinas do curso
     */
    List<Disciplina> findByCurso(Curso curso);
    
    /**
     * Busca todas as disciplinas de um professor
     * @param professor O professor
     * @return Uma lista de disciplinas ministradas pelo professor
     */
    List<Disciplina> findByProfessorResponsavel(Professor professor);
    
    /**
     * Busca todas as disciplinas por tipo
     * @param tipo O tipo da disciplina (obrigatória ou optativa)
     * @return Uma lista de disciplinas do tipo especificado
     */
    List<Disciplina> findByTipo(ETipoDisciplina tipo);
    
    /**
     * Busca todas as disciplinas por status
     * @param status O status da disciplina (ativa ou cancelada)
     * @return Uma lista de disciplinas com o status especificado
     */
    List<Disciplina> findByStatus(EStatusDisciplina status);
    
    /**
     * Busca todas as disciplinas com menos de 3 alunos matriculados
     * @return Uma lista de disciplinas com menos de 3 alunos
     */
    @Query("SELECT d FROM Disciplina d JOIN d.alunosMatriculados am GROUP BY d HAVING COUNT(am) < 3")
    List<Disciplina> findDisciplinasComMenosTresAlunos();
    
    /**
     * Busca todas as disciplinas lotadas (com 60 alunos matriculados)
     * @return Uma lista de disciplinas lotadas
     */
    @Query("SELECT d FROM Disciplina d JOIN d.alunosMatriculados am GROUP BY d HAVING COUNT(am) >= 60")
    List<Disciplina> findDisciplinasLotadas();
} 