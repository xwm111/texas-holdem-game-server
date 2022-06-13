package cn.wuhan.xwm.texasholdemcalculator.service;

import cn.wuhan.xwm.texasholdemcalculator.entity.Card;

import java.util.*;

/**
 * @author Wei Ming Xu  QQ:1274263 Just For Fun
 **/
public class Rule {
    private static final int TYPE_ROYAL_FLUSH = 10;
    private static final int TYPE_STRAIGHT_FLUSH = 9;
    private static final int TYPE_FOUR_OF_A_KIND = 8;

    private static final int TYPE_FULL_HOUSE = 7;
    private static final int TYPE_FLUSH = 6;
    private static final int TYPE_STRAIGHT = 5;
    private static final int TYPE_THREE_OF_A_KIND = 4;
    private static final int TYPE_TWO_PAIRS = 3;
    private static final int TYPE_ONE_PAIR = 2;
    private static final int TYPE_HIGH_CARD = 1;

    public static final String COMPARE_WIN = "WIN";
    public static final String COMPARE_LOSE = "LOSE";
    public static final String COMPARE_EQUAL = "EQUAL";

    /**
     * 比较两手牌的大小
     * 前提: 比较之前已经将5张牌按照点数大小排序
     *
     * @param first
     * @param second
     * @return
     */
    public String compareTwo(List<Card> first, List<Card> second) {
        first.sort(Comparator.reverseOrder());
        second.sort(Comparator.reverseOrder());
        //首先判断所有牌是否一样,一样就直接相等,后续判断则不用考虑相等的比较
        if (first.get(0).getNumber() == second.get(0).getNumber()
                && first.get(1).getNumber() == second.get(1).getNumber()
                && first.get(2).getNumber() == second.get(2).getNumber()
                && first.get(3).getNumber() == second.get(3).getNumber()
                && first.get(4).getNumber() == second.get(4).getNumber()
        )
            return COMPARE_EQUAL;
        int firstType = calType(first);
        int secondType = calType(second);

        // 先比较牌型
        if (firstType > secondType) {
            return COMPARE_WIN;
        } else if (firstType < secondType) {
            return COMPARE_LOSE;
        } else {
            //牌型相同再比较挂子
            switch (firstType) {
                case TYPE_ROYAL_FLUSH://皇家同花顺只能是平
                    return COMPARE_EQUAL;
                case TYPE_STRAIGHT_FLUSH:
                    return COMPARE_TYPE_STRAIGHT_FLUSH(first, second);
                case TYPE_FOUR_OF_A_KIND:
                    return COMPARE_TYPE_TYPE_FOUR_OF_A_KIND(first, second);
                case TYPE_FULL_HOUSE:
                    return COMPARE_TYPE_FULLHOUSE(first, second);
                case TYPE_FLUSH:
                    return COMPARE_TYPE_FLUSH(first, second);
                case TYPE_STRAIGHT:
                    return COMPARE_TYPE_STRAIGHT(first, second);
                case TYPE_THREE_OF_A_KIND:
                    return COMPARE_TYPE_THREE_OF_A_KIND(first, second);
                case TYPE_TWO_PAIRS:
                    return COMPARE_TYPE_TWO_PAIRS(first, second);
                case TYPE_ONE_PAIR:
                    return COMPARE_TYPE_ONE_PAIR(first, second);
                case TYPE_HIGH_CARD:
                    return COMPARE_TYPE_HIGH_CARD(first, second);
                default:
                    return "error";
            }
        }
    }

