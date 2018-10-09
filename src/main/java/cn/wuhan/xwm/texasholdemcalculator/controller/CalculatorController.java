package cn.wuhan.xwm.texasholdemcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wei Ming Xu  QQ:1274263 Just For Fun
 **/
@Controller
public class CalculatorController {

    @RequestMapping(value = "/welcome")
    public String index(){
        return "welcome";
    }

    @RequestMapping(value = "/game")
    public String game(){
        return "game";
    }


}
