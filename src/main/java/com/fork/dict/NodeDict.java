package com.fork.dict;

import java.util.Arrays;
import java.util.List;

public enum NodeDict {

    WINNER(1, Arrays.asList("Победитель встречи Live", "Победа")),

    MORE_LESS_0_5(2, Arrays.asList("Игра - Больше/Меньше", "Тотал"));

    private Integer code;
    private List<String> names;

    NodeDict(Integer code, List<String> names) {
        this.code = code;
        this.names = names;
    }

    public Integer getCode() {
        return code;
    }

    public List<String> getNames() {
        return names;
    }

    public static Integer findCode(String name){
        for(NodeDict val : NodeDict.values()){
            for (String str : val.getNames()) {
                if(str.equals(name) || name.contains(str) || str.contains(name))
                    return val.getCode();
            }
        }
        return null;
    }

}
