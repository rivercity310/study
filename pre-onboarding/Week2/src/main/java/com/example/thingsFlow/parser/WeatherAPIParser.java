package com.example.thingsFlow.parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalTime;

/** 날씨를 4시간 간격으로 가져온다.
 - API 요청마다 무거운 로직을 접근하지 않도록 Memo
 - 두 시간 간격이 4시간 이상 차이날 때 API 호출을 통해 weather 값 갱신
 */
public class WeatherAPIParser {
    private LocalTime lastCallTime;
    private String weather;
    private static WeatherAPIParser weatherAPIParser;

    private WeatherAPIParser() { }

    private String call() {
        final String url = "http://api.weatherapi.com/v1/forecast.json";
        final String key = "fb9a3d940471413a88f102715230601";

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        return WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(url)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", key)
                        .queryParam("q", "south%20korea")
                        .queryParam("days", 1)
                        .queryParam("aqi", "no")
                        .queryParam("alerts", "no")
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private String parsing() {
        String jsonStr = this.call();
        String weather = "";
        JSONParser jsonParser = new JSONParser();

        try {
            Object result = jsonParser.parse(jsonStr);

            // ClassCastException 방지
            if (result instanceof JSONObject jsonObject) {
                JSONObject current = (JSONObject) jsonObject.get("current");
                JSONObject condition = (JSONObject) current.get("condition");
                weather = condition.get("text").toString();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return weather;
    }

    public static WeatherAPIParser getInstance() {
        if (weatherAPIParser == null) weatherAPIParser = new WeatherAPIParser();
        return weatherAPIParser;
    }

    public String getCurrentWeather() {
        if (lastCallTime == null) lastCallTime = LocalTime.now();
        int lastCallHour = lastCallTime.getHour();

        LocalTime curCallTime = LocalTime.now();
        int curCallHour = curCallTime.getHour();

        // 4시간이 지났으면 새로 정보를 받아오고 호출 시간을 필드에 저장
        if (weather == null || curCallHour - lastCallHour >= 4) {
            weather = parsing();
            lastCallTime = curCallTime;
        }

        return weather;
    }
}