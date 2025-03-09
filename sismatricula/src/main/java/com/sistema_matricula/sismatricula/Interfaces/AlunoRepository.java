package com.sistema_matricula.sismatricula.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sistema_matricula.sismatricula.Models.Aluno;

/**
 * Repositório para a entidade Aluno.
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Métodos de consulta customizados, se necessário
} 