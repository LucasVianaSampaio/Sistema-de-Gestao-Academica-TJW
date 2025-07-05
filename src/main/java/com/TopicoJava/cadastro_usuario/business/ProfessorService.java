package com.TopicoJava.cadastro_usuario.business;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Professor;
import com.TopicoJava.cadastro_usuario.infrastructure.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public void salvarProfessor(Professor professor) {
        repository.saveAndFlush(professor);
    }

    public Professor buscarPorCpf(String cpf) {
        Professor professor = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));

        if (!professor.getAtivo()) {
            throw new RuntimeException("Professor inativo");
        }
        return professor;
    }

    public Professor buscarPorId(Long id) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (!professor.getAtivo()) {
            throw new RuntimeException("Professor inativo");
        }
        return professor;
    }

    public void atualizarProfessorPorId(Long id, Professor professorAtualizado) {
        Professor professorExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Professor professor = Professor.builder()
                .id(professorExistente.getId())
                .cpf(professorExistente.getCpf()) // CPF não deve ser alterado
                .nome(professorAtualizado.getNome() != null ? professorAtualizado.getNome() : professorExistente.getNome())
                .email(professorAtualizado.getEmail() != null ? professorAtualizado.getEmail() : professorExistente.getEmail())
                .areaAtuacao(professorAtualizado.getAreaAtuacao() != null ? professorAtualizado.getAreaAtuacao() : professorExistente.getAreaAtuacao())
                .formacao(professorAtualizado.getFormacao() != null ? professorAtualizado.getFormacao() : professorExistente.getFormacao())
                .ativo(professorExistente.getAtivo())
                .build();

        repository.saveAndFlush(professor);
    }

    public void deletarProfessorPorCpf(String cpf) {
        Professor professor = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Professor com CPF não encontrado"));
        professor.setAtivo(false);
        repository.saveAndFlush(professor);
    }

    public List<Professor> listarProfessoresAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<Professor> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Professor> buscarPorAreaAtuacao(String area) {
        return repository.findByAreaAtuacaoContainingIgnoreCase(area);
    }
}
