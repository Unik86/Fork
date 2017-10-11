package com.fork.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.fork.util.Utils.round;

@Document(collection = "TwoOfThree")
public class TwoOfThree {

    @Getter @Setter
    private List<Match> matches;

    @Getter
    private Bet bet;

    @Getter
    private Double rate;

    public TwoOfThree() {

    }

    public TwoOfThree(Bet bet) {
        this.bet = bet;
    }

    public void calc() {
        if (bet == null || bet.getRight() == null || bet.getLeft() == null)
            return;

        if (bet.getLeft() < 2.1)
            calcRate(bet.getLeft(), bet.getCenter());
        else if (bet.getRight() < 2.1)
            calcRate(bet.getRight(), bet.getCenter());

    }

    private void calcRate(Double winner, Double center){
        // В = 1/К1 + 1/К2
        rate = 1 / winner + 1 / center;
        rate = round(rate, 1000);
    }

    public boolean isHasGoodRate(){
        return rate != null && rate < 0.81;
    }

}
