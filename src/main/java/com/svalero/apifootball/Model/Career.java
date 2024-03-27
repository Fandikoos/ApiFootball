package com.svalero.apifootball.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Career {
    private Team team;
    private String start;
    private String end;
}
