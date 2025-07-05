package com.TopicoJava.cadastro_usuario.controller;

import com.TopicoJava.cadastro_usuario.business.AlunoService;
import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Aluno;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Void> salvarAluno(@RequestBody Aluno aluno) {
        alunoService.salvarAluno(aluno);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunosAtivos() {
        return ResponseEntity.ok(alunoService.listarAlunosAtivos());
    }

    @GetMapping("/matricula")
    public ResponseEntity<Aluno> buscarPorMatricula(@RequestParam String matricula) {
        return ResponseEntity.ok(alunoService.buscarPorMatricula(matricula));
    }

    @GetMapping("/id")
    public ResponseEntity<Aluno> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Aluno>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(alunoService.buscarPorNome(nome));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarAlunoPorId(@RequestParam Long id,
                                                    @RequestBody Aluno aluno) {
        alunoService.atualizarAlunoPorId(id, aluno);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAlunoPorMatricula(@RequestParam String matricula) {
        alunoService.deletarAlunoPorMatricula(matricula);
        return ResponseEntity.ok().build();
    }
}
