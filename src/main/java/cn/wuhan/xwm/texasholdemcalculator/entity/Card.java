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
    private Flower flower;//♠️:Spade  ♥️:Heart  ♣️:Club  ♦️:Diamond.

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
        cs[0]   =   0x2660; //♠️的ascii码
        cs[1]   =   0x2665; //♥️的ascii码
        cs[2]   =   0x2663; //♣️的ascii码
        cs[3]   =   0x2666; //♦️的ascii码
        if(this.getFlower()==Flower.SPADE){
            temp+="♠️";
        }else if(this.getFlower()==Flower.HEART){
            temp+="♥️";
        }else if(this.getFlower()==Flower.CLUB){
            temp+="♣️️";
        }else if(this.getFlower()==Flower.DIAMOND){
            temp+="♦️";
        }
        return temp;
    }
}
