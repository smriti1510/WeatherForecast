package com.example.restservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.ResponseWrapper;

@RestController
@EnableCaching
@Cacheable("CacheName")
public class WeatherController {

    String API_KEY = "ffa6f13ea40a22452bba3be726315d3f";

    public static Map<String,Object> jsonToMap(String str){
        Map<String,Object> map = new Gson().fromJson(str,new
                TypeToken<HashMap<String,Object>>() {}.getType());
        return map;
    }

    @GetMapping("/weatherbycity")
    public Forecast weatherbycity(@RequestParam(value = "name", defaultValue = "World") String name) {
        StringBuilder result = new StringBuilder();
        Forecast forecast= new Forecast();
        {

            try {
                String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=" + API_KEY + "&units=imperial";

                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                 rd.close();
                 Gson g = new Gson();
                forecast = g.fromJson(result.toString(), Forecast.class);

            } catch (Exception e) {
            }
        }
        return forecast;
    }

    @GetMapping("/weatherbycoordinates")
    public Forecast weatherbycoordinates(@RequestParam(value = "name", defaultValue = "World") String name) {
        StringBuilder result = new StringBuilder();
        JsonObject jsonObj=new JsonObject();
        Forecast forecast= new Forecast();
        {
            try {
                String lat[] = StringUtils.split(name, ",");
                String urlStringLa = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat[0] + "&lon=" + lat[1] + "&appid=ffa6f13ea40a22452bba3be726315d3f&units=imperial";

                URL url = new URL(urlStringLa);
                URLConnection conn = url.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                Gson g = new Gson();
                forecast = g.fromJson(result.toString(), Forecast.class);
            } catch (Exception e) {
            }
        }
        return forecast;
    }
}
