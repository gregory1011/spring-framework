package com.cydeo.aspect;


import com.cydeo.annotation.Loggable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Aspect
@Configuration
public class LogginAspect {


    Logger logger = LoggerFactory.getLogger(LogginAspect.class);


    // this is the pathern of packages and class name Controller. and methods *
    @Pointcut("execution(* com.cydeo.controller.CourseController.*(..))")
    private void pointCut() {}

//    @Before("pointCut()")
//    public void before() {
//        logger.info("Logger into  -> Working!");
//    }

    // also we can include both in one with same @Before and path
//    @Before("execution(* com.cydeo.controller.CourseController.*(..))")
//    public void beforeAdvice() {
//        logger.info("Logger Advice Controller class execution any method  -> Working!");
//    }


    // rectiving data useing a method from repo : path
    @Pointcut("execution(* com.cydeo.repository.CourseRepository.findById(*))")
    private void findByIdAdvice() {}

//    @Before("findByIdAdvice()")
//    public void beforeFindByIdAdvice(JoinPoint joinPoint) {
//        logger.info("Before-> findById()  -> Method:{} -Arguments: {} - Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
//    }


//  --------------   within (pointcut expretion)   ----------------  >


    // this within is for class level for all classes under controller package
    @Pointcut("within(com.cydeo.controller..*)")
    private void anyControllerOperation() {}

    // import service annotation path: this will hel to monitor class level annotaion. we need all @Service info
    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void anyServiceAnnotation(){}

//    @Before("anyControllerOperation() || anyServiceAnnotation()")
//    public void beforeControllerAdvice(JoinPoint joinPoint) {
//        logger.info("Before () -> Method: {} - Arguments: {} - Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
//    }




   // --------------   @annotation  (pointcut expretion)   ----------------  >


    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void anyDeleteCourseOpetation(){}

//    @Before("anyDeleteCourseOpetation()")
//    public void beforeControllerAdvice(JoinPoint joinPoint) {
//        logger.info("Before ()-> Method: {}, - Arguments: {}, - Target: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs(), joinPoint.getTarget().getClass());
//    }


    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetCourseOpetation(){}

    // single return object
//    @AfterReturning(pointcut = "anyGetCourseOpetation()", returning = "result")
//    private void afterReturningControllerAdvice(JoinPoint joinPoint, Object result) {
//        logger.info("After ()-> Method: {}, result: {}", joinPoint.getSignature().toShortString(), result.toString());
//    }


    // multiple return objects 'List'
//    @AfterReturning(pointcut = "anyGetCourseOpetation()", returning = "result")
//    private void afterReturningControllerAdvice(JoinPoint joinPoint, List<Object> result) {
//        logger.info("After ()-> Method: {}, result: {}", joinPoint.getSignature().toShortString(), result.toString());
//    }


    // exceptiom handling with logger
//    @AfterThrowing(pointcut = "anyGetCourseOpetation()", throwing = "exception")
//    public void afterThrowingControllerAdvice(JoinPoint joinPoint, Exception exception) {
//        logger.info("AfterThrowing ()-> Method: {}, - Exception: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
//    }


    /// monitoring what method i've used in postman ( getById() or getCourses(), or any get method available in Controller class)
//    @After("anyGetCourseOpetation()")
//    public void aftereControllerAdvice(JoinPoint joinPoint) {
//        logger.info("After finnaly -> Method: {}", joinPoint.getSignature().toShortString());
//    }


// --------------  Advice: @Arround  (pointcut expretion)   ----------------  >

    // this helps me to display something before until after advice
    // before execution busniss logic



    // ---------------------- useing custom annotaion -----------------

    @Pointcut("@annotation(com.cydeo.annotation.Loggable)")
    private void anyLoggableOperation() {}


    @Around("anyLoggableOperation()")
    public Object anyLoggableMethodOperationAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Before () -> Method: {}, - Paramenters: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());

        Object result = null;
        try {
            result = joinPoint.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }

        logger.info("After () -> Method: {}, - Result: {}", joinPoint.getSignature().toShortString(), result.toString());
        return result;
    }







}

