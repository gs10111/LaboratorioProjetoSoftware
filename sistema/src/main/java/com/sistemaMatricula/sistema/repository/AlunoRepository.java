package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Alunos
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
    /**
     * Busca um aluno pelo login
     * @param login O login do aluno
     * @return Um Optional contendo o aluno, se encontrado
     */
    Optional<Aluno> findByLogin(String login);
} 