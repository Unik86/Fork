package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.BookMaker;
import com.fork.model.enums.BookMakers;
import com.fork.model.Match;
import com.fork.util.Constants;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Log4j
@Component("Bet365")
public class Bet365Parser extends BaseParser{

    private final static String URL = "https://www.bet365.com";
    private final static String MATCHES = "//div[contains(@class, 'gl-MarketGroupContainer')]";
    private final static String SOCCER_LINK = "//div[contains(@class, 'wn-Classification') and contains(text(),'Soccer')]";

    public Bet365Parser() {
        pagesStr = "//div[contains(@class, 'sm-CouponLink_Label') and contains(text(),'s Matches')]";
        bookMaker = new BookMaker(BookMakers.BET365.getName());
    }

    @Override
    public void goToSite(){
        log.info("Enter the site " + URL);

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(URL);

            driver.findElement(By.xpath("//a[contains(@class, 'lpdgl') and contains(text(),'English')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(SOCCER_LINK)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(@class, 'hm-DropDownSelections_DropLink') and contains(text(),'Odds')]")).click();
            driver.findElement(By.xpath("//a[contains(@class, 'hm-DropDownSelections_Item') and contains(text(),'Decimal')]")).click();
            Thread.sleep(1000);
        } catch (Exception e){
            driver.close();
            log.error("Enter the site failure");
        }
    }

    @Override
    public void parsMainRates(){
        log.info("Pars main rates");
        bookMaker.getMatches().clear();
        int cntPages = 0;

        if(nonNull(pagesStr))
            cntPages = driver.findElements(By.xpath(pagesStr)).size();

        log.info("count pages = " + cntPages);

        for(int i = 0; i < cntPages; i++){
            try {
                driver.findElements(By.xpath(pagesStr)).get(i).click();

                Thread.sleep(2000);
                log.info("page = " + (i+1));

                parsOnePageMainRates();

                driver.findElement(By.xpath(SOCCER_LINK)).click();
                Thread.sleep(1000);
            } catch (Exception e){

            }
        }

        log.info("matchs size = " + bookMaker.getMatches().size());
    }

    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info("matches on page = " + cntIds);

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement base = driver.findElements(By.xpath(MATCHES + "/div[1]")).get(i);
                WebElement leftRate = driver.findElements(By.xpath(MATCHES + "/div[2]")).get(i);
                WebElement centerRate = driver.findElements(By.xpath(MATCHES + "/div[3]")).get(i);
                WebElement rightRate = driver.findElements(By.xpath(MATCHES + "/div[4]")).get(i);

                List<WebElement> names = base.findElements(By.className("sl-CouponParticipantWithBookCloses_NameContainer"));
                List<WebElement> times = base.findElements(By.className("sl-CouponParticipantWithBookCloses_LeftSideContainer"));

                List<WebElement> leftRates = leftRate.findElements(By.className("gl-ParticipantOddsOnly_Odds"));
                List<WebElement> centerRates = centerRate.findElements(By.className("gl-ParticipantOddsOnly_Odds"));
                List<WebElement> rightRates = rightRate.findElements(By.className("gl-ParticipantOddsOnly_Odds"));

                for(int j = 1; j < names.size(); j++) {
                    WebElement name = names.get(j);
                    String nameStr = name.getText();

                    if(!nameStr.contains(Constants.SEPARATOR_NAME))
                        continue;

                    String[] namesStr = nameStr.split(Constants.SEPARATOR_NAME);
//                    String url = base.findElement(By.tagName("a")).getAttribute("href");

                    Bet bet = new Bet();
                    bet.setLeft(Double.parseDouble(leftRates.get(j).getText()));
                    bet.setCenter(Double.parseDouble(centerRates.get(j).getText()));
                    bet.setRight(Double.parseDouble(rightRates.get(j).getText()));


                    Match match = new Match();
                    match.setBookMaker(BookMakers.BET365.getName());
                    match.setPlayerLeft(namesStr[0].trim());
                    match.setPlayerRight(namesStr[1].trim());
                    match.setTime(times.get(j).getText());
                    match.setUrl(null);

                    match.setWinner(bet);
                    bookMaker.getMatches().add(match);
                }
            } catch (Exception e){
                log.error("Pars error");
                continue;
            }
        }
    }

}
