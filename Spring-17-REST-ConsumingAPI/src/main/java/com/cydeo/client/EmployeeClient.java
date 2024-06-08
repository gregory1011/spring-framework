package com.cydeo.client;

import com.cydeo.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(url = "https://dummyapi.io", name = "EMPLOYEE-CLIENT")
public interface EmployeeClient {

    @GetMapping("/data/v1/users?limit=10")
    Employee getEmployees(@RequestHeader("app-id") String in);

    // https://dummyapi.io/data/v1/user?limit=10
}