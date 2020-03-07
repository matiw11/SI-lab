package com.mwolczecki.backend.controller;

import com.mwolczecki.backend.platform.Evaluation;
import com.mwolczecki.backend.platform.TSPParser;
import com.mwolczecki.backend.platform.TSPProblem;
import com.mwolczecki.backend.platform.TSPProblemFactory;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/")
public class Controller {

    @PostMapping("/runProblem")
    public List<Evaluation> runProblem(@RequestBody Map<String, String> map){
        TSPProblem build = TSPProblemFactory.build(map);
        List<Evaluation> evaluations = build.run();
        return evaluations;
    }

    @GetMapping("/getFileNames")
    public List<String> getFileNames(){
        URL resourceAsStream = TSPParser.class.getClassLoader().getResource("dane/TSP");
        try {
            File file = Paths.get(resourceAsStream.toURI()).toFile();
            return Arrays.asList(file.listFiles()).stream().map(file1 -> file1.getName().replace(".tsp","")).collect(Collectors.toList());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
