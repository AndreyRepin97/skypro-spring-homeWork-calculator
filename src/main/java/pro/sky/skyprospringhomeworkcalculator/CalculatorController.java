package pro.sky.skyprospringhomeworkcalculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("/calculator")
@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping()
    public String meets(){
        return calculatorService.meets();
    }
    @GetMapping("/plus")
    public String plus(@RequestParam(value = "num1", required = false) Float a,
                       @RequestParam(value = "num2", required = false) Float b){
        if(Objects.isNull(a)||Objects.isNull(b)){
            return "ne pravilnie parametri";
        }
        return buildStr(a,b,calculatorService.plus(a,b),"+");
    }
    @GetMapping("/minus")
    public String minus(@RequestParam(value = "num1", required = false) Float a,
                        @RequestParam(value = "num2", required = false) Float b){
        if(Objects.isNull(a)||Objects.isNull(b)){
            return "ne pravilnie parametri";
        }
        return buildStr(a,b,calculatorService.minus(a,b),"-");
    }
    @GetMapping("/multiply")
    public String multiply(@RequestParam(value = "num1", required = false) Float a,
                           @RequestParam(value = "num2", required = false) Float b){
        if(Objects.isNull(a)||Objects.isNull(b)){
            return "ne pravilnie parametri";
        }
        return buildStr(a,b,calculatorService.multiply(a,b),"*");
    }
    @GetMapping("/divide")
    public String divide(@RequestParam(value = "num1", required = false) Float a,
                         @RequestParam(value = "num2", required = false) Float b){
        if(Objects.isNull(a)||Objects.isNull(b)){
            return "ne pravilnie parametri";
        }
        if(b==0) {
            return "delit nelzya";
        }else{
            return buildStr(a,b,calculatorService.divide(a,b),"/");
        }

    }

    private String buildStr(float a,
                            float b,
                            float result,
                            String operation){
        return String.format("%.1f%s%.1f=%.1f",a,operation,b,result);
    }

}
