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

        Bet bat1 = new Bet(2.55, 1.50);
        Bet bat2 = new Bet(2.70, 1.35);
        Bet bat3 = new Bet(2.60, 1.55);

        Bet maxBet = builder.calc(bat1, bat2, bat3);

        assertThat(maxBet.getLeft(), is(2.70));
        assertThat(maxBet.getRight(), is(1.55));
    }

    @Test
    public void testCalcThree() {
        MaxBetBuilder builder = new MaxBetBuilder();

        Bet bat1 = new Bet(2.55, 1.9, 1.50);
        Bet bat2 = new Bet(2.70, 2.05, 1.35);
        Bet bat3 = new Bet(2.60, 1.95, 1.55);

        Bet maxBet = builder.calc(bat1, bat2, bat3);

        assertThat(maxBet.getLeft(), is(2.70));
        assertThat(maxBet.getCenter(), is(2.05));
        assertThat(maxBet.getRight(), is(1.55));
    }

    @Test
    public void testCalcException() {
        MaxBetBuilder builder = new MaxBetBuilder();

        Bet bat1 = new Bet(null, null);
        Bet maxBet = builder.calc(bat1);

        List<Bet> bets = null;
        Bet maxBet2 = builder.calc(bets);
    }
}
