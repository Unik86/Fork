package com.fork.calc;

import com.fork.model.Bet;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ForkTest {

    @Test
    public void testCalc() {
        Bet bet = new Bet(2.5D, 1.5D);

        Fork fork = new Fork(bet, 100D);
        fork.calc();

        // В = 1/К1 + 1/К2 + 1/К3
        assertThat(fork.getForkRate(), is(1.07D));
        // Р = (1/К/В)*С
        assertThat(fork.getSumRight(), is(37.38D));
        assertThat(fork.getSumLeft(), is(62.31D));

        assertThat(fork.getWinSumRight(), is(93.45D));
        assertThat(fork.getWinSumLeft(), is(93.47D));
    }

}
