package com.sistema_matricula.sismatricula.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sistema_matricula.sismatricula.Models.Disciplina;

/**
 * Repositório para a entidade Disciplina.
 */
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    // Métodos de consulta customizados, se necessário
} 