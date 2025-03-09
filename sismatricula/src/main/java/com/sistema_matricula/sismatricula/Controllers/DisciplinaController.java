package com.sistema_matricula.sismatricula.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sistema_matricula.sismatricula.Models.Disciplina;
import com.sistema_matricula.sismatricula.Services.DisciplinaService;

/**
 * Controller para gerenciar endpoints relacionados a Disciplina.
 */
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    /**
     * Retorna todas as disciplinas.
     * @return lista de disciplinas
     */
    @GetMapping
    public List<Disciplina> listarTodas() {
        return disciplinaService.listarTodas();
    }

    /**
     * Salva uma nova disciplina.
     * @param disciplina objeto disciplina a ser salvo
     * @return disciplina salva
     */
    @PostMapping
    public Disciplina salvar(@RequestBody Disciplina disciplina) {
        return disciplinaService.salvar(disciplina);
    }

    /**
     * Busca uma disciplina por ID.
     * @param id identificador da disciplina
     * @return disciplina encontrada ou null
     */
    @GetMapping("/{id}")
    public Disciplina buscarPorId(@PathVariable Long id) {
        return disciplinaService.buscarPorId(id);
    }

    /**
     * Deleta uma disciplina por ID.
     * @param id identificador da disciplina
     */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        disciplinaService.deletar(id);
    }
    
    /**
     * Ativa uma disciplina.
     * @param id identificador da disciplina
     * @return disciplina ativada
     */
    @PutMapping("/{id}/ativar")
    public Disciplina ativar(@PathVariable Long id) {
        return disciplinaService.ativar(id);
    }
    
    /**
     * Cancela uma disciplina.
     * @param id identificador da disciplina
     * @return disciplina cancelada
     */
    @PutMapping("/{id}/cancelar")
    public Disciplina cancelar(@PathVariable Long id) {
        return disciplinaService.cancelar(id);
    }
} 