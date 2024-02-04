package br.com.duxusdesafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.duxusdesafio.model.Time;

import br.com.duxusdesafio.repository.TimeRepository;

@RestController
@RequestMapping("/api/dados")
public class DadosController {
    
    @Autowired
    private TimeRepository timeRepository;
    
    @GetMapping("/times")
    public ResponseEntity<List<Time>> obterTimes() {
        var todosTimes = timeRepository.findAll();
        return new ResponseEntity<>(todosTimes, HttpStatus.OK);
    }
}
