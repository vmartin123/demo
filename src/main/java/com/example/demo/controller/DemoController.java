package com.example.demo.controller;

import com.example.demo.dto.Wind;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController
public class DemoController {
    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/demo")
    public Wind demo() throws IOException {
        String uri ="https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (2460286)&format=json";

        String inputString = restTemplate.getForObject(uri, ObjectNode.class)
                .get("query")
                .get("results")
                .get("channel")
                .get("wind")
                .toString();

        return new ObjectMapper().readValue(inputString, Wind.class);
    }

}
