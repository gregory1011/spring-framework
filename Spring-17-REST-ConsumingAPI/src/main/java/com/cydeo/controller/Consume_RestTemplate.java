package com.cydeo.controller;

import com.cydeo.dto.User;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class Consume_RestTemplate {

    private final RestTemplate restTemplate;

    // this will be the 3rd party uri that we plan to consume
    private final String URI= "https://jsonplaceholder.typicode.com/users";

    // we are building own api
    @GetMapping
    public User[] readAllUsers(){

        // first method to consume API - getForEntity();
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URI, User[].class);

        return responseEntity.getBody();
    }

    @GetMapping("{id}")
    public User readUserById(@PathVariable("id") Integer id){

        String URL= URI + "/{id}";
        /// 2nd method to consume API - getForObject();
        return restTemplate.getForObject(URL, User.class, id);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> consumeFromDummyApi(){

        String uri= "https://dummyapi.io/data/v1/user?limit=10";
        String  token = "6298ebfecd0551211fce37a6";


        HttpHeaders  headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id", token); // key= id, value= token

        HttpEntity<String> entity = new HttpEntity<>(headers);
        // 3rd method to consume API - exchange();
        ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);

        return response;
    }



}
