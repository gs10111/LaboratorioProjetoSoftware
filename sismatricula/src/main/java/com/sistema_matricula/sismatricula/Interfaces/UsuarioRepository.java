package com.sistema_matricula.sismatricula.Interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema_matricula.sismatricula.Models.UsuarioBase;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioBase, Long> {

    Optional<UsuarioBase> findByLogin(String login);
}
