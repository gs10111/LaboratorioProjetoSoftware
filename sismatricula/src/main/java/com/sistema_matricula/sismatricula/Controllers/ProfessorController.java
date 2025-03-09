package com.sistema_matricula.sismatricula.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sistema_matricula.sismatricula.Models.Professor;
import com.sistema_matricula.sismatricula.Services.ProfessorService;

/**
 * Controller para gerenciar endpoints relacionados a Professor.
 */
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    /**
     * Retorna todos os professores.
     * @return lista de professores
     */
    @GetMapping
    public List<Professor> listarTodos() {
        return professorService.listarTodos();
    }

    /**
     * Salva um novo professor.
     * @param professor objeto professor a ser salvo
     * @return professor salvo
     */
    @PostMapping
    public Professor salvar(@RequestBody Professor professor) {
        return professorService.salvar(professor);
    }

    /**
     * Busca um professor por ID.
     * @param id identificador do professor
     * @return professor encontrado ou null
     */
    @GetMapping("/{id}")
    public Professor buscarPorId(@PathVariable Long id) {
        return professorService.buscarPorId(id);
    }

    /**
     * Deleta um professor por ID.
     * @param id identificador do professor
     */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        professorService.deletar(id);
    }
} 