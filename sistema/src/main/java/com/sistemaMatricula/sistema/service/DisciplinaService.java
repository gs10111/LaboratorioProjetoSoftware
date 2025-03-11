package com.sistemaMatricula.sistema.service;

import com.sistemaMatricula.sistema.enums.EStatusDisciplina;
import com.sistemaMatricula.sistema.enums.EStatusMatricula;
import com.sistemaMatricula.sistema.model.Disciplina;
import com.sistemaMatricula.sistema.model.Matricula;
import com.sistemaMatricula.sistema.repository.DisciplinaRepository;
import com.sistemaMatricula.sistema.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações relacionadas a disciplinas
 */
@Service
public class DisciplinaService {
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private MatriculaRepository matriculaRepository;
    
    /**
     * Busca uma disciplina pelo ID
     * @param id O ID da disciplina
     * @return Um Optional contendo a disciplina, se encontrada
     */
    public Optional<Disciplina> buscarPorId(Long id) {
        return disciplinaRepository.findById(id);
    }
    
    /**
     * Lista todas as disciplinas cadastradas
     * @return Uma lista de disciplinas
     */
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }
    
    /**
     * Lista todas as disciplinas ativas
     * @return Uma lista de disciplinas ativas
     */
    public List<Disciplina> listarAtivas() {
        return disciplinaRepository.findByStatus(EStatusDisciplina.ATIVA);
    }
    
    /**
     * Lista todas as disciplinas canceladas
     * @return Uma lista de disciplinas canceladas
     */
    public List<Disciplina> listarCanceladas() {
        return disciplinaRepository.findByStatus(EStatusDisciplina.CANCELADA);
    }
    
    /**
     * Salva uma disciplina no sistema
     * @param disciplina A disciplina a ser salva
     * @return A disciplina salva
     */
    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }
    
    /**
     * Remove uma disciplina do sistema
     * @param id O ID da disciplina a ser removida
     */
    public void remover(Long id) {
        disciplinaRepository.deleteById(id);
    }
    
    /**
     * Atualiza o status de todas as disciplinas com base no número de alunos matriculados
     * Disciplinas com menos de 3 alunos serão canceladas, as demais serão ativadas
     * @return O número de disciplinas atualizadas
     */
    @Transactional
    public int atualizarStatusDisciplinas() {
        int contador = 0;
        
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        
        for (Disciplina disciplina : disciplinas) {
            List<Matricula> matriculas = matriculaRepository.findByDisciplina(disciplina);
            long alunosMatriculados = matriculas.stream()
                    .filter(m -> m.getStatus().equals(EStatusMatricula.EFETIVADA))
                    .count();
            
            if (alunosMatriculados < Disciplina.NUMERO_MIN_ALUNOS) {
                if (!disciplina.getStatus().equals(EStatusDisciplina.CANCELADA)) {
                    disciplina.setStatus(EStatusDisciplina.CANCELADA);
                    disciplinaRepository.save(disciplina);
                    contador++;
                }
            } else {
                if (!disciplina.getStatus().equals(EStatusDisciplina.ATIVA)) {
                    disciplina.setStatus(EStatusDisciplina.ATIVA);
                    disciplinaRepository.save(disciplina);
                    contador++;
                }
            }
        }
        
        return contador;
    }
    
    /**
     * Verifica se uma disciplina tem pelo menos o número mínimo de alunos matriculados
     * @param disciplinaId O ID da disciplina
     * @return true se a disciplina tem pelo menos o número mínimo de alunos, false caso contrário
     */
    public boolean verificarNumeroMinimoAlunos(Long disciplinaId) {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            
            List<Matricula> matriculas = matriculaRepository.findByDisciplina(disciplina);
            long alunosMatriculados = matriculas.stream()
                    .filter(m -> m.getStatus().equals(EStatusMatricula.EFETIVADA))
                    .count();
            
            return alunosMatriculados >= Disciplina.NUMERO_MIN_ALUNOS;
        }
        
        return false;
    }
    
    /**
     * Verifica se uma disciplina está lotada (com o número máximo de alunos)
     * @param disciplinaId O ID da disciplina
     * @return true se a disciplina está lotada, false caso contrário
     */
    public boolean verificarDisciplinaLotada(Long disciplinaId) {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            
            List<Matricula> matriculas = matriculaRepository.findByDisciplina(disciplina);
            long alunosMatriculados = matriculas.stream()
                    .filter(m -> m.getStatus().equals(EStatusMatricula.EFETIVADA))
                    .count();
            
            return alunosMatriculados >= Disciplina.NUMERO_MAX_ALUNOS;
        }
        
        return false;
    }
} 