package com.cydeo.controller;

import com.cydeo.client.EmployeeClient;
import com.cydeo.client.UserClient;
import com.cydeo.dto.ResponseWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Consume_FeignClient {

    private final UserClient userClient;
    private final EmployeeClient employeeClient;


    // this is my own api end points - localhost:/api/v1/users
    @GetMapping("/api/v1/users")
    public ResponseEntity<ResponseWrapper> getUsers(){

        return ResponseEntity.ok(new ResponseWrapper("UserList Retrieved: ", userClient.getUsers())); // new ResponseWrapper("UserList Retrieved: ", userClient.getUsers())
//    return null;
    }


    @GetMapping("/api/v1/employee")
    public ResponseEntity<ResponseWrapper> getEmployee(){

        String  token = "6298ebfecd0551211fce37a6";
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved: ", employeeClient.getEmployees(token)));
//    return null;
    }



}
