package cn.wuhan.xwm.texasholdemcalculator;

import cn.wuhan.xwm.texasholdemcalculator.entity.Card;
import cn.wuhan.xwm.texasholdemcalculator.entity.Flower;
import cn.wuhan.xwm.texasholdemcalculator.entity.Round;
import cn.wuhan.xwm.texasholdemcalculator.service.Rule;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Log4j2
public class TexasHoldemCalculatorApplicationTests {

    public static final String COMPARE_WIN = "WIN";
    public static final String COMPARE_LOSE = "LOSE";
    public static final String COMPARE_EQUAL = "EQUAL";
    @Test
    public void contextLoads() {
        log.info(Flower.SPADE.name().equals("SPADE"));
    }

    /**
     * 皇家同花顺测试，只能是一样的牌
     */
    @Test
    public void Test_ROYAL_FLUSH(){
        log.info("皇家同花顺测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(14, Flower.HEART);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(12, Flower.HEART);
        list.add(card);
        card  = new Card(11, Flower.HEART);
        list.add(card);
        card  = new Card(10, Flower.HEART);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(14, Flower.HEART);
        list2.add(card);
        card  = new Card(13, Flower.HEART);
        list2.add(card);
        card  = new Card(12, Flower.HEART);
        list2.add(card);
        card  = new Card(11, Flower.HEART);
        list2.add(card);
        card  = new Card(10, Flower.HEART);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_EQUAL,result);
    }

    /**
     * 同花顺测试
     */
    @Test
    public void TYPE_STRAIGHT_FLUSH(){
        log.info("同花顺测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(12, Flower.SPADE);
        list.add(card);
        card  = new Card(11, Flower.SPADE);
        list.add(card);
        card  = new Card(10, Flower.SPADE);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list2.add(card);
        card  = new Card(11, Flower.SPADE);
        list2.add(card);
        card  = new Card(10, Flower.SPADE);
        list2.add(card);
        card  = new Card(9, Flower.SPADE);
        list2.add(card);
        card  = new Card(8, Flower.SPADE);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);



        list = new ArrayList<>();
        card  = new Card(6, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(4, Flower.SPADE);
        list.add(card);
        card  = new Card(3, Flower.SPADE);
        list.add(card);
        card  = new Card(2, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(14, Flower.SPADE);
        list2.add(card);
        card  = new Card(3, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(2, Flower.SPADE);
        list2.add(card);
        card  = new Card(4, Flower.SPADE);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }


    /**
     * 四条测试
     */
    @Test
    public void TYPE_FOUR_OF_A_KIND(){
        log.info("四条测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(13, Flower.CLUB);
        list.add(card);
        card  = new Card(13, Flower.DIAMOND);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(13, Flower.HEART);
        list2.add(card);
        card  = new Card(13, Flower.CLUB);
        list2.add(card);
        card  = new Card(13, Flower.DIAMOND);
        list2.add(card);
        card  = new Card(9, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_EQUAL,result);

        list = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.HEART);
        list.add(card);
        card  = new Card(5, Flower.CLUB);
        list.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.HEART);
        list2.add(card);
        card  = new Card(5, Flower.CLUB);
        list2.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);


        list = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(7, Flower.SPADE);
        list.add(card);
        card  = new Card(7, Flower.HEART);
        list.add(card);
        card  = new Card(7, Flower.CLUB);
        list.add(card);
        card  = new Card(7, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.HEART);
        list2.add(card);
        card  = new Card(5, Flower.CLUB);
        list2.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }

    /**
     * 葫芦测试
     */
    @Test
    public void TYPE_FULLHOUSE(){
        log.info("葫芦测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(13, Flower.CLUB);
        list.add(card);
        card  = new Card(9, Flower.DIAMOND);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(13, Flower.HEART);
        list2.add(card);
        card  = new Card(9, Flower.CLUB);
        list2.add(card);
        card  = new Card(9, Flower.DIAMOND);
        list2.add(card);
        card  = new Card(9, Flower.SPADE);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);

        list = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.CLUB);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(5, Flower.CLUB);
        list.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list2.add(card);
        card  = new Card(12, Flower.CLUB);
        list2.add(card);
        card  = new Card(12, Flower.HEART);
        list2.add(card);
        card  = new Card(5, Flower.CLUB);
        list2.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);

