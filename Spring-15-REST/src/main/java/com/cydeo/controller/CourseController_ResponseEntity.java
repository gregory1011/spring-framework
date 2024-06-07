package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/api/v2")
public class CourseController_ResponseEntity {

    // this class purpose is to include certain information that my request can take and expect a certain response accordingly
    private final CourseService courseService;

    public CourseController_ResponseEntity(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){

        // we can manipulate however we want . status, headers
        return ResponseEntity
                .status(HttpStatus.ACCEPTED) // status 202
                .header("Version", "Cydeo.V2")
                .header("Operation", "Get List")
                // we can customize body response
                .body(courseService.getCourses());
    }


   @GetMapping("{id}")
   public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long id){

        return ResponseEntity.ok(courseService.getCourseById(id));
   }


    @PostMapping
   public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Operation", "Create")
                .body(courseService.createCourse(course));
    }


}
