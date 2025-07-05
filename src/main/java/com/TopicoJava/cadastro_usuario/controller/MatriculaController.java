package com.TopicoJava.cadastro_usuario.controller;

import com.TopicoJava.cadastro_usuario.business.MatriculaService;
import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Matricula;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<Void> realizarMatricula(@RequestBody Matricula matricula) {
        matriculaService.realizarMatricula(matricula);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarMatricula(@PathVariable Long id, @RequestBody Matricula matricula) {
        matriculaService.atualizarMatricula(id, matricula);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarMatricula(@PathVariable Long id) {
        matriculaService.cancelarMatricula(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aluno")
    public ResponseEntity<List<Matricula>> buscarPorAluno(@RequestParam Long alunoId) {
        return ResponseEntity.ok(matriculaService.listarPorAluno(alunoId));
    }

    @GetMapping("/turma")
    public ResponseEntity<List<Matricula>> buscarPorTurma(@RequestParam Long turmaId) {
        return ResponseEntity.ok(matriculaService.listarPorTurma(turmaId));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Matricula>> buscarPorStatus(@RequestParam String status) {
        Matricula.StatusMatricula statusEnum = Matricula.StatusMatricula.valueOf(status.toUpperCase());
        return ResponseEntity.ok(matriculaService.listarPorStatus(statusEnum));
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> listarTodas() {
        return ResponseEntity.ok(matriculaService.listarTodas());
    }
}
