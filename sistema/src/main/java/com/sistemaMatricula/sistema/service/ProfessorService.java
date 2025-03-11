package com.sistemaMatricula.sistema.service;

import com.sistemaMatricula.sistema.enums.EStatusMatricula;
import com.sistemaMatricula.sistema.model.Aluno;
import com.sistemaMatricula.sistema.model.Disciplina;
import com.sistemaMatricula.sistema.model.Matricula;
import com.sistemaMatricula.sistema.model.Professor;
import com.sistemaMatricula.sistema.repository.DisciplinaRepository;
import com.sistemaMatricula.sistema.repository.MatriculaRepository;
import com.sistemaMatricula.sistema.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço para gerenciar operações relacionadas a professores
 */
@Service
public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private MatriculaRepository matriculaRepository;
    
    /**
     * Busca um professor pelo ID
     * @param id O ID do professor
     * @return Um Optional contendo o professor, se encontrado
     */
    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }
    
    /**
     * Busca um professor pelo login
     * @param login O login do professor
     * @return Um Optional contendo o professor, se encontrado
     */
    public Optional<Professor> buscarPorLogin(String login) {
        return professorRepository.findByLogin(login);
    }
    
    /**
     * Lista todos os professores cadastrados
     * @return Uma lista de professores
     */
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }
    
    /**
     * Salva um professor no sistema
     * @param professor O professor a ser salvo
     * @return O professor salvo
     */
    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }
    
    /**
     * Remove um professor do sistema
     * @param id O ID do professor a ser removido
     */
    public void remover(Long id) {
        professorRepository.deleteById(id);
    }
    
    /**
     * Lista as disciplinas ministradas por um professor
     * @param professorId O ID do professor
     * @return Uma lista de disciplinas ministradas pelo professor
     */
    public List<Disciplina> listarDisciplinasMinistrando(Long professorId) {
        Optional<Professor> professorOpt = professorRepository.findById(professorId);
        
        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
            return disciplinaRepository.findByProfessorResponsavel(professor);
        }
        
        return List.of();
    }
    
    /**
     * Lista os alunos matriculados em uma disciplina ministrada por um professor
     * @param professorId O ID do professor
     * @param disciplinaId O ID da disciplina
     * @return Uma lista de alunos matriculados na disciplina, se o professor for o responsável por ela
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Aluno> listarAlunosPorDisciplina(Long professorId, Long disciplinaId) {
        Optional<Professor> professorOpt = professorRepository.findById(professorId);
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        
        if (professorOpt.isPresent() && disciplinaOpt.isPresent()) {
            Professor professor = professorOpt.get();
            Disciplina disciplina = disciplinaOpt.get();
            
            if (disciplina.getProfessorResponsavel().equals(professor)) {
                List<Matricula> matriculas = matriculaRepository.findByDisciplina(disciplina);
                
                return matriculas.stream()
                        .filter(m -> m.getStatus().equals(EStatusMatricula.EFETIVADA))
                        .map(Matricula::getAluno)
                        .collect(Collectors.toList());
            }
        }
        
        return List.of();
    }
} 