package com.svalero.apifootball.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {

    private int id;
    private String name;
    private String address;
    private String city;
    private int capacity;
    private String surface;
    private String image;
}
