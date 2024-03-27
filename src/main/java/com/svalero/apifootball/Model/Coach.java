package com.svalero.apifootball.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    private int id;
    private String name;
    private String firstname;
    private String lastname;
    private int age;
    private String nationality;
    private String photo;
    private Team team;
}
