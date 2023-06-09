package com.backend.controller;

import com.backend.entity.Moto;
import com.backend.exception.InfoException;
import com.backend.service.MotoTests.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moto")
@RequiredArgsConstructor
@CrossOrigin()
public class MotoController {

    private final MotoService motoService;

    @GetMapping("/listar")
    public List<Moto> buscarTodos() {
        return motoService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    public Moto inserir(@RequestBody Moto moto) throws InfoException {
        return motoService.inserir(moto);
    }

    @PutMapping("/atualizar/{id}")
    public Moto alterar(@PathVariable("id") Long id, @RequestBody Moto moto) throws InfoException {
        return motoService.alterar(id, moto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        motoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
