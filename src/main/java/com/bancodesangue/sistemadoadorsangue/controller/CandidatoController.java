package com.bancodesangue.sistemadoadorsangue.controller;

import com.bancodesangue.sistemadoadorsangue.dto.ResultadoDTO;
import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import com.bancodesangue.sistemadoadorsangue.service.CandidatoService;
import com.bancodesangue.sistemadoadorsangue.service.PDFService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    private final CandidatoService candidatoService;
    private final PDFService pdfService;
    private final ObjectMapper objectMapper; // Usado para conversão de JSON

    public CandidatoController(CandidatoService candidatoService, PDFService pdfService, ObjectMapper objectMapper) {
        this.candidatoService = candidatoService;
        this.pdfService = pdfService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<Candidato>> getAllCandidatos() {
        List<Candidato> candidatos = candidatoService.findAll();
        return ResponseEntity.ok(candidatos);
    }

    @PostMapping
    public ResponseEntity<Candidato> addCandidato(@RequestBody Candidato candidato) {
        Candidato novoCandidato = candidatoService.save(candidato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCandidato);
    }

    @PostMapping("/import")
    public ResponseEntity<Object> importCandidatos(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || file.getContentType() == null) {
            return ResponseEntity.badRequest().body("Arquivo inválido ou não é um PDF.");
        }

        if (!file.getContentType().equals("application/pdf")) {
            return ResponseEntity.badRequest().body("O arquivo não é um PDF.");
        }

        try {
            String jsonContent = pdfService.extractTextFromPdf(file);
            String sanitizedJson = jsonContent.replaceAll("\\r\\n|\\r|\\n", " ");
            List<Candidato> candidatos = objectMapper.readValue(sanitizedJson, new TypeReference<List<Candidato>>() {});

            candidatoService.saveAll(candidatos);
            ResultadoDTO resultado = candidatoService.processarCandidatos(candidatos);

            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Falha na validação dos dados: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro na leitura do arquivo PDF: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro no servidor: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> getCandidatoById(@PathVariable Long id) {
        Candidato candidato = candidatoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado"));
        return ResponseEntity.ok(candidato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> updateCandidato(@PathVariable Long id, @RequestBody Candidato candidatoDetails) {
        Candidato updatedCandidato = candidatoService.update(id, candidatoDetails);
        return ResponseEntity.ok(updatedCandidato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidato(@PathVariable Long id) {
        candidatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
