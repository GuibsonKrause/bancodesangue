package com.bancodesangue.sistemadoadorsangue.controller;

import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import com.bancodesangue.sistemadoadorsangue.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    private final CandidatoService candidatoService;

    @Autowired
    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping
    public ResponseEntity<List<Candidato>> getAllCandidatos() {
        List<Candidato> candidatos = candidatoService.findAll();
        return ResponseEntity.ok(candidatos);
    }

    // Outros endpoints ...
}
