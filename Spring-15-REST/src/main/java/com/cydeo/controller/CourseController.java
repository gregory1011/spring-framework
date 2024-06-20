package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController // @Controller + @ResponseBody
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses(){

        return courseService.getCourses();
    }

    @GetMapping("{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long id){

        return courseService.getCourseById(id);
    }

    @GetMapping("category/{name}")
    public List<CourseDTO> getCourseByCategory(@PathVariable("name") String category){

        return courseService.getCoursesByCategory(category);
    }

    @GetMapping("rating/{rating}")
    public List<CourseDTO> getCourseByRating(@PathVariable("rating") Integer rating){
        return courseService.getCourseByRating(rating);
    }

    @PostMapping()
    public CourseDTO createCourse(@RequestBody CourseDTO course){
        return courseService.createCourse(course);
    }

    @PutMapping("{id}")
    public void updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO course){
        courseService.updateCourse(id, course);
    }

    @DeleteMapping("{id}")
    public void deleteCourseById(@PathVariable("id") Long id){
        courseService.deleteCourseById(id);
    }

    @DeleteMapping
    public void deleteCourses(){
        courseService.deleteCourses();
    }






































}
