package com.bancodesangue.sistemadoadorsangue.controller;

import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import com.bancodesangue.sistemadoadorsangue.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    private final CandidatoService candidatoService;

    @Autowired
    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    // Já implementado - listar todos os candidatos
    @GetMapping
    public ResponseEntity<List<Candidato>> getAllCandidatos() {
        List<Candidato> candidatos = candidatoService.findAll();
        return ResponseEntity.ok(candidatos);
    }

    // Adicionar um novo candidato
    @PostMapping
    public ResponseEntity<Candidato> addCandidato(@RequestBody Candidato candidato) {
        Candidato novoCandidato = candidatoService.save(candidato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCandidato);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importCandidatos(@RequestBody List<Candidato> candidatos) {
        try {
            candidatoService.saveAll(candidatos);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            // Lógica para tratar violações de integridade, como entradas duplicadas
            return ResponseEntity.badRequest().body("Falha na validação dos dados.");
        } catch (Exception e) {
            // Lógica para tratar outras exceções
            return ResponseEntity.internalServerError().build();
        }
    }


    // Buscar candidato pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidato> getCandidatoById(@PathVariable Long id) {
        Candidato candidato = candidatoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado"));
        return ResponseEntity.ok(candidato);
    }

    // Atualizar um candidato existente
    @PutMapping("/{id}")
    public ResponseEntity<Candidato> updateCandidato(@PathVariable Long id, @RequestBody Candidato candidatoDetails) {
        Candidato updatedCandidato = candidatoService.update(id, candidatoDetails);
        return ResponseEntity.ok(updatedCandidato);
    }

    // Deletar um candidato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidato(@PathVariable Long id) {
        candidatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Outros métodos conforme necessário...

    // A lógica destes métodos deve ser implementada no CandidatoService.
    // Lembre-se de tratar exceções apropriadamente, possivelmente usando um @ControllerAdvice para manipulação global de erros.
}
