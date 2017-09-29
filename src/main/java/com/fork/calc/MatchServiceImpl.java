package com.fork.calc;

import com.fork.model.*;
import info.debatty.java.stringsimilarity.JaroWinkler;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class MatchServiceImpl implements MatchService {

    private static double SIMILARITY_FACTOR = 0.8;

    private List<Fork> forks = new ArrayList<>();
    private List<TwoOfThree> twoOfThrees = new ArrayList<>();

    @Override
    public List<Fork> getForks() {
        return forks;
    }

    @Override
    public List<TwoOfThree> getTwoOfThrees() {
        return twoOfThrees;
    }

    @Override
    public void findForkForMainRates(List<BookMaker> bookMakers){
        log.info("Find Fork For Main Rates");
        List<List<Match>> mathes = rearrange(bookMakers);
        calcForkForMainRates(mathes);
        calcTwoOfThreeForMainRates(mathes);
    }

    private List<List<Match>> rearrange(List<BookMaker> bookMakers){
        JaroWinkler similar = new JaroWinkler();
        List<Match> allMatches = new ArrayList<>();
        List<Match> allMatches2 = new ArrayList<>();
        List<List<Match>> similarMatches = new ArrayList<>();
        List<List<Match>> result = new ArrayList<>();

        for(BookMaker bookMaker : bookMakers){
            allMatches.addAll(bookMaker.getMatches());
        }

        allMatches2.addAll(allMatches);

        for(Match match1 : allMatches){
            allMatches2.remove(match1);
            List<Match> list = new ArrayList<>();
            List<Match> listU = new ArrayList<>();

            if(match1.getPlayerLeft().contains("U19")
                    || match1.getPlayerLeft().contains("U20")
                    || match1.getPlayerLeft().contains("U21")
                    || match1.getPlayerLeft().contains("Reserves")
                    || match1.getPlayerLeft().contains("Youth"))
                listU.add(match1);
            else
                list.add(match1);

            for(Match match2 : allMatches2){
                Double left = similar.similarity(match1.getPlayerLeft(), match2.getPlayerLeft());
                Double right = similar.similarity(match1.getPlayerRight(), match2.getPlayerRight());

                if(left > SIMILARITY_FACTOR && right > SIMILARITY_FACTOR){
                    if(match2.getPlayerLeft().contains("U19")
                            || match2.getPlayerLeft().contains("U20")
                            || match2.getPlayerLeft().contains("U21")
                            || match2.getPlayerLeft().contains("Reserves")
                            || match2.getPlayerLeft().contains("Youth"))
                        listU.add(match2);
                    else
                        list.add(match2);
                }
            }

            for(Match match : list){
                allMatches2.remove(match);
            }

            for(Match match : listU){
                allMatches2.remove(match);
            }

            similarMatches.add(list);
            similarMatches.add(listU);
        }

        for(List<Match> matches : similarMatches){
            if(matches.size() > 1)
                result.add(matches);
        }

        log.info("Similar mathes = " + result.size());
        return result;
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
                fork.setMatches(list);
                forks.add(fork);
            }
        }

        log.info("Forks = " + forks.size());
    }

    private void calcTwoOfThreeForMainRates(List<List<Match>> matches){
        for (List<Match> list : matches) {
            List<Bet> bets = new ArrayList<>();

            for(Match match : list){
                bets.add(match.getWinner());
            }

            MaxBetBuilder builder = new MaxBetBuilder();
            Bet maxBet = builder.calc(bets);

            TwoOfThree twoOfThree = new TwoOfThree(maxBet);
            twoOfThree.calc();

            if(twoOfThree.isHasGoodRate()){
                twoOfThree.setMatches(list);
                twoOfThrees.add(twoOfThree);
            }
        }

        log.info("TwoOfThrees = " + twoOfThrees.size());
    }

    @Override
    public void findFork(FullMatch... matches){
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

    private List<List<Bet>> rearrange(FullMatch[] matches){
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

        for (FullMatch match : matches) {
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