        list = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.CLUB);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(5, Flower.CLUB);
        list.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(13, Flower.CLUB);
        list2.add(card);
        card  = new Card(13, Flower.HEART);
        list2.add(card);
        card  = new Card(5, Flower.CLUB);
        list2.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_EQUAL,result);


        list = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.CLUB);
        list.add(card);
        card  = new Card(5, Flower.HEART);
        list.add(card);
        card  = new Card(5, Flower.CLUB);
        list.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list2.add(card);
        card  = new Card(12, Flower.CLUB);
        list2.add(card);
        card  = new Card(5, Flower.HEART);
        list2.add(card);
        card  = new Card(5, Flower.CLUB);
        list2.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }

    /**
     * 同花测试
     */
    @Test
    public void TYPE_FLUSH(){
        log.info("同花测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(4, Flower.SPADE);
        list.add(card);
        card  = new Card(8, Flower.SPADE);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(14, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(4, Flower.SPADE);
        list2.add(card);
        card  = new Card(8, Flower.SPADE);
        list2.add(card);
        card  = new Card(9, Flower.SPADE);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);

        list = new ArrayList<>();
        card  = new Card(11, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        card  = new Card(10, Flower.SPADE);
        list.add(card);
        card  = new Card(12, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list2.add(card);
        card  = new Card(2, Flower.SPADE);
        list2.add(card);
        card  = new Card(8, Flower.SPADE);
        list2.add(card);
        card  = new Card(11, Flower.SPADE);
        list2.add(card);
        card  = new Card(9, Flower.SPADE);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }

    /**
     * 顺子测试
     */
    @Test
    public void TYPE_STRAIGHT(){
        log.info("顺子测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(14, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(12, Flower.CLUB);
        list.add(card);
        card  = new Card(11, Flower.DIAMOND);
        list.add(card);
        card  = new Card(10, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(12, Flower.HEART);
        list2.add(card);
        card  = new Card(11, Flower.CLUB);
        list2.add(card);
        card  = new Card(10, Flower.DIAMOND);
        list2.add(card);
        card  = new Card(9, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);

        list = new ArrayList<>();
        card  = new Card(14, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(4, Flower.HEART);
        list.add(card);
        card  = new Card(3, Flower.CLUB);
        list.add(card);
        card  = new Card(2, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(4, Flower.SPADE);
        list2.add(card);
        card  = new Card(3, Flower.HEART);
        list2.add(card);
        card  = new Card(6, Flower.CLUB);
        list2.add(card);
        card  = new Card(2, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);
    }

    /**
     * 三条测试
     */
    @Test
    public void TYPE_THREE_OF_A_KIND(){
        log.info("三条测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(13, Flower.CLUB);
        list.add(card);
        card  = new Card(12, Flower.DIAMOND);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(13, Flower.HEART);
        list2.add(card);
        card  = new Card(13, Flower.CLUB);
        list2.add(card);
        card  = new Card(14, Flower.DIAMOND);
        list2.add(card);
        card  = new Card(2, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);

        list = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list.add(card);
        card  = new Card(12, Flower.CLUB);
        list.add(card);
        card  = new Card(12, Flower.HEART);
        list.add(card);
        card  = new Card(5, Flower.CLUB);
        list.add(card);
        card  = new Card(4, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(4, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(3, Flower.HEART);
        list2.add(card);
        card  = new Card(3, Flower.CLUB);
        list2.add(card);
        card  = new Card(3, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }



    /**
     * 两对测试
     */
    @Test
    public void TYPE_TWO_PAIRS(){
        log.info("两对测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(14, Flower.CLUB);
        list.add(card);
        card  = new Card(14, Flower.DIAMOND);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(14, Flower.SPADE);
        list2.add(card);
        card  = new Card(14, Flower.HEART);
        list2.add(card);
        card  = new Card(12, Flower.CLUB);
        list2.add(card);
        card  = new Card(12, Flower.DIAMOND);
        list2.add(card);
        card  = new Card(3, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);

        list = new ArrayList<>();
        card  = new Card(14, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.HEART);
        list.add(card);
        card  = new Card(6, Flower.CLUB);
        list.add(card);
        card  = new Card(6, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(14, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.HEART);
        list2.add(card);
        card  = new Card(8, Flower.CLUB);
        list2.add(card);
        card  = new Card(8, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);
    }


    /**
     * 对子测试
     */
    @Test
    public void TYPE_ONE_PAIR(){
        log.info("对子测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(12, Flower.CLUB);
        list.add(card);
        card  = new Card(11, Flower.DIAMOND);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(12, Flower.HEART);
        list2.add(card);
        card  = new Card(12, Flower.CLUB);
        list2.add(card);
        card  = new Card(11, Flower.DIAMOND);
        list2.add(card);
        card  = new Card(9, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);

        list = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(4, Flower.HEART);
        list.add(card);
        card  = new Card(3, Flower.CLUB);
        list.add(card);
        card  = new Card(3, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.HEART);
        list2.add(card);
        card  = new Card(3, Flower.CLUB);
        list2.add(card);
        card  = new Card(4, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);
    }

    /**
     * HIGH牌测试
     */
    @Test
    public void TYPE_HIGH_CARD(){
        log.info("HIGH牌测试");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(11, Flower.HEART);
        list.add(card);
        card  = new Card(8, Flower.CLUB);
        list.add(card);
        card  = new Card(7, Flower.DIAMOND);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(11, Flower.HEART);
        list2.add(card);
        card  = new Card(8, Flower.CLUB);
        list2.add(card);
        card  = new Card(7, Flower.DIAMOND);
        list2.add(card);
        card  = new Card(9, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_EQUAL,result);

        list = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(14, Flower.SPADE);
        list.add(card);
        card  = new Card(8, Flower.HEART);
        list.add(card);
        card  = new Card(9, Flower.CLUB);
        list.add(card);
        card  = new Card(2, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list2.add(card);
        card  = new Card(14, Flower.SPADE);
        list2.add(card);
        card  = new Card(13, Flower.HEART);
        list2.add(card);
        card  = new Card(9, Flower.CLUB);
        list2.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);
    }
    /**
     * 测试模板
     */
    @Test
    public void temp(){
        log.info("测试模板");
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(13, Flower.HEART);
        list.add(card);
        card  = new Card(13, Flower.CLUB);
        list.add(card);
        card  = new Card(13, Flower.DIAMOND);
        list.add(card);
        card  = new Card(9, Flower.SPADE);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        ArrayList<Card> list2 = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list2.add(card);
        card  = new Card(13, Flower.HEART);
        list2.add(card);
        card  = new Card(13, Flower.CLUB);
        list2.add(card);
        card  = new Card(13, Flower.DIAMOND);
        list2.add(card);
        card  = new Card(9, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_EQUAL,result);

        list = new ArrayList<>();
        card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.HEART);
        list.add(card);
        card  = new Card(5, Flower.CLUB);
        list.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list.add(card);
        list.sort(Comparator.reverseOrder());
        list.forEach((obj) -> log.info(obj.toString()));

        log.info("----");
        list2 = new ArrayList<>();
        card  = new Card(12, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.SPADE);
        list2.add(card);
        card  = new Card(5, Flower.HEART);
        list2.add(card);
        card  = new Card(5, Flower.CLUB);
        list2.add(card);
        card  = new Card(5, Flower.DIAMOND);
        list2.add(card);
        list2.sort(Comparator.reverseOrder());
        list2.forEach((obj) -> log.info(obj.toString()));
        result = rule.compareTwo(list,list2);
        log.info("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }

    /**
     * 发牌测试
     */
    @Test
    public void testPlay(){
        Round round = new Round();
        round.setplayersCount(4);
        Map<String,List<Card>> map = round.preflop();

        map.forEach((k,v) ->{
            log.info("hand:"+k);
            v.forEach((obj) -> log.info(obj.toString()));
        });
        round.flop();
        log.info("--cut1--");
        log.info(round.getCut1().toString());
        log.info("--flop--");
        round.getFlop().forEach((obj) -> log.info(obj.toString()) );

        round.turn();
        log.info("--cut2--");
        log.info(round.getCut2().toString());
        log.info("--turn--");
        round.getTurn().forEach((obj) -> log.info(obj.toString()) );


        round.river();
        log.info("--cut3--");
        log.info(round.getCut3().toString());
        log.info("--river--");
        round.getRiver().forEach((obj) -> log.info(obj.toString()) );

    }

    @Test
    public void testcombination(){
        String[] dataList ={"aaa","bbbb","cccc","ddd","eeee","fffff","ggggg"};
        int n =5;
        Round round = new Round();
        round.setplayersCount(2);
        round.preflop();
        round.flop();
        round.turn();
        round.river();
        Map<String, List<Card>> map = round.getPlayerHands();
        List<Card> p1 = map.get("1");
        List<Card> river = round.getRiver();
        ArrayList<Card> all = new ArrayList<Card>();
        all.addAll(p1);
        all.addAll(river);
        Card[] cardarr = (Card[]) all.toArray(new Card[all.size()]);
//        combinationSelect(dataList, 0, new String[n], 0);
        combinationSelectArrayList(cardarr,0,new Card[5],0);
        log.info(objlist.size());

        Collections.sort(objlist,new Comparator<List<Card>>(){

            @Override
            public int compare(List<Card> o1, List<Card> o2) {
                Rule rule = new Rule();
                String result = rule.compareTwo(o1,o2);
                if(result.equals(Rule.COMPARE_WIN)){
                    return -1;
                }else if(result.equals(Rule.COMPARE_EQUAL)){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        for(List<Card> cards : objlist){
            log.info(cards);
        }
    }


    private List<List<Card>> objlist = new ArrayList<>();
    /**
     * 组合选择
     * @param dataList 待选列表
     * @param dataIndex 待选开始索引
     * @param resultList 前面（resultIndex-1）个的组合结果
     * @param resultIndex 选择索引，从0开始
     */
    private void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            log.info(Arrays.asList(resultList));
            return;
        }

        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
        }
    }

    private void combinationSelectArrayList(Card[] dataList, int dataIndex, Card[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            log.info(Arrays.asList(resultList));
            List<Card> cards = new ArrayList<>();
            for(Card c :Arrays.asList(resultList)){
                Card n = new Card(c.getNumber(),c.getFlower());
                cards.add(n);
            }
            objlist.add(cards);
            return;
        }

        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelectArrayList(dataList, i + 1, resultList, resultIndex + 1);
        }
    }

    @Test
    public void findBiggestHandFromSevenCards(){
        ArrayList<Card> list = new ArrayList<>();
        Card card  = new Card(13, Flower.SPADE);
        list.add(card);
        card  = new Card(12, Flower.HEART);
        list.add(card);
        card  = new Card(8, Flower.CLUB);
        list.add(card);
        card  = new Card(7, Flower.DIAMOND);
        list.add(card);
        card  = new Card(6, Flower.SPADE);
        list.add(card);
        card  = new Card(5, Flower.SPADE);
        list.add(card);
        card  = new Card(2, Flower.SPADE);
        list.add(card);
        Rule rule = new Rule();
        log.info(Arrays.asList(rule.findBiggestHandFromSevenCards(list)));
    }
}
