package br.com.duxusdesafio.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;

@RestController
@RequestMapping("/api/dados")
public class DadosController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private IntegranteRepository integranteRepository;

    @GetMapping("/times")
    public ResponseEntity<List<Time>> obterTimes() {
        List<Time> todosOsTimes = timeRepository.findAll();
        return new ResponseEntity<>(todosOsTimes, HttpStatus.OK);
    }

    @GetMapping("/integrantes")
    public ResponseEntity<List<Integrante>> obterIntegrantes() {
        List<Integrante> todosOsIntegrantes = integranteRepository.findAll();
        return new ResponseEntity<>(todosOsIntegrantes, HttpStatus.OK);
    }
}
