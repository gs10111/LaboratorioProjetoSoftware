package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Cursos
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    
    /**
     * Busca um curso pelo nome
     * @param nome O nome do curso
     * @return Um Optional contendo o curso, se encontrado
     */
    Optional<Curso> findByNome(String nome);
    
    /**
     * Verifica se um curso com o nome fornecido existe
     * @param nome O nome a ser verificado
     * @return true se o curso existe, false caso contrário
     */
    boolean existsByNome(String nome);
} 