package com.TopicoJava.cadastro_usuario.business;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Aluno;
import com.TopicoJava.cadastro_usuario.infrastructure.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public void salvarAluno(Aluno aluno) {
        repository.saveAndFlush(aluno);
    }

    public Aluno buscarPorMatricula(String matricula) {
        Aluno aluno = repository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        if (!aluno.getAtivo()) {
            throw new RuntimeException("Aluno inativo");
        }
        return aluno;
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public void atualizarAlunoPorId(Long id, Aluno novoAluno) {
        Aluno alunoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Aluno alunoAtualizado = Aluno.builder()
                .id(alunoExistente.getId())
                .matricula(alunoExistente.getMatricula()) // matrícula não pode mudar
                .email(novoAluno.getEmail() != null ? novoAluno.getEmail() : alunoExistente.getEmail())
                .nome(novoAluno.getNome() != null ? novoAluno.getNome() : alunoExistente.getNome())
                .dataNascimento(novoAluno.getDataNascimento() != null ? novoAluno.getDataNascimento() : alunoExistente.getDataNascimento())
                .telefone(novoAluno.getTelefone() != null ? novoAluno.getTelefone() : alunoExistente.getTelefone())
                .endereco(novoAluno.getEndereco() != null ? novoAluno.getEndereco() : alunoExistente.getEndereco())
                .ativo(alunoExistente.getAtivo())
                .build();

        repository.saveAndFlush(alunoAtualizado);
    }

    public void deletarAlunoPorMatricula(String matricula) {
        Aluno aluno = repository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        aluno.setAtivo(false);
        repository.saveAndFlush(aluno);
    }

    public List<Aluno> listarAlunosAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<Aluno> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
