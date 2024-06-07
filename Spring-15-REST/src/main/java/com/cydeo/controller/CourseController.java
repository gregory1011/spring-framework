package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        List<CourseDTO> list = courseService.getCourses();
        return list;

    }

    @GetMapping("{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long id){
        return courseService.getCourseById(id);
    }

    @GetMapping("category/{name}")
    public List<CourseDTO> getCourseByCategory(@PathVariable("name") String category){
        return courseService.getCoursesByCategory(category);
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
