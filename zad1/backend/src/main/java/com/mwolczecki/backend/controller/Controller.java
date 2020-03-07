package com.mwolczecki.backend.controller;

import com.mwolczecki.backend.platform.Evaluation;
import com.mwolczecki.backend.platform.TSPProblem;
import com.mwolczecki.backend.platform.TSPProblemFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {

    @PostMapping("/runProblem")
    public List<Evaluation> runProblem(@RequestBody Map<String, String> map){
        TSPProblem build = TSPProblemFactory.build(map);
        List<Evaluation> evaluations = build.run();
        return evaluations;
    }
}
