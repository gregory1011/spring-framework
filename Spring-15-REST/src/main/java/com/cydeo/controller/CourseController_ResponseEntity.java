package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/courses/api/v2")
public class CourseController_ResponseEntity {

    // this class purpose is to include certain information that my request can take and expect a certain response accordingly
    private final CourseService courseService;


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

    @GetMapping("rating/{rating}")
    public ResponseEntity<List<CourseDTO>> getCourseByRating(@PathVariable("rating") Integer rating){

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("service", "All set")
                .body(courseService.getCourseByRating(rating));
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

    @PutMapping("{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable("id") Integer id, @RequestBody CourseDTO course){

        CourseDTO foundStudent = courseService.getCourseById(id);
        foundStudent.setName(course.getName());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(foundStudent);
    }

    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable("id") Integer id){

        ResponseEntity.status(HttpStatus.ACCEPTED)
                        .header("Id ="+id, "was deleted");
        courseService.deleteCourseById(id);
    }


}
