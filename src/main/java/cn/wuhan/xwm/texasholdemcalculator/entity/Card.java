package cn.wuhan.xwm.texasholdemcalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Wei Ming Xu  QQ:1274263 Just For Fun
 **/
@Getter
@Setter
@AllArgsConstructor
public class Card implements Comparable<Card> {
    private Integer number;
    private Flower flower;//黑桃:Spade橄榄叶.红桃:Heart.梅花:Club.方块:Diamond.

    @Override
    public int compareTo(Card o) {
        //如果认为此实体本身大则返回1，否则返回-1
        if (this.getNumber() > o.getNumber()) {
            return 1;
        } else if (this.getNumber() == o.getNumber()) {
            if (this.getFlower().ordinal() < o.getFlower().ordinal()) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String temp;
        if(this.getNumber()==11){
            temp = "J";
        }else if(this.getNumber()==12){
            temp = "Q";
        }else if(this.getNumber()==13){
            temp = "K";
        }else if(this.getNumber()==14){
            temp = "A";
        }else if(this.getNumber()==10){
            temp = "T";
        }else{
            temp = String.valueOf(this.getNumber());
        }
        char[] cs = new char[4];
        cs[0]   =   0x2660;
        cs[1]   =   0x2665;
        cs[2]   =   0x2663;
        cs[3]   =   0x2666;
        if(this.getFlower()==Flower.SPADE){
            temp+=cs[0];
        }else if(this.getFlower()==Flower.HEART){
            temp+=cs[1];
        }else if(this.getFlower()==Flower.CLUB){
            temp+=cs[2];
        }else if(this.getFlower()==Flower.DIAMOND){
            temp+=cs[3];
        }
        return temp;
    }
}
