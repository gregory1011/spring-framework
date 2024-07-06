package com.cydeo.service.impl;

import com.cydeo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {



    @Mock
    UserRepository userRepo;

    @InjectMocks
    UserServiceImpl userService;


    @Test
    void deleteByUsername_test1(){

        String username = "mikesmith@cydeo.com";
        userService.deleteByUserName(username);

//        verify(userRepo).deleteByUserName(username);

        // we can use to run nr of time the method we call deleteByUsername
//        verify(userRepo, times(2)).deleteByUserName(username);

        // this method will run atLeastOne time
//        verify(userRepo, atLeastOnce()).deleteByUserName(username);

//        verify(userRepo, atMostOnce()).deleteByUserName(username);

//        verify(userRepo, atLeast(4)).deleteByUserName(username);



        InOrder inOrder = Mockito.inOrder(userRepo);

        inOrder.verify(userRepo).deleteByUserName(username);
        inOrder.verify(userRepo).findAll();






    }






}