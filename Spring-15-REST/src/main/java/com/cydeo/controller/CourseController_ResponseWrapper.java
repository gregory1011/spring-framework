package com.cydeo.controller;

import com.cydeo.entity.ResponseWrapper;
import com.cydeo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses/api/v3")
public class CourseController_ResponseWrapper {
    // this class purpose is to include certain information that my request can take and expect a certain response accordingly
    // first we crated a class ResponseWrapper and define it, then create a certain constructor we can use in here to customize our response
    private final CourseService courseService;

    public CourseController_ResponseWrapper(CourseService courseService) {
        this.courseService = courseService;
    }

    // this time we are returning costume class ResponseWrapper instead on DTO,
    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllCourses(){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Version", "Cydeo.V3")
                // constructor ResponseWrapper with 2 args.
                .body(new ResponseWrapper("successfully retrieved", courseService.getCourses()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWrapper> getCourseById(@PathVariable("id") Long courseId){
        return ResponseEntity.ok(new ResponseWrapper("course: " + courseId + " retrieved", courseService.getCourseById(courseId)));
    }

}
