package com.fork.live.model;

import com.fork.base.model.Bet;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LiveMatch")
public class LiveMatch {

    private String bookMaker;

    private Bet winner;

    private Bet set1Game1;
    private Bet set1Game2;
    private Bet set1Game3;
    private Bet set1Game4;
    private Bet set1Game5;
    private Bet set1Game6;
    private Bet set1Game7;
    private Bet set1Game8;
    private Bet set1Game9;
    private Bet set1Game10;
    private Bet set1Game11;
    private Bet set1Game12;

    private Bet set2Game1;
    private Bet set2Game2;
    private Bet set2Game3;
    private Bet set2Game4;
    private Bet set2Game5;
    private Bet set2Game6;
    private Bet set2Game7;
    private Bet set2Game8;
    private Bet set2Game9;
    private Bet set2Game10;
    private Bet set2Game11;
    private Bet set2Game12;

    private Bet set3Game1;
    private Bet set3Game2;
    private Bet set3Game3;
    private Bet set3Game4;
    private Bet set3Game5;
    private Bet set3Game6;
    private Bet set3Game7;
    private Bet set3Game8;
    private Bet set3Game9;
    private Bet set3Game10;
    private Bet set3Game11;
    private Bet set3Game12;

    private Bet total05;
    private Bet total15;
    private Bet total25;
    private Bet total35;
    private Bet total45;
    private Bet total55;
    private Bet total65;
    private Bet total75;
    private Bet total85;
    private Bet total95;

    public LiveMatch(String bookMaker) {
        this.bookMaker = bookMaker;
    }

    public String getBookMaker() {
        return bookMaker;
    }

    public void setBookMaker(String bookMaker) {
        this.bookMaker = bookMaker;
    }

    public Bet getWinner() {
        return winner;
    }

    public void setWinner(Bet winner) {
        this.winner = winner;
    }

    public Bet getSet1Game1() {
        return set1Game1;
    }

    public void setSet1Game1(Bet set1Game1) {
        this.set1Game1 = set1Game1;
    }

    public Bet getSet1Game2() {
        return set1Game2;
    }

    public void setSet1Game2(Bet set1Game2) {
        this.set1Game2 = set1Game2;
    }

    public Bet getSet1Game3() {
        return set1Game3;
    }

    public void setSet1Game3(Bet set1Game3) {
        this.set1Game3 = set1Game3;
    }

    public Bet getSet1Game4() {
        return set1Game4;
    }

    public void setSet1Game4(Bet set1Game4) {
        this.set1Game4 = set1Game4;
    }

    public Bet getSet1Game5() {
        return set1Game5;
    }

    public void setSet1Game5(Bet set1Game5) {
        this.set1Game5 = set1Game5;
    }

    public Bet getSet1Game6() {
        return set1Game6;
    }

    public void setSet1Game6(Bet set1Game6) {
        this.set1Game6 = set1Game6;
    }

    public Bet getSet1Game7() {
        return set1Game7;
    }

    public void setSet1Game7(Bet set1Game7) {
        this.set1Game7 = set1Game7;
    }

    public Bet getSet1Game8() {
        return set1Game8;
    }

    public void setSet1Game8(Bet set1Game8) {
        this.set1Game8 = set1Game8;
    }

    public Bet getSet1Game9() {
        return set1Game9;
    }

    public void setSet1Game9(Bet set1Game9) {
        this.set1Game9 = set1Game9;
    }

    public Bet getSet1Game10() {
        return set1Game10;
    }

    public void setSet1Game10(Bet set1Game10) {
        this.set1Game10 = set1Game10;
    }

    public Bet getSet1Game11() {
        return set1Game11;
    }

    public void setSet1Game11(Bet set1Game11) {
        this.set1Game11 = set1Game11;
    }

    public Bet getSet1Game12() {
        return set1Game12;
    }

    public void setSet1Game12(Bet set1Game12) {
        this.set1Game12 = set1Game12;
    }

    public Bet getSet2Game1() {
        return set2Game1;
    }

    public void setSet2Game1(Bet set2Game1) {
        this.set2Game1 = set2Game1;
    }

    public Bet getSet2Game2() {
        return set2Game2;
    }

    public void setSet2Game2(Bet set2Game2) {
        this.set2Game2 = set2Game2;
    }

    public Bet getSet2Game3() {
        return set2Game3;
    }

    public void setSet2Game3(Bet set2Game3) {
        this.set2Game3 = set2Game3;
    }

    public Bet getSet2Game4() {
        return set2Game4;
    }

    public void setSet2Game4(Bet set2Game4) {
        this.set2Game4 = set2Game4;
    }

    public Bet getSet2Game5() {
        return set2Game5;
    }

