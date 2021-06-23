package com.example.restservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {

    String id;
    String main;
    String description;
    String icon;
}
