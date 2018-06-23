package com.fork.base.model.enums;

import java.util.EnumSet;
import java.util.Set;

import static com.fork.base.model.enums.BookMakers.*;

public class BookMakersGroups {

    public static final Set<BookMakers> ALEX_BOOKMAKERS = EnumSet.of(
            WILLIAMHILL,
            BWIN,
            BET365,
            BET10,
            UNIBET,
            TITANBET
    );

    public static final Set<BookMakers> MY_BOOKMAKERS = EnumSet.of(
            WILLIAMHILL,
            BET365,
            UNIBET,
            PARIMATCH,
            FANSPORT
    );

}
