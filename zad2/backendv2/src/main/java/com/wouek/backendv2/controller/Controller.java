package com.wouek.backendv2.controller;

import com.wouek.backendv2.CSPProblem;
import com.wouek.backendv2.ValuePicker.NextValuePicker;
import com.wouek.backendv2.ValuePicker.RandomValuePicker;
import com.wouek.backendv2.ValuePicker.ValuePicker;
import com.wouek.backendv2.domain.Result;
import com.wouek.backendv2.variablePicker.NextVariablePicker;
import com.wouek.backendv2.variablePicker.RandomVariablePicker;
import com.wouek.backendv2.variablePicker.VariablePicker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    @RequestMapping("/solve/{id}")
    public Result solve(@PathVariable String id, @RequestBody Map<String, String> body){
        int intId = Integer.valueOf(id);
        VariablePicker variablePicker = null;
        if(body.get("variable").equals("random")){
            variablePicker = new RandomVariablePicker();
        }
        else{
            variablePicker = new NextVariablePicker();
        }
        ValuePicker valuePicker = null;
        if(body.get("value").equals("random")){
            valuePicker = new RandomValuePicker();
        }
        else{
            valuePicker = new NextValuePicker();
        }

        CSPProblem cspProblem = new CSPProblem(intId, variablePicker, valuePicker);
        Result solve = cspProblem.solve();
        return solve;
    }

    @RequestMapping("/peek/{id}")
    public int[][] peek (@PathVariable String id){
        CSPProblem cspProblem = new CSPProblem(Integer.valueOf(id));
        int[][] variableValues = cspProblem.cspVariables.getVariableValues();
        return variableValues;
    }
}