    /**
     * 比较两手牌的大小
     * High 牌 比较
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_HIGH_CARD(List<Card> first, List<Card> second) {
        if (first.get(0).getNumber() > second.get(0).getNumber()) {
            return COMPARE_WIN;
        } else if (first.get(0).getNumber() < second.get(0).getNumber()) {
            return COMPARE_LOSE;
        } else if (first.get(1).getNumber() > second.get(1).getNumber()) {
            return COMPARE_WIN;
        } else if (first.get(1).getNumber() < second.get(1).getNumber()) {
            return COMPARE_LOSE;
        } else if (first.get(2).getNumber() > second.get(2).getNumber()) {
            return COMPARE_WIN;
        } else if (first.get(2).getNumber() < second.get(2).getNumber()) {
            return COMPARE_LOSE;
        }else if (first.get(3).getNumber() > second.get(3).getNumber()) {
            return COMPARE_WIN;
        } else if (first.get(3).getNumber() < second.get(3).getNumber()) {
            return COMPARE_LOSE;
        }else if (first.get(4).getNumber() > second.get(4).getNumber()) {
            return COMPARE_WIN;
        } else{
            return COMPARE_LOSE;
        }
    }

    /**
     * 比较两手牌的大小
     * 一对比较
     *
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_ONE_PAIR(List<Card> first, List<Card> second) {
        int a = findPairFromOnePair(first);
        int b = findPairFromOnePair(second);
        List<Card> first3 = removePair(first);
        List<Card> second3 = removePair(second);
        if (a > b) {
            return COMPARE_WIN;
        } else if (a < b) {
            return COMPARE_LOSE;
        } else if (first3.get(0).getNumber() > second3.get(0).getNumber()) {
            return COMPARE_WIN;
        } else if (first3.get(0).getNumber() < second3.get(0).getNumber()) {
            return COMPARE_LOSE;
        } else if (first3.get(1).getNumber() > second3.get(1).getNumber()) {
            return COMPARE_WIN;
        } else if (first3.get(1).getNumber() < second3.get(1).getNumber()) {
            return COMPARE_LOSE;
        } else if (first3.get(2).getNumber() > second3.get(2).getNumber()) {
            return COMPARE_WIN;
        } else if (first3.get(2).getNumber() < second3.get(2).getNumber()) {
            return COMPARE_LOSE;
        } else {
            return COMPARE_EQUAL;
        }
    }

    /**
     * 从五张牌中移除对子
     * AABCD ABBCD ABCCD ABCDD
     *
     * @param five
     * @return
     */
    private List<Card> removePair(List<Card> five) {
        List<Card> back = new ArrayList<>();
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        if (minus1 == 0) {
            back.add(five.get(2));
            back.add(five.get(3));
            back.add(five.get(4));
        } else if (minus2 == 0) {
            back.add(five.get(0));
            back.add(five.get(3));
            back.add(five.get(4));
        } else if (minus3 == 0) {
            back.add(five.get(0));
            back.add(five.get(1));
            back.add(five.get(4));
        } else {
            back.add(five.get(0));
            back.add(five.get(1));
            back.add(five.get(2));
        }
        return back;
    }

    /**
     * 取对子的点数
     * AABCD ABBCD ABCCD ABCDD
     *
     * @param five
     * @return
     */
    private int findPairFromOnePair(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        if (minus1 == 0) {
            return five.get(0).getNumber();
        } else if (minus2 == 0) {
            return five.get(1).getNumber();
        } else if (minus3 == 0) {
            return five.get(2).getNumber();
        } else {
            return five.get(3).getNumber();
        }
    }

