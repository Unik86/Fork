package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.BookMaker;
import com.fork.model.Match;
import com.fork.model.BookMakers;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j
@Component("BWin")
public class BWinParser extends BaseParser{

    private final static String URL = "https://sports.bwin.com/en/sports#sportId=4";
    private final static String MATCHES = "//div[contains(@class, 'ui-widget-content-body')]/div/div/div/div/div/div/div/div/div";

    public BWinParser() {
        pagesStr = "//a[contains(@href, '?page=') and not(contains(@class,'active-page-arrow'))]";
        bookMaker = new BookMaker(BookMakers.BWIN.getName());
    }

    @Override
    public void goToSite(){
        log.info("Enter the site " + URL);

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(URL);

            driver.findElement(By.xpath("//li[contains(@title, 'Today')]/a")).click();

            Thread.sleep(1000);
        } catch (Exception e){
            driver.close();
            log.error("Enter the site failure");
        }
    }

    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info("matches on page = " + cntIds);

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(i);

                String time = element.findElement(By.tagName("div")).getText();
                String url = element.findElement(By.tagName("a")).getAttribute("href");

                List<WebElement> columns = element.findElements(By.tagName("button"));

                List<WebElement>  columnLeft = columns.get(0).findElements(By.tagName("div"));
                List<WebElement>  columnCenter = columns.get(1).findElements(By.tagName("div"));
                List<WebElement>  columnRight = columns.get(2).findElements(By.tagName("div"));

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(columnLeft.get(1).getText()));
                bet.setCenter(Double.parseDouble(columnCenter.get(1).getText()));
                bet.setRight(Double.parseDouble(columnRight.get(1).getText()));


                Match match = new Match();
                match.setBookMaker(BookMakers.BWIN.getName());
                match.setPlayerLeft(columnLeft.get(0).getText());
                match.setPlayerRight(columnRight.get(0).getText());
                match.setTime(time);
                match.setUrl(url);

                match.setWinner(bet);
                bookMaker.getMatches().add(match);
            } catch (Exception e){
                log.error("Pars error");
                continue;
            }
        }
    }

    @Override
    public void parsAllRates() {

    }

}
