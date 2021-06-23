package com.example.restservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    Coord coord;
    ArrayList<Weather> weather;
    String base;
    Main main;
    String visibility;
    Wind wind;
    Clouds clouds;
    String dt;
    Sys sys;
    String timezone;
    String  id;
    String  name;
    String  cod;
}
