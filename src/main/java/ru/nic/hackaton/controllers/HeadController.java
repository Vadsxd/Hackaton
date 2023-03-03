package ru.nic.hackaton.controllers;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nic.hackaton.requests.CalculateRequest;
import ru.nic.hackaton.responses.ResultResponse;
import ru.nic.hackaton.services.HeadService;

@Api(tags = "Главный контроллер")
@RestController
@RequestMapping("/api")
public class HeadController {
    public final HeadService headService;

    @Autowired
    public HeadController(HeadService headService) {
        this.headService = headService;
    }

    @PostMapping("/result")
    public ResponseEntity<ResultResponse> calculate(@Valid @RequestBody CalculateRequest request) {
        return ResponseEntity.ok(headService.calculate(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello Tester!");
    }
}
