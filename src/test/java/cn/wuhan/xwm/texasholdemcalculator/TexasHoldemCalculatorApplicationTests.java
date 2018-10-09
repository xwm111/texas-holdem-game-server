package cn.wuhan.xwm.texasholdemcalculator;

import cn.wuhan.xwm.texasholdemcalculator.entity.Card;
import cn.wuhan.xwm.texasholdemcalculator.entity.Flower;
import cn.wuhan.xwm.texasholdemcalculator.entity.Round;
import cn.wuhan.xwm.texasholdemcalculator.service.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TexasHoldemCalculatorApplicationTests {

    public static final String COMPARE_WIN = "WIN";
    public static final String COMPARE_LOSE = "LOSE";
    public static final String COMPARE_EQUAL = "EQUAL";
    @Test
    public void contextLoads() {
        System.out.println(Flower.SPADE.name().equals("SPADE"));
    }

    /**
     * 皇家同花顺测试，只能是一样的牌
     */
    @Test
    public void Test_ROYAL_FLUSH(){
        System.out.println("皇家同花顺测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_EQUAL,result);
    }

    /**
     * 同花顺测试
     */
    @Test
    public void TYPE_STRAIGHT_FLUSH(){
        System.out.println("同花顺测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }


    /**
     * 四条测试
     */
    @Test
    public void TYPE_FOUR_OF_A_KIND(){
        System.out.println("四条测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }

    /**
     * 葫芦测试
     */
    @Test
    public void TYPE_FULLHOUSE(){
        System.out.println("葫芦测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }

    /**
     * 同花测试
     */
    @Test
    public void TYPE_FLUSH(){
        System.out.println("同花测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }

    /**
     * 顺子测试
     */
    @Test
    public void TYPE_STRAIGHT(){
        System.out.println("顺子测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);
    }

    /**
     * 三条测试
     */
    @Test
    public void TYPE_THREE_OF_A_KIND(){
        System.out.println("三条测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }



    /**
     * 两对测试
     */
    @Test
    public void TYPE_TWO_PAIRS(){
        System.out.println("两对测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);
    }


    /**
     * 对子测试
     */
    @Test
    public void TYPE_ONE_PAIR(){
        System.out.println("对子测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);
    }

    /**
     * HIGH牌测试
     */
    @Test
    public void TYPE_HIGH_CARD(){
        System.out.println("HIGH牌测试");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_LOSE,result);
    }
    /**
     * 测试模板
     */
    @Test
    public void temp(){
        System.out.println("测试模板");
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        Rule rule = new Rule();
        String result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
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
        list.forEach((obj) -> System.out.print(obj.toString()));

        System.out.print("----");
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
        list2.forEach((obj) -> System.out.print(obj.toString()));
        result = rule.compareTwo(list,list2);
        System.out.println("测试结果"+result);
        assertEquals(COMPARE_WIN,result);
    }

    /**
     * 测试发preflop
     */
    @Test
    public void testPlay(){
        Round round = new Round();
        round.setPlayers(2);
        Map<String,List<Card>> map = round.preflop();

        map.forEach((k,v) ->{
            System.out.println("hand:"+k);
            v.forEach((obj) -> System.out.print(obj.toString()));
            System.out.println("--preflop--");
        });
        round.flop();
        round.getFlop().forEach((obj) -> System.out.print(obj.toString()) );
        System.out.println("--flop--");

        round.turn();
        round.getTurn().forEach((obj) -> System.out.print(obj.toString()) );
        System.out.println("--turn--");

        round.river();
        round.getRiver().forEach((obj) -> System.out.print(obj.toString()) );
        System.out.println("--river--");
    }

    @Test
    public void testcombination(){
        String[] dataList ={"aaa","bbbb","cccc","ddd","eeee","fffff","ggggg"};
        int n =5;
        Round round = new Round();
        round.setPlayers(2);
        round.preflop();
        round.flop();
        round.turn();
        round.river();
        Map<String, List<Card>> map = round.getPlayerhands();
        List<Card> p1 = map.get("1");
        List<Card> river = round.getRiver();
        ArrayList<Card> all = new ArrayList<Card>();
        all.addAll(p1);
        all.addAll(river);
        Card[] cardarr = (Card[]) all.toArray(new Card[all.size()]);
//        combinationSelect(dataList, 0, new String[n], 0);
        combinationSelectArrayList(cardarr,0,new Card[5],0);
        System.out.println(objlist.size());

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
            System.out.println(cards);
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
            System.out.println(Arrays.asList(resultList));
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
            System.out.println(Arrays.asList(resultList));
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
        System.out.println(Arrays.asList(rule.findBiggestHandFromSevenCards(list)));
    }
}
