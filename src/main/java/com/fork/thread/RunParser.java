package com.fork.thread;

import com.fork.parser.Parser;
import com.fork.repository.BookMakerRepository;
import lombok.extern.log4j.Log4j;

@Log4j
public class RunParser implements Runnable {

    private Parser parser;
    private BookMakerRepository bookMakerRepository;

    public RunParser(Parser parser, BookMakerRepository bookMakerRepository) {
        this.parser = parser;
        this.bookMakerRepository = bookMakerRepository;
    }

    @Override
    public void run() {
        log.info("Run parser >>>> " + getBookMakerName());

        parser.goToSite();
        parser.parsMainRates();
        parser.closeBrowser();

        bookMakerRepository.deleteByNameAndSportType(
                parser.getBookMaker().getName(),
                parser.getBookMaker().getSportType()
        );
        bookMakerRepository.save(parser.getBookMaker());
        log.info("Finish parser >>>>>>>>>> " + getBookMakerName());
    }

    private String getBookMakerName(){
        return parser.getBookMaker().getName();
    }
}