    /**
     * 比较两手牌的大小
     * 两对比较
     *
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_TWO_PAIRS(List<Card> first, List<Card> second) {
        int a_2_1 = findBiggerPairFromTwoPairs(first);
        int b_2_1 = findBiggerPairFromTwoPairs(second);
        int a_2_2 = findSmallerPairFromTwoPairs(first);
        int b_2_2 = findSmallerPairFromTwoPairs(first);
        int a_s = findSingleFromTwoPairs(first);
        int b_s = findSingleFromTwoPairs(second);
        if (a_2_1 > b_2_1) {
            return COMPARE_WIN;
        } else if (a_2_1 < b_2_1) {
            return COMPARE_LOSE;
        } else if (a_2_2 > b_2_2) {
            return COMPARE_WIN;
        } else if (a_2_2 < b_2_2) {
            return COMPARE_LOSE;
        } else if (a_s > b_s) {
            return COMPARE_WIN;
        } else if (a_s < b_s) {
            return COMPARE_LOSE;
        } else
            return COMPARE_EQUAL;
    }

    /**
     * 取两对中的单张
     * 两对的牌型只有AABBC ABBCC AABCC
     *
     * @param five
     * @return
     */
    private int findSingleFromTwoPairs(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus1 == 0 && minus3 == 0) {
            return five.get(4).getNumber();
        } else if (minus2 == 0 && minus4 == 0) {
            return five.get(0).getNumber();
        } else {
            return five.get(2).getNumber();
        }

    }

    /**
     * 取底对
     * 两对的牌型只有AABBC ABBCC AABCC
     *
     * @param five
     * @return
     */
    private int findSmallerPairFromTwoPairs(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus4 == 0) {
            return five.get(4).getNumber();
        } else {
            return five.get(3).getNumber();
        }
    }

    /**
     * 取高对
     * 两对的牌型只有AABBC ABBCC AABCC
     *
     * @param five
     * @return
     */
    private int findBiggerPairFromTwoPairs(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        if (minus1 == 0) {
            return five.get(0).getNumber();
        } else {
            return five.get(1).getNumber();
        }
    }

    /**
     * 三条比较
     *
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_THREE_OF_A_KIND(List<Card> first, List<Card> second) {
        int a3 = findThreeFromThreeOfAKind(first);
        int b3 = findThreeFromThreeOfAKind(second);
        int a_1 = findSingleBiggerFromThreeOfAKind(first);
        int b_1 = findSingleBiggerFromThreeOfAKind(second);
        int a_2 = findSingleSmallerFromThreeOfAKind(first);
        int b_2 = findSingleSmallerFromThreeOfAKind(second);

        //先比较三条的大小
        if (a3 > b3) {
            return COMPARE_WIN;
        } else if (a3 < b3) {
            return COMPARE_LOSE;
        } else {
            //再比较三条高张的大小
            if (a_1 > b_1) {
                return COMPARE_WIN;
            } else if (a_1 < b_1) {
                return COMPARE_LOSE;
            } else {
                //再比较第二高张的大小
                if (a_2 > b_2) {
                    return COMPARE_WIN;
                } else if (a_2 < b_2) {
                    return COMPARE_LOSE;
                } else {
                    return COMPARE_EQUAL;
                }
            }
        }
    }

    /**
     * 取三条的高张
     * @param five
     * @return
     */
    private int findSingleBiggerFromThreeOfAKind(List<Card> five) {
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        if (minus1 == 0 && minus2 == 0) {
            return five.get(3).getNumber();
        } else {
            return five.get(0).getNumber();
        }
    }

    /**
     * 取三条的低张
     * @param five
     * @return
     */
    private int findSingleSmallerFromThreeOfAKind(List<Card> five) {
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus3 == 0 && minus4 == 0) {
            return five.get(1).getNumber();
        } else {
            return five.get(4).getNumber();
        }
    }

    /**
     * 取三条中 三条的点数
     * @param five
     * @return
     */
    private int findThreeFromThreeOfAKind(List<Card> five) {
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        if (minus1 == 0 && minus2 == 0) {
            return five.get(0).getNumber();
        } else if (minus2 == 0 && minus3 == 0) {
            return five.get(3).getNumber();
        } else {
            return five.get(4).getNumber();
        }
    }

    /**
     * 顺子比较
     * 顺子只有可能是上顺和下顺
     *
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_STRAIGHT(List<Card> first, List<Card> second) {
        if (first.get(4).getNumber() == 2 && first.get(0).getNumber() == 14) {//first 为 A5432
            return COMPARE_LOSE;
        } else if (second.get(0).getNumber() == 14 && second.get(4).getNumber() == 2) {//second为 A5432
            return COMPARE_WIN;
        } else if (first.get(0).getNumber() > second.get(0).getNumber()) {
            return COMPARE_WIN;
        } else {
            return COMPARE_LOSE;
        }
    }

    /**
     * 同花比较
     * 从最大的牌依次开始比较
     *
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_FLUSH(List<Card> first, List<Card> second) {
        if (first.get(0).getNumber() > second.get(0).getNumber()) {//
            return COMPARE_WIN;
        } else if (first.get(0).getNumber() < second.get(0).getNumber()) {
            return COMPARE_LOSE;
        } else if (first.get(1).getNumber() > second.get(1).getNumber()) {
            return COMPARE_WIN;
        } else if (first.get(1).getNumber() < second.get(1).getNumber()) {
            return COMPARE_LOSE;
        } else if (first.get(2).getNumber() > second.get(2).getNumber()) {
            return COMPARE_WIN;
        } else if (first.get(2).getNumber() < second.get(2).getNumber()) {
            return COMPARE_LOSE;
        } else if (first.get(3).getNumber() > second.get(3).getNumber()) {
            return COMPARE_WIN;
        } else if (first.get(3).getNumber() < second.get(3).getNumber()) {
            return COMPARE_LOSE;
        } else if (first.get(4).getNumber() > second.get(4).getNumber()) {
            return COMPARE_WIN;
        } else if (first.get(4).getNumber() < second.get(4).getNumber()) {
            return COMPARE_LOSE;
        } else {
            return COMPARE_EQUAL;
        }
    }

    /**
     * 葫芦比较
     *
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_FULLHOUSE(List<Card> first, List<Card> second) {
        int a2 = findTwoFromFullhouse(first);
        int b2 = findTwoFromFullhouse(second);
        int a3 = findThreeFromFullhouse(first);
        int b3 = findThreeFromFullhouse(second);
        //先比较葫芦的三张大小
        if (a3 > b3) {
            return COMPARE_WIN;
        } else if (a3 < b3) {
            return COMPARE_LOSE;
        } else {
            //再比较葫芦的对子大小
            if (a2 > b2) {
                return COMPARE_WIN;
            } else {
                return COMPARE_LOSE;
            }
        }
    }

    /**
     * 找出葫芦中的对子点数
     * @param five
     * @return
     */
    private int findTwoFromFullhouse(List<Card> five) {
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        if (minus1 == 0 && minus2 == 0) {
            return five.get(4).getNumber();
        } else {
            return five.get(0).getNumber();
        }
    }

    /**
     * 找出葫芦中的三条点数
     * @param five
     * @return
     */
    private int findThreeFromFullhouse(List<Card> five) {
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        if (minus1 == 0 && minus2 == 0) {
            return five.get(0).getNumber();
        } else {
            return five.get(4).getNumber();
        }
    }


    /**
     * 四条比较
     *
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_TYPE_FOUR_OF_A_KIND(List<Card> first, List<Card> second) {

        //先比较四条大小
        int a4 = findFourFromFourOfAKind(first);
        int b4 = findFourFromFourOfAKind(second);
        if (a4 > b4) {
            return COMPARE_WIN;
        } else if (a4 < b4) {
            return COMPARE_LOSE;
        } else {
            //再比较四条的挂张大小
            int a = findOneFromFourOfAKind(first);
            int b = findOneFromFourOfAKind(second);
            if (a > b) {
                return COMPARE_WIN;
            } else {
                return COMPARE_LOSE;
            }
        }
    }

    /**
     * 找出四条中单张的点数
     * @param five
     * @return
     */
    private int findOneFromFourOfAKind(List<Card> five) {
        if ((five.get(0).getNumber() - five.get(1).getNumber()) == 0) {
            return five.get(0).getNumber();
        } else {
            return five.get(4).getNumber();
        }
    }

    /**
     * 找出四条中四条的点数
     * @param five
     * @return
     */
    private int findFourFromFourOfAKind(List<Card> five) {
        if ((five.get(0).getNumber() - five.get(1).getNumber()) == 0) {
            return five.get(4).getNumber();
        } else {
            return five.get(0).getNumber();
        }
    }


    /**
     * 同花顺
     *
     * @param first
     * @param second
     * @return
     */
    private String COMPARE_TYPE_STRAIGHT_FLUSH(List<Card> first, List<Card> second) {
        Card a = first.get(0);
        Card b = second.get(0);
        if (a.getNumber() == 14)//有A的肯定是下顺，如果是AKQJT就是皇家同花顺了
            return COMPARE_LOSE;
        if (b.getNumber() == 14)
            return COMPARE_WIN;
        if (a.getNumber() > b.getNumber())
            return COMPARE_WIN;
        else
            return COMPARE_LOSE;
    }

    /**
     * 计算牌的类型
     *
     * @param five
     * @return
     */
    public int calType(List<Card> five) {
        if (isRoyalFlush(five)) {
            return TYPE_ROYAL_FLUSH;
        } else if (isStraightFlush(five)) {
            return TYPE_STRAIGHT_FLUSH;
        } else if (isFourOfaKind(five)) {
            return TYPE_FOUR_OF_A_KIND;
        } else if (isFullhouse(five)) {
            return TYPE_FULL_HOUSE;
        } else if (isFlush(five)) {
            return TYPE_FLUSH;
        } else if (isStraight(five)) {
            return TYPE_STRAIGHT;
        } else if (isThreeOfaKind(five)) {
            return TYPE_THREE_OF_A_KIND;
        } else if (isTwoPairs(five)) {
            return TYPE_TWO_PAIRS;
        } else if (isOnePairs(five)) {
            return TYPE_ONE_PAIR;
        } else {
            return TYPE_HIGH_CARD;
        }
    }

    /**
     * 判断是否是一对
     * @param five
     * @return
     */
    private boolean isOnePairs(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus1 == 0 && minus2 != 0 && minus3 != 0 && minus4 != 0) {
            return true;
        } else if (minus1 != 0 && minus2 == 0 && minus3 != 0 && minus4 != 0) {
            return true;
        } else if (minus1 != 0 && minus2 != 0 && minus3 == 0 && minus4 != 0) {
            return true;
        } else if (minus1 != 0 && minus2 != 0 && minus3 != 0 && minus4 == 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断是否是两对
     * @param five
     * @return
     */
    private boolean isTwoPairs(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus1 == 0 && minus2 != 0 && minus3 == 0 && minus4 != 0) {
            return true;
        } else if (minus1 != 0 && minus2 == 0 && minus3 != 0 && minus4 == 0) {
            return true;
        } else if (minus1 != 0 && minus2 != 0 && minus3 == 0 && minus4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是三条
     * @param five
     * @return
     */
    private boolean isThreeOfaKind(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus1 == 0 && minus2 == 0 && minus3 != 0 && minus4 != 0) {
            return true;
        } else if (minus1 != 0 && minus2 == 0 && minus3 == 0 && minus4 != 0) {
            return true;
        } else if (minus1 != 0 && minus2 != 0 && minus3 == 0 && minus4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是顺子
     * @param five
     * @return
     */
    private boolean isStraight(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus1 == 1 && minus2 == 1 && minus3 == 1 && minus4 == 1) {
            return true;
        } else if (minus1 == 9 && minus2 == 1 && minus3 == 1 && minus4 == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是同花
     * @param five
     * @return
     */
    private boolean isFlush(List<Card> five) {
        if (five.get(0).getFlower() == five.get(1).getFlower()
                && five.get(2).getFlower() == five.get(3).getFlower()
                && five.get(3).getFlower() == five.get(4).getFlower()
                && five.get(4).getFlower() == five.get(0).getFlower()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断是否是葫芦
     * @param five
     * @return
     */
    private boolean isFullhouse(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus1 == 0 && minus2 == 0 && minus3 != 0 && minus4 == 0) {
            return true;
        } else if (minus1 == 0 && minus2 != 0 && minus3 == 0 && minus4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是四条
     * @param five
     * @return
     */
    private boolean isFourOfaKind(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        int minus1 = five.get(0).getNumber() - five.get(1).getNumber();
        int minus2 = five.get(1).getNumber() - five.get(2).getNumber();
        int minus3 = five.get(2).getNumber() - five.get(3).getNumber();
        int minus4 = five.get(3).getNumber() - five.get(4).getNumber();
        if (minus1 == 0 && minus2 == 0 && minus3 == 0 && minus4 != 0) {
            return true;
        } else if (minus1 != 0 && minus2 == 0 && minus3 == 0 && minus4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是同花顺
     * @param five
     * @return
     */
    private boolean isStraightFlush(List<Card> five) {
        if (isStraight(five) && isFlush(five)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是皇家同花顺
     * @param five
     * @return
     */
    private boolean isRoyalFlush(List<Card> five) {
        five.sort(Comparator.reverseOrder());
        if (isStraightFlush(five) && five.get(1).getNumber() == 13) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 从7张牌中匹配出 牌型最大的5张牌
     *
     * @param seven
     * @return
     */
    public List<Card> findBiggestHandFromSevenCards(List<Card> seven) {
        Rule rule = new Rule();
        //1.七张牌做5张的组合
        Card[] cards = seven.toArray(new Card[seven.size()]);
        combinationSelectArrayList(cards, 0, new Card[5], 0);
        //2.然后对所有的组合进行排序，降序
        Collections.sort(objlist, new Comparator<List<Card>>() {
            String result;
            @Override
            public int compare(List<Card> o1, List<Card> o2) {
                result = rule.compareTwo(o1, o2);
                if (result.equals(Rule.COMPARE_WIN) || result.equals(Rule.COMPARE_EQUAL)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        //3.返回第一个，第一个是最大的
        return objlist.get(0);
    }

    //用于从7张牌中找出5张最大的牌的临时变量
    private List<List<Card>> objlist = new ArrayList<>();

    private void combinationSelectArrayList(Card[] dataList, int dataIndex, Card[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            //System.out.println(Arrays.asList(resultList));
            List<Card> cards = new ArrayList<>();
            Card n;
            for (Card c : Arrays.asList(resultList)) {
                n = new Card(c.getNumber(), c.getFlower());
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

}
