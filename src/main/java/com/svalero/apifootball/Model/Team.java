package com.svalero.apifootball.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private int id;
    private String name;
    private String code;
    private String country;
    private int founded;
    private String logo;

}
