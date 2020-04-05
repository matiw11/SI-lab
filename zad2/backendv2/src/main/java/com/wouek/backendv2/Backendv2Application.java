package com.wouek.backendv2;

import com.wouek.backendv2.domain.CSPVariables;
import com.wouek.backendv2.domain.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Backendv2Application {

    public static void main(String[] args) {
        //SpringApplication.run(Backendv2Application.class, args);

        CSPProblem cspProblem = new CSPProblem(1);
        System.out.println("A'");
        Result solve = cspProblem.solve();
        System.out.println(solve);
        /*for(CSPVariables cspVariables: solve){
           cspVariables.print();
        }*/
    }

}
