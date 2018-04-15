package com.fork.live.model;

import com.fork.base.model.Fork;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Document(collection = "Live")
public class Live {

    @Getter @Setter
    private Date time;

    @Getter @Setter
    private List<Fork> forks = new ArrayList<>();

    public String getTimeStr(){
        if(isNull(time))
            return "-";

        return new SimpleDateFormat("HH:mm:ss").format(time);
    }
}
