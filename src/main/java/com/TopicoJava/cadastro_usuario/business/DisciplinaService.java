package com.TopicoJava.cadastro_usuario.business;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Disciplina;
import com.TopicoJava.cadastro_usuario.infrastructure.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public void salvarDisciplina(Disciplina disciplina) {
        repository.saveAndFlush(disciplina);
    }

    public Disciplina buscarPorCodigo(String codigo) {
        Disciplina disciplina = repository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Código de disciplina não encontrado"));
        if (!disciplina.getAtivo()) {
            throw new RuntimeException("Disciplina inexistente");
        }
        return disciplina;
    }

    public Disciplina buscarPorId(Long id) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        if (!disciplina.getAtivo()) {
            throw new RuntimeException("Disciplina inexistente");
        }
        return disciplina;
    }

    public void atualizarDisciplinaPorId(Long id, Disciplina atualizada) {
        Disciplina existente = buscarPorId(id);

        Disciplina nova = Disciplina.builder()
                .id(existente.getId())
                .codigo(existente.getCodigo())
                .nome(atualizada.getNome() != null ? atualizada.getNome() : existente.getNome())
                .cargaHoraria(atualizada.getCargaHoraria() != null ? atualizada.getCargaHoraria() : existente.getCargaHoraria())
                .ementa(atualizada.getEmenta() != null ? atualizada.getEmenta() : existente.getEmenta())
                .ativo(existente.getAtivo())
                .build();

        repository.saveAndFlush(nova);
    }

    public void deletarPorCodigo(String codigo) {
        Disciplina disciplina = buscarPorCodigo(codigo);
        disciplina.setAtivo(false);
        repository.saveAndFlush(disciplina);
    }

    public List<Disciplina> listarDisciplinasAtivas() {
        return repository.findByAtivoTrue();
    }

    public List<Disciplina> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