    public void setSet2Game5(Bet set2Game5) {
        this.set2Game5 = set2Game5;
    }

    public Bet getSet2Game6() {
        return set2Game6;
    }

    public void setSet2Game6(Bet set2Game6) {
        this.set2Game6 = set2Game6;
    }

    public Bet getSet2Game7() {
        return set2Game7;
    }

    public void setSet2Game7(Bet set2Game7) {
        this.set2Game7 = set2Game7;
    }

    public Bet getSet2Game8() {
        return set2Game8;
    }

    public void setSet2Game8(Bet set2Game8) {
        this.set2Game8 = set2Game8;
    }

    public Bet getSet2Game9() {
        return set2Game9;
    }

    public void setSet2Game9(Bet set2Game9) {
        this.set2Game9 = set2Game9;
    }

    public Bet getSet2Game10() {
        return set2Game10;
    }

    public void setSet2Game10(Bet set2Game10) {
        this.set2Game10 = set2Game10;
    }

    public Bet getSet2Game11() {
        return set2Game11;
    }

    public void setSet2Game11(Bet set2Game11) {
        this.set2Game11 = set2Game11;
    }

    public Bet getSet2Game12() {
        return set2Game12;
    }

    public void setSet2Game12(Bet set2Game12) {
        this.set2Game12 = set2Game12;
    }

    public Bet getSet3Game1() {
        return set3Game1;
    }

    public void setSet3Game1(Bet set3Game1) {
        this.set3Game1 = set3Game1;
    }

    public Bet getSet3Game2() {
        return set3Game2;
    }

    public void setSet3Game2(Bet set3Game2) {
        this.set3Game2 = set3Game2;
    }

    public Bet getSet3Game3() {
        return set3Game3;
    }

    public void setSet3Game3(Bet set3Game3) {
        this.set3Game3 = set3Game3;
    }

    public Bet getSet3Game4() {
        return set3Game4;
    }

    public void setSet3Game4(Bet set3Game4) {
        this.set3Game4 = set3Game4;
    }

    public Bet getSet3Game5() {
        return set3Game5;
    }

    public void setSet3Game5(Bet set3Game5) {
        this.set3Game5 = set3Game5;
    }

    public Bet getSet3Game6() {
        return set3Game6;
    }

    public void setSet3Game6(Bet set3Game6) {
        this.set3Game6 = set3Game6;
    }

    public Bet getSet3Game7() {
        return set3Game7;
    }

    public void setSet3Game7(Bet set3Game7) {
        this.set3Game7 = set3Game7;
    }

    public Bet getSet3Game8() {
        return set3Game8;
    }

    public void setSet3Game8(Bet set3Game8) {
        this.set3Game8 = set3Game8;
    }

    public Bet getSet3Game9() {
        return set3Game9;
    }

    public void setSet3Game9(Bet set3Game9) {
        this.set3Game9 = set3Game9;
    }

    public Bet getSet3Game10() {
        return set3Game10;
    }

    public void setSet3Game10(Bet set3Game10) {
        this.set3Game10 = set3Game10;
    }

    public Bet getSet3Game11() {
        return set3Game11;
    }

    public void setSet3Game11(Bet set3Game11) {
        this.set3Game11 = set3Game11;
    }

    public Bet getSet3Game12() {
        return set3Game12;
    }

    public void setSet3Game12(Bet set3Game12) {
        this.set3Game12 = set3Game12;
    }

    public Bet getTotal05() {
        return total05;
    }

    public void setTotal05(Bet total05) {
        this.total05 = total05;
    }

    public Bet getTotal15() {
        return total15;
    }

    public void setTotal15(Bet total15) {
        this.total15 = total15;
    }

    public Bet getTotal25() {
        return total25;
    }

    public void setTotal25(Bet total25) {
        this.total25 = total25;
    }

    public Bet getTotal35() {
        return total35;
    }

    public void setTotal35(Bet total35) {
        this.total35 = total35;
    }

    public Bet getTotal45() {
        return total45;
    }

    public void setTotal45(Bet total45) {
        this.total45 = total45;
    }

    public Bet getTotal55() {
        return total55;
    }

    public void setTotal55(Bet total55) {
        this.total55 = total55;
    }

    public Bet getTotal65() {
        return total65;
    }

    public void setTotal65(Bet total65) {
        this.total65 = total65;
    }

    public Bet getTotal75() {
        return total75;
    }

    public void setTotal75(Bet total75) {
        this.total75 = total75;
    }

    public Bet getTotal85() {
        return total85;
    }

    public void setTotal85(Bet total85) {
        this.total85 = total85;
    }

    public Bet getTotal95() {
        return total95;
    }

    public void setTotal95(Bet total95) {
        this.total95 = total95;
    }
}
