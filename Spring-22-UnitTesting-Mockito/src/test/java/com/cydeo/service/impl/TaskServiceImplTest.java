package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import com.cydeo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {


    @MockBean
    private ProjectMapper projectMapper;

    @MockBean
    private UserRepository userRepo;

    @Mock
    TaskRepository taskRepo;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L, -5L})
    void findById_test1(long id) {

        Task task = new Task();

        // given part
        when(taskRepo.findById(id)).thenReturn(Optional.of(task));
        when(taskMapper.convertToDTO(task)).thenReturn(new TaskDTO());

        // when
        taskService.findById(id);

        // then verification
        verify(taskRepo).findById(id);
        verify(taskMapper).convertToDTO(any(Task.class));

        verify(taskRepo, never()).findById(-5L);

    }


    @Test
    void findById_BDD_test2() {

        Task task = new Task();

        // BDD framework
        //Given
        given(taskRepo.findById(anyLong())).willReturn(Optional.of(task));
        given(taskMapper.convertToDTO(task)).willReturn(new TaskDTO());

        // when
        taskService.findById(anyLong());

        // then
        then(taskRepo).should().findById(anyLong());
        then(taskRepo).should(never()).findById(-5L);


    }







}