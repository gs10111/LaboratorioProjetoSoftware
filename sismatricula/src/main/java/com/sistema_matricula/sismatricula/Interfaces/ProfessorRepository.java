package com.sistema_matricula.sismatricula.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sistema_matricula.sismatricula.Models.Professor;

/**
 * Repositório para a entidade Professor.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Métodos de consulta customizados, se necessário
} 