package com.sistema_matricula.sismatricula.Interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema_matricula.sismatricula.Models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>
{
    // Busca um professor pelo SIAPE
    Optional<Professor> findBySiape(String siape);
    
    // Busca um professor pelo email
    Optional<Professor> findByEmail(String email);
}
