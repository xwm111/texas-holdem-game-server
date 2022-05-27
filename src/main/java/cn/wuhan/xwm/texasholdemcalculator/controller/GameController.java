package cn.wuhan.xwm.texasholdemcalculator.controller;

import cn.wuhan.xwm.texasholdemcalculator.entity.Card;
import cn.wuhan.xwm.texasholdemcalculator.entity.Round;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wei Ming Xu  QQ:1274263 Just For Fun
 **/
@RestController
@RequestMapping("/api")
@Scope("session")
public class GameController {
    private Round round;

    @GetMapping("/gamestart")
    public Map<String, List<Card>> start(@RequestParam String persons) throws Exception{
        round = new Round();
        int playerCount = Integer.valueOf(persons);
        if(playerCount>9){
            throw new Exception("玩家数量最多只允许9人");
        }
        round.setplayersCount(Integer.valueOf(persons));
        return round.preflop();
    }

    @GetMapping("/preflop")
    public Map<String, List<Card>> preflop() {
        return round.getPlayerHands();
    }

    @GetMapping("/flop")
    public List<Card> flop() {
        return round.flop();
    }

    @GetMapping("/turn")
    public List<Card> turn() {
        return round.turn();
    }

    @GetMapping("/river")
    public List<Card> river() {
        return round.river();
    }

    @GetMapping("/result")
    public String result() {
        return round.getWinner();
    }

    @GetMapping("/currentround")
    public Round currentround() {
        return round;
    }
}
