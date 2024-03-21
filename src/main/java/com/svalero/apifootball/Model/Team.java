package com.svalero.apifootball.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private int id;
    private String name;
    private String code;
    private String country;
    private int founded;
    private boolean national;
    private String logo;

}
