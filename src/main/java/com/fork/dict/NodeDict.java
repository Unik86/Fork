package com.fork.dict;

import java.util.Arrays;
import java.util.List;

public enum NodeDict {

    WINNER(1, Arrays.asList("Победитель встречи Live")),

    MORE_LESS_0_5(2, Arrays.asList("Игра - Больше/Меньше 0.5 голов Live")),
    MORE_LESS_1_5(3, Arrays.asList("Игра - Больше/Меньше 1.5 голов Live")),
    MORE_LESS_2_5(4, Arrays.asList("Игра - Больше/Меньше 2.5 голов Live")),
    MORE_LESS_3_5(5, Arrays.asList("Игра - Больше/Меньше 3.5 голов Live")),
    MORE_LESS_4_5(6, Arrays.asList("Игра - Больше/Меньше 4.5 голов Live")),
    MORE_LESS_5_5(7, Arrays.asList("Игра - Больше/Меньше 5.5 голов Live")),
    MORE_LESS_6_5(8, Arrays.asList("Игра - Больше/Меньше 6.5 голов Live")),
    MORE_LESS_7_5(9, Arrays.asList("Игра - Больше/Меньше 7.5 голов Live"));

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
                if(str.equals(name))
                    return val.getCode();
            }
        }
        return null;
    }

}
