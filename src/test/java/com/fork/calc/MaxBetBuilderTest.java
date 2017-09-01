package com.fork.calc;

import com.fork.model.Bet;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MaxBetBuilderTest {

    @Test
    public void testCalcTwo() {
        MaxBetBuilder builder = new MaxBetBuilder();

        Bet bet1 = new Bet();
        bet1.setLeft(2.55);
        bet1.setRight(1.50);

        Bet bet2 = new Bet();
        bet2.setLeft(2.70);
        bet2.setRight(1.35);

        Bet bet3 = new Bet();
        bet3.setLeft(2.60);
        bet3.setRight(1.55);

        Bet maxBet = builder.calc(bet1, bet2, bet3);

        assertThat(maxBet.getLeft(), is(2.70));
        assertThat(maxBet.getRight(), is(1.55));
    }

    @Test
    public void testCalcThree() {
        MaxBetBuilder builder = new MaxBetBuilder();

        Bet bet1 = new Bet();
        bet1.setLeft(2.55);
        bet1.setCenter(1.90);
        bet1.setRight(1.50);

        Bet bet2 = new Bet();
        bet2.setLeft(2.70);
        bet2.setCenter(2.05);
        bet2.setRight(1.35);

        Bet bet3 = new Bet();
        bet3.setLeft(2.60);
        bet3.setCenter(1.95);
        bet3.setRight(1.55);

        Bet maxBet = builder.calc(bet1, bet2, bet3);

        assertThat(maxBet.getLeft(), is(2.70));
        assertThat(maxBet.getCenter(), is(2.05));
        assertThat(maxBet.getRight(), is(1.55));
    }

    @Test
    public void testCalcException() {
        MaxBetBuilder builder = new MaxBetBuilder();

        Bet bet1 = new Bet();
        bet1.setLeft(null);
        bet1.setRight(null);

        Bet maxBet = builder.calc(bet1);

        List<Bet> bets = null;
        Bet maxBet2 = builder.calc(bets);
    }
}
