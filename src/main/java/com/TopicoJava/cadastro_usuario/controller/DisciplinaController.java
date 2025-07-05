package com.TopicoJava.cadastro_usuario.controller;

import com.TopicoJava.cadastro_usuario.business.DisciplinaService;
import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Disciplina;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Void> salvarDisciplina(@RequestBody Disciplina disciplina) {
        disciplinaService.salvarDisciplina(disciplina);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarAtivas() {
        return ResponseEntity.ok(disciplinaService.listarDisciplinasAtivas());
    }

    @GetMapping("/codigo")
    public ResponseEntity<Disciplina> buscarPorCodigo(@RequestParam String codigo) {
        return ResponseEntity.ok(disciplinaService.buscarPorCodigo(codigo));
    }

    @GetMapping("/id")
    public ResponseEntity<Disciplina> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(disciplinaService.buscarPorId(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Disciplina>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(disciplinaService.buscarPorNome(nome));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarPorId(@RequestParam Long id, @RequestBody Disciplina disciplina) {
        disciplinaService.atualizarDisciplinaPorId(id, disciplina);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarPorCodigo(@RequestParam String codigo) {
        disciplinaService.deletarPorCodigo(codigo);
        return ResponseEntity.ok().build();
    }
}
