package com.example.calculator.DAO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDTO {
    private String nam;
    private String cal;
    private String str;
    private String res;
    private Long id;
    private boolean liked;

    public DataDTO(String nam, String cal, String str, String res,Long id,boolean liked) {
        this.nam = nam;
        this.cal = cal;
        this.str = str;
        this.res = res;
        this.id=id;
        this.liked=liked;
    }
}
