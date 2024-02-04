package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/times")
public class TimeController {
    private ApiService apiService;
    private TimeRepository timeRepository;

    @Autowired
    public TimeController(ApiService apiService, TimeRepository timeRepository) {
        this.apiService = apiService;
        this.timeRepository = timeRepository;
    }

    @GetMapping("/integrantes")
    public ResponseEntity<List<String>> obterIntegrantesNaData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ){

        List<Time> todosOsTimes = timeRepository.findAll();
        List<String> integrantesDoTime = apiService.timeDaData(data, todosOsTimes);

        return new ResponseEntity<>(integrantesDoTime, HttpStatus.OK);
    }

    @GetMapping("/integrantemaisusado")
    public ResponseEntity<Integrante> obterIntegranteMaisUsado(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        List<Time> todosOsTimes = timeRepository.findAll();
        Integrante integranteMaisUsado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

        return new ResponseEntity<>(integranteMaisUsado, HttpStatus.OK);
    }


}