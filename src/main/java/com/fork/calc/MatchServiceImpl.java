package com.fork.calc;

import com.fork.model.Bet;
import com.fork.model.Fork;
import com.fork.model.Match;
import info.debatty.java.stringsimilarity.JaroWinkler;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class MatchServiceImpl implements MatchService {

    private List<Fork> forks = new ArrayList<>();

    @Override
    public List<Fork> getForks() {
        return forks;
    }

    @Override
    public void findForkForMainRates(List<Match> ... sites){
        log.info("Find Fork For Main Rates");
        List<List<Match>> mathes = rearrange(sites);
        calcForkForMainRates(mathes);
    }

    private List<List<Match>> rearrange(List<Match>[] sites){
        JaroWinkler similar = new JaroWinkler();
        List<List<Match>> bets = new ArrayList<>();

        // TODO for many sites
        for(Match match1 : sites[0]){
            for(Match match2 : sites[1]){
                Double left = similar.similarity(match1.getPlayerLeft(), match2.getPlayerLeft());
                Double right = similar.similarity(match1.getPlayerRight(), match2.getPlayerRight());

                if(left > 0.8 && right > 0.8){
                    List<Match> list = new ArrayList<>();
                    list.add(match1);
                    list.add(match2);
                    bets.add(list);
                }

            }
        }

        log.info("Similar mathes = " + bets.size());
        return bets;
    }

    private void calcForkForMainRates(List<List<Match>> matches){
        for (List<Match> list : matches) {
            List<Bet> bets = new ArrayList<>();

            for(Match match : list){
                bets.add(match.getWinner());
            }

            MaxBetBuilder builder = new MaxBetBuilder();
            Bet maxBet = builder.calc(bets);

            Fork fork = new Fork(maxBet);
            fork.calc();

            if(fork.isHasFork()){
                forks.add(fork);
            }
        }

        log.info("Forks = " + forks);
    }

    @Override
    public void findFork(Match... matches){
        List<List<Bet>> bets = rearrange(matches);
        calcFork(bets);
    }

    private void calcFork(List<List<Bet>> bets){
        for (List<Bet> list : bets) {
            MaxBetBuilder builder = new MaxBetBuilder();
            Bet maxBet = builder.calc(list);

            Fork fork = new Fork(maxBet);
            fork.calc();

            if(fork.isHasFork()){

            }
        }
    }

    private List<List<Bet>> rearrange(Match[] matches){
        List<List<Bet>> bets = new ArrayList<>();
        List<Bet> winners = new ArrayList<>();

        List<Bet> total05s = new ArrayList<>();
        List<Bet> total15s = new ArrayList<>();
        List<Bet> total25s = new ArrayList<>();
        List<Bet> total35s = new ArrayList<>();
        List<Bet> total45s = new ArrayList<>();
        List<Bet> total55s = new ArrayList<>();
        List<Bet> total65s = new ArrayList<>();
        List<Bet> total75s = new ArrayList<>();
        List<Bet> total85s = new ArrayList<>();
        List<Bet> total95s = new ArrayList<>();

        for (Match match : matches) {
            winners.add(match.getWinner());

            total05s.add(match.getTotal05());
            total15s.add(match.getTotal15());
            total25s.add(match.getTotal25());
            total35s.add(match.getTotal35());
            total45s.add(match.getTotal45());
            total55s.add(match.getTotal55());
            total65s.add(match.getTotal65());
            total75s.add(match.getTotal75());
            total85s.add(match.getTotal85());
            total95s.add(match.getTotal95());
        }

        addList(bets, winners);

        addList(bets, total05s);
        addList(bets, total15s);
        addList(bets, total25s);
        addList(bets, total35s);
        addList(bets, total45s);
        addList(bets, total55s);
        addList(bets, total65s);
        addList(bets, total75s);
        addList(bets, total85s);
        addList(bets, total95s);

        return bets;
    }

    private void addList(List<List<Bet>> bets, List<Bet> list){
        if(!list.isEmpty())
            bets.add(list);
    }

}
