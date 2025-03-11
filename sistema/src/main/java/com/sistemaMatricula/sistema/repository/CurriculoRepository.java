package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.model.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Currículos
 */
@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
    
    /**
     * Busca um currículo pelo nome
     * @param nome O nome do currículo
     * @return Um Optional contendo o currículo, se encontrado
     */
    Optional<Curriculo> findByNome(String nome);
    
    /**
     * Verifica se um currículo com o nome fornecido existe
     * @param nome O nome a ser verificado
     * @return true se o currículo existe, false caso contrário
     */
    boolean existsByNome(String nome);
} 