package cn.wuhan.xwm.texasholdemcalculator.entity;

import cn.wuhan.xwm.texasholdemcalculator.service.Rule;

import java.util.*;

/**
 * @author Wei Ming Xu  QQ:1274263 Just For Fun
 **/
public class Round {

    /**
     * 0 洗牌状态
     * 1 preflop
     * 2 flop
     * 3 turn
     * 4 river
     * 5 result
     */
    private int currentStep = 0;

    public int getCurrentStep() {
        return currentStep;
    }

    private final List<Card> poker = new ArrayList<>(52);

    // 洗牌后 剩余的牌，除去玩家手牌
    private final List<Card> remain = new ArrayList<>();

    // 洗牌后 flop
    private final List<Card> flop = new ArrayList<>();

    private Card cut1; //第1张切牌
    private Card cut2; //第2张切牌
    private Card cut3; //第3张切牌

    private final List<Card> turn = new ArrayList<>();

    private final List<Card> river = new ArrayList<>();

    public List<Card> getTurn() {
        return turn;
    }

    public List<Card> getRiver() {
        return river;
    }


    public Map<String, List<Card>> getPlayerHands() {
        return playerHands;
    }


    public List<Card> getFlop() {
        return flop;
    }


    public Card getCut1() {
        return cut1;
    }

    public Card getCut2() {
        return cut2;
    }

    public Card getCut3() {
        return cut3;
    }

    // 玩家手排
    private final Map<String, List<Card>> playerHands = new HashMap<>();

    private int playersCount = 2;

    public void setplayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public Round() {
        Card card;
        for (int i = 2; i < 15; i++) {
            for (int j = 1; j < 5; j++) {
                if (j == 1) {
                    card = new Card(i, Flower.SPADE);

                } else if (j == 2) {
                    card = new Card(i, Flower.HEART);
                } else if (j == 3) {
                    card = new Card(i, Flower.CLUB);
                } else {
                    card = new Card(i, Flower.DIAMOND);
                }
                poker.add(card);
            }
        }
        //洗牌5次
        Collections.shuffle(poker);
        Collections.shuffle(poker);
        Collections.shuffle(poker);
        Collections.shuffle(poker);
        Collections.shuffle(poker);
    }

    public Map<String, List<Card>> preflop() {
        if(currentStep == 0) {
            currentStep = 1;
            int numberOfCards = playersCount * 2 + 1 + 3 + 1 + 1 + 1 + 1;
            for (int i = 0; i < numberOfCards; i++) {
                remain.add(poker.get(i));
            }

            for (int i = 0; i < playersCount; i++) {
                ArrayList<Card> hand = new ArrayList<>();
                hand.add(remain.get(i));
                hand.add(remain.get(i + playersCount));
                playerHands.put(i + 1 + "", hand);
            }

            playerHands.forEach((k, v) ->
                    remain.removeAll(v)
            );
        }
        return playerHands;
    }

    public List<Card> flop() {
        if(currentStep==1) {
            currentStep = 2;
            cut1 = remain.get(0);
            remain.remove(0);
            flop.add(remain.get(0));
            remain.remove(0);
            flop.add(remain.get(0));
            remain.remove(0);
            flop.add(remain.get(0));
            remain.remove(0);
        }
        return flop;
    }

    public List<Card> turn() {
        if(currentStep==2) {
            currentStep = 3;
            turn.addAll(flop);
            cut2 = remain.get(0);
            remain.remove(0);
            turn.add(remain.get(0));
            remain.remove(0);
        }
        return turn;
    }

    public List<Card> river() {
        if(currentStep==3) {
            currentStep = 4;
            river.addAll(turn);
            cut3 = remain.get(0);
            remain.remove(0);
            river.add(remain.get(0));
            remain.remove(0);
        }
        return river;
    }

    public String getWinner() {
        String winner = "1";
        if(currentStep==4) {
            currentStep = 5;
            Rule rule = new Rule();
            Map<String, List<Card>> allplayercards = new HashMap<>();
            for (Map.Entry<String, List<Card>> entry : playerHands.entrySet()) {
                entry.getValue();
                ArrayList<Card> all = new ArrayList<>();
                all.addAll(entry.getValue());
                all.addAll(river);
                Card[] cardarr = all.toArray(new Card[all.size()]);
                //7张牌 组合成5张
                combinationSelectArrayList(cardarr, 0, new Card[5], 0);
                //排序比较
                Collections.sort(objlist, new Comparator<List<Card>>() {
                    @Override
                    public int compare(List<Card> o1, List<Card> o2) {
                        String result = rule.compareTwo(o1, o2);
                        if (result.equals(Rule.COMPARE_WIN)) {
                            return -1;
                        } else if (result.equals(Rule.COMPARE_EQUAL)) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                });
                //取玩家最大的一手牌
                allplayercards.put(entry.getKey(), objlist.get(0));
                objlist = new ArrayList<>();
            }
            List<List<Card>> finalcards = new ArrayList<>();
            for (Map.Entry<String, List<Card>> entry : allplayercards.entrySet()) {
                finalcards.add(entry.getValue());
            }
            //对每个玩家最大的牌进行比较
            Collections.sort(finalcards, new Comparator<List<Card>>() {
                @Override
                public int compare(List<Card> o1, List<Card> o2) {
                    String result = rule.compareTwo(o1, o2);
                    if (result.equals(Rule.COMPARE_WIN)) {
                        return -1;
                    } else if (result.equals(Rule.COMPARE_EQUAL)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
            //最大的牌
            List<Card> biggest = finalcards.get(0);

            for (Map.Entry<String, List<Card>> entry : allplayercards.entrySet()) {
                if (rule.compareTwo(entry.getValue(), biggest).equals(Rule.COMPARE_EQUAL)) {
                    winner = entry.getKey() + ",";
                }
            }
        }
        return winner.substring(0, winner.length() - 1);
    }

    //用于从7张牌中找出5张最大的牌的临时变量
    private List<List<Card>> objlist = new ArrayList<>();

    private void combinationSelectArrayList(Card[] dataList, int dataIndex, Card[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            //System.out.println(Arrays.asList(resultList));
            List<Card> cards = new ArrayList<>();
            for (Card c : Arrays.asList(resultList)) {
                Card n = new Card(c.getNumber(), c.getFlower());
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

    /**
     * 从7张牌中匹配出 牌型最大的5张牌
     *
     * @param seven
     * @return
     */
    public List<Card> findBiggestHandFromSevenCards(List<Card> seven) {
        Rule rule = new Rule();
        //1.七张牌做5张的组合
        List<List<Card>> fives = new ArrayList<>();
        Card[] cardarr = seven.toArray(new Card[seven.size()]);
        combinationSelectArrayList(cardarr, 0, new Card[5], 0);
        Collections.sort(objlist, new Comparator<List<Card>>() {
            @Override
            public int compare(List<Card> o1, List<Card> o2) {
                String result = rule.compareTwo(o1, o2);
                if (result.equals(Rule.COMPARE_WIN)) {
                    return -1;
                } else if (result.equals(Rule.COMPARE_EQUAL)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return objlist.get(0);
    }
}
