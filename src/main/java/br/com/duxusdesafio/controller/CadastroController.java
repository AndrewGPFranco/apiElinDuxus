package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dtos.IntegranteDto;
import br.com.duxusdesafio.dtos.TimeDto;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastro")
public class CadastroController {

    @Autowired
    private IntegranteRepository integranteRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ComposicaoTimeRepository composicaoTimeRepository;

    @PostMapping("/integrantes")
    public ResponseEntity<?> cadastrarIntegrante(@RequestBody IntegranteDto dto) {
        try {
            Integrante integrante = new Integrante();
            integrante.setNome(dto.getNome());
            integrante.setFranquia(dto.getFranquia());
            integrante.setFuncao(dto.getFuncao());

            integranteRepository.save(integrante);

            return ResponseEntity.ok(integrante);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar, nome já cadastrado!");
        }
    }

    @SuppressWarnings("null")
    @PostMapping("/times")
    public ResponseEntity<Time> cadastrarTime(@RequestBody TimeDto dto) {
        Time time = new Time();
        time.setData(dto.getData());
        time.setNome(dto.getNome());

        timeRepository.save(time);

        for (Long integranteId : dto.getIntegrantesIds()) {
            ComposicaoTime composicaoTime = new ComposicaoTime();
            composicaoTime.setTime(time);
            composicaoTime.setIntegrante(integranteRepository.findById(integranteId).get());

            composicaoTimeRepository.save(composicaoTime);
        }

        return ResponseEntity.ok(time);
    }
}