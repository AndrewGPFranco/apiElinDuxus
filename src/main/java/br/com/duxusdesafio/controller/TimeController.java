package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Time;
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
    private

    @Autowired
    public TimeController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/integrantes")
    public ResponseEntity<List<String>> obterIntegrantesNaData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ){

        List<Time> todosOsTimes =
        List<String> integrantesDoTime = apiService.timeDaData(data, todosOsTimes);

        return new ResponseEntity<>(integrantesDoTime, HttpStatus.OK);
    }
}