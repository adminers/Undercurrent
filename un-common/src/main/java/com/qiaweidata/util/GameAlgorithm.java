package com.qiaweidata.util;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GameAlgorithm {

    public static void main(String[] args) {
        cardLeft();
    }

    public static void cardLeft() {

        int totalWidth = 200;
        int cardWidth = 25;

        // 每个卡牌间距(人为固定)
        float spacing = 10;

        // 卡牌个数
        int cardNum = 4;

        float everyCardTotalWidth = cardWidth * cardNum;

        float everySpacingTotalWidth = spacing * (cardNum - 1);

        float surplus =  totalWidth - (everyCardTotalWidth + everySpacingTotalWidth);

        // 左侧X
        float result = surplus / 2;

        float rigthX = totalWidth - (result + cardWidth);
        System.out.println("左侧X=" + result + ";右侧X=" + rigthX);
        float temp = result;

        List<Float> cards = new ArrayList<>();
        List<Float> stays = new ArrayList<>();
        cards.add(temp);
        for (int i = 0; i < cardNum - 1; i++) {
            temp = temp + cardWidth + spacing;
            cards.add(temp);
        }

        float stayWidth = 22;

        // 转换落脚点坐标
        cards.forEach(xPoint -> stays.add(xPoint + 17.5F));

        Gson gson = new Gson();
        System.out.println("卡牌：" + gson.toJson(cards));
        System.out.println("落脚：" + gson.toJson(stays));
    }

    /**
     * 卡牌部分运算
     * (作废,不这样计算了)
     */
    public static void card() {

        int totalWidth = 200;
        int cardWidth = 25;
        int leftLength = 30;

        // 每个卡牌间距(需要计算的)
        float spacing = 0;

        // 卡牌个数
        int cardNum = 4;

        // 右侧距离
        int rigthLength = totalWidth - leftLength;

        for (int i = 0; i < cardNum; i++) {

        }
    }
}
