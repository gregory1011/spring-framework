package com.cydeo.controller;

import com.cydeo.annotation.ExecutionTime;
import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    // injection
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }



    Logger logger = LoggerFactory.getLogger(CourseController.class);


    @GetMapping
    public List<CourseDTO> getAllCourses() {

        // Advice -> When
        logger.info("Before -> Controller: {} - Method {} "," CourseController","getCourses()");

        // bissness logic
        List<CourseDTO> courseDTOList = courseService.getCourses();


        logger.info("After -> Controller: {} - Method : {} - Output : {}"," CourseController","getCourses()", courseDTOList.toString());
        return courseDTOList;
    }

    @ExecutionTime
    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/category/{name}")
    public List<CourseDTO> getCourseByCategory(@PathVariable("name") String category) {
        return courseService.getCoursesByCategory(category)       ;
    }

    @ExecutionTime
    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable("id") Long courseId, @RequestBody CourseDTO course) {
        courseService.updateCourse(courseId, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable("id") Long courseId) {
        courseService.deleteCourseById(courseId);
    }

    @DeleteMapping
    public void deleteCourses() {
        courseService.deleteCourses();
    }

}
