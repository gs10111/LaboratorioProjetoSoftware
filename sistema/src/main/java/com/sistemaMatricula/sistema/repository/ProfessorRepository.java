package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Professores
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
    /**
     * Busca um professor pelo login
     * @param login O login do professor
     * @return Um Optional contendo o professor, se encontrado
     */
    Optional<Professor> findByLogin(String login);
} 