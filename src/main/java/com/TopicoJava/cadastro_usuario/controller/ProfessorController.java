package com.TopicoJava.cadastro_usuario.controller;

import com.TopicoJava.cadastro_usuario.business.ProfessorService;
import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Void> salvarProfessor(@RequestBody Professor professor) {
        professorService.salvarProfessor(professor);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessoresAtivos() {
        return ResponseEntity.ok(professorService.listarProfessoresAtivos());
    }

    @GetMapping("/cpf")
    public ResponseEntity<Professor> buscarPorCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(professorService.buscarPorCpf(cpf));
    }

    @GetMapping("/id")
    public ResponseEntity<Professor> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(professorService.buscarPorId(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Professor>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(professorService.buscarPorNome(nome));
    }

    @GetMapping("/area")
    public ResponseEntity<List<Professor>> buscarPorAreaAtuacao(@RequestParam String areaAtuacao) {
        return ResponseEntity.ok(professorService.buscarPorAreaAtuacao(areaAtuacao));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarProfessorPorId(@RequestParam Long id,
                                                        @RequestBody Professor professor) {
        professorService.atualizarProfessorPorId(id, professor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarProfessorPorCpf(@RequestParam String cpf) {
        professorService.deletarProfessorPorCpf(cpf);
        return ResponseEntity.ok().build();
    }
}
