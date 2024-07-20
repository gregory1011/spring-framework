package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {


    @Mock
    ProjectRepository projectRepo;

    @Mock
    ProjectMapper projectMapper;  // this is the interface we want to inject to lose coupling

    @InjectMocks
    ProjectServiceImpl projectService; // this is the class where we inject mocks



    @Test
    void getByProjectCode_test1() {

        //Given part
        Project project = new Project(); // entity
        ProjectDTO projectDTO = new ProjectDTO(); // dto

        // verify if mock methods are run or not
        when(projectRepo.findByProjectCode(anyString())).thenReturn(project);
        when(projectMapper.convertToDto(project)).thenReturn(projectDTO);


        // When part
        ProjectDTO projectDTO1 = projectService.getByProjectCode(anyString());

        // Then part
        //verification if mock methods are run or not
        verify(projectRepo).findByProjectCode(anyString()); // this method accepts any String as param
        verify(projectMapper).convertToDto(any(Project.class)); // or use project entity object

        // assertion
        assertNotNull(projectDTO1);
    }



    @Test
    void getByProjectCode_exception_test2() {

        // when part
        when(projectRepo.findByProjectCode("")).thenThrow(new RuntimeException("Project not found"));

        // then assert
        Throwable exception = assertThrows(RuntimeException.class, () -> projectService.getByProjectCode("PR01"));

//        verify(projectRepo).findByProjectCode(anyString());

        assertEquals("Project not found", exception.getMessage());

    }

    @Test
    void save_test1() {


        Project project = new Project(); // entity
        ProjectDTO projectDTO = new ProjectDTO(); // dto

        // when part
        when(projectMapper.convertToEntity(projectDTO)).thenReturn(project);
        when(projectRepo.save(any())).thenReturn(project);

        // then
        projectService.save(projectDTO);

        // assert
        verify(projectRepo).save(project);
        verify(projectMapper).convertToEntity(any(ProjectDTO.class));

    }

    @Test
    void save_test2() {

    }


}