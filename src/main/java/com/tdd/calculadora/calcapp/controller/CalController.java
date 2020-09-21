package com.tdd.calculadora.calcapp.controller;

import com.tdd.calculadora.calcapp.DTO.calcDTO;
import com.tdd.calculadora.calcapp.services.CalculatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalController {
    @Autowired
    private CalculatorService calcSvc;

    @GetMapping("/poweron")
    public ResponseEntity powerOn() {
        return ResponseEntity.ok("powerOn");
    }

    @PostMapping("/sumar")
    public ResponseEntity<calcDTO> sumar(@RequestBody calcDTO dto) {
        Integer res= calcSvc.sumaInt(dto.getNum1(), dto.getNum2());
        // Integer res = calcSvc.sumaInt(1, 2);
        dto.setRes(res);
        return ResponseEntity.ok().body(dto);

    }
}
