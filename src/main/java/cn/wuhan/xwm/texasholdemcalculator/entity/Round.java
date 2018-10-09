package cn.wuhan.xwm.texasholdemcalculator.entity;

import cn.wuhan.xwm.texasholdemcalculator.service.Rule;

import java.util.*;

/**
 * @author Wei Ming Xu  QQ:1274263 Just For Fun
 **/
public class Round {
    private List<Card> poker = new ArrayList<>(52);

    // 洗牌后 剩余的牌，除去玩家手排
    private List<Card> remain = new ArrayList<>();
    // 洗牌后 flop
    private List<Card> flop = new ArrayList<>();

    private Card cut1;
    private Card cut2;
    private Card cut3;

    private List<Card> turn = new ArrayList<>();

    private List<Card> river = new ArrayList<>();

    public List<Card> getTurn() {
        return turn;
    }

    public List<Card> getRiver() {
        return river;
    }


    public Map<String, List<Card>> getPlayerhands() {
        return playerhands;
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
    private Map<String, List<Card>> playerhands = new HashMap<>();

    private int players = 2;

    public void setPlayers(int players) {
        this.players = players;
    }

    public Round() {
        Card card = null;
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
        Integer numberOfCards = players * 2 + 1 + 3 + 1 + 1 + 1 + 1;
        for (int i = 0; i < numberOfCards; i++) {
            remain.add(poker.get(i));
        }

        for (int i = 0; i < players; i++) {
            ArrayList<Card> hand = new ArrayList<>();
            hand.add(remain.get(i));
            hand.add(remain.get(i + players));
            playerhands.put(i + 1 + "", hand);
        }

        playerhands.forEach((k, v) ->
                remain.removeAll(v)
        );

        return playerhands;
    }

    public List<Card> flop() {
        List<Card> flop = new ArrayList<>();
        cut1 = remain.get(0);
        remain.remove(0);
        flop.add(remain.get(0));
        remain.remove(0);
        flop.add(remain.get(0));
        remain.remove(0);
        flop.add(remain.get(0));
        remain.remove(0);
        this.flop = flop;
        return flop;
    }

    public List<Card> turn() {
        List<Card> turn = new ArrayList<>();
        turn.addAll(flop);
        cut2 = remain.get(0);
        remain.remove(0);
        turn.add(remain.get(0));
        remain.remove(0);
        this.turn = turn;
        return turn;
    }

    public List<Card> river() {
        List<Card> river = new ArrayList<>();
        river.addAll(turn);
        cut3 = remain.get(0);
        remain.remove(0);
        river.add(remain.get(0));
        remain.remove(0);
        this.river = river;
        return river;
    }

    public String getWinner() {
        Rule rule = new Rule();
        String winner = "1";
        Map<String, List<Card>> allplayercards = new HashMap<>();
        for (Map.Entry<String, List<Card>> entry : playerhands.entrySet()) {
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
