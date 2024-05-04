package com.cydeo.repository;

import com.cydeo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface CourseRepo extends JpaRepository<Course, Long> {

    // ------ Below are DRIVE Query --------- //

    // find all courses by category
    List<Course> findByCategory(String category);

    // find all courses by category and order the entities by name
    List<Course> findByCategoryOrderByName(String category);

    // check if a course with the provided name exists. return true if course exists, false otherwise
     boolean existsByName(String name);

     // returns the count of courses for the provided category
    int countByCategory(String category);

    // find all courses that start with provided course name.
    List<Course> findByNameStartingWith(String name);

    // find all courses by category and returns a stream
    Stream<Course> streamByCategory(String category);

      // ------ DRIVE Query  End--------- //



    // Using JPQL queries

    @Query("select c from Course c where c.category = ?1 and c.rating > ?2")
    List<Course> findAllByCategoryAndRatingGreaterThan(String category, int rating);

    @Query("select c from Course c where c.category = :category and c.rating < :rating")
    List<Course> findAllByCategoryAndRatingLessThan(@Param("category") String category, @Param("rating") int rating);





}
