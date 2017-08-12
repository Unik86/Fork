package com.fork.calc;

import com.fork.model.Bet;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MaxBetBuilderTest {

    @Test
    public void testCalc() {
        MaxBetBuilder builder = new MaxBetBuilder();

        Bet bat1 = new Bet(2.55, 1.50);
        Bet bat2 = new Bet(2.70, 1.35);
        Bet bat3 = new Bet(2.60, 1.55);

        Bet maxBet = builder.calc(bat1, bat2, bat3);

        assertThat(maxBet.getRight(), is(2.70));
        assertThat(maxBet.getLeft(), is(1.55));
    }

}
