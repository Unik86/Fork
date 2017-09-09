package com.fork.calc;

import com.fork.model.Bet;
import com.fork.model.Fork;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ForkTest {

    @Test
    public void testCalcTwo() {
        Bet bet = new Bet();
        bet.setLeft(2.5);
        bet.setRight(1.5);

        Fork fork = new Fork(bet, 100D);
        fork.calc();

        // В = 1/К1 + 1/К2 + 1/К3
        assertThat(fork.getForkRate(), is(1.07));
        // Р = (1/К/В)*С
        assertThat(fork.getSumLeft(), is(37.38));
        assertThat(fork.getSumRight(), is(62.31));

        assertThat(fork.getWinSumLeft(), is(93.45));
        assertThat(fork.getWinSumRight(), is(93.47));
    }

    @Test
    public void testCalcThree() {
        Bet bet = new Bet();
        bet.setLeft(2.5);
        bet.setCenter(2.0);
        bet.setRight(1.5);

        Fork fork = new Fork(bet, 100D);
        fork.calc();

        // В = 1/К1 + 1/К2 + 1/К3
        assertThat(fork.getForkRate(), is(1.57));
        // Р = (1/К/В)*С
        assertThat(fork.getSumLeft(), is(25.48));
        assertThat(fork.getSumCenter(), is(31.85));
        assertThat(fork.getSumRight(), is(42.46));

        assertThat(fork.getWinSumLeft(), is(63.7));
        assertThat(fork.getWinSumCenter(), is(63.7));
        assertThat(fork.getWinSumRight(), is(63.69));
    }

    @Test
    public void testCalcException() {
        Bet bet = new Bet();
        bet.setLeft(null);
        bet.setRight(null);

        Fork fork = new Fork(bet, 100D);
        fork.calc();

        Fork fork2 = new Fork(null, null);
        fork2.calc();
    }

}
