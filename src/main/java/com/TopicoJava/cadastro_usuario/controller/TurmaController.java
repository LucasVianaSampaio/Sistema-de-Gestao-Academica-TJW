package com.TopicoJava.cadastro_usuario.controller;

import com.TopicoJava.cadastro_usuario.business.TurmaService;
import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Turma;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Turma turma) {
        turmaService.salvarTurma(turma);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listarAtivas() {
        return ResponseEntity.ok(turmaService.listarTurmasAtivas());
    }

    @GetMapping("/codigo")
    public ResponseEntity<Turma> buscarPorCodigo(@RequestParam String codigo) {
        return ResponseEntity.ok(turmaService.buscarPorCodigo(codigo));
    }

    @GetMapping("/id")
    public ResponseEntity<Turma> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(turmaService.buscarPorId(id));
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestParam Long id, @RequestBody Turma turma) {
        turmaService.atualizarTurma(id, turma);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestParam String codigo) {
        turmaService.deletarTurmaPorCodigo(codigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/disciplina")
    public ResponseEntity<List<Turma>> buscarPorDisciplina(@RequestParam Long disciplinaId) {
        return ResponseEntity.ok(turmaService.buscarPorDisciplina(disciplinaId));
    }

    @GetMapping("/professor")
    public ResponseEntity<List<Turma>> buscarPorProfessor(@RequestParam Long professorId) {
        return ResponseEntity.ok(turmaService.buscarPorProfessor(professorId));
    }

    @GetMapping("/semestre")
    public ResponseEntity<List<Turma>> buscarPorSemestreEAno(@RequestParam String semestre,
                                                             @RequestParam Integer ano) {
        return ResponseEntity.ok(turmaService.buscarPorSemestreEAno(semestre, ano));
    }

    @GetMapping("/vagas")
    public ResponseEntity<List<Turma>> turmasComVagasDisponiveis() {
        return ResponseEntity.ok(turmaService.buscarTurmasComVagasDisponiveis());
    }
}
