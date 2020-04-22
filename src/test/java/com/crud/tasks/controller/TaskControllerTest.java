package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testGetTasksWithEmptyList() throws Exception {
        List<TaskDto> testTaskDtoList = new ArrayList<>();
        List<Task> testTaskList = new ArrayList<>();

        when(dbService.getAllTasks()).thenReturn(testTaskList);
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(testTaskDtoList);

        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetTasks() throws Exception {
        List<TaskDto> testTaskDtoList = new ArrayList<>();
        List<Task> testTaskList = new ArrayList<>();
        testTaskDtoList.add(new TaskDto(1L, "test title", "test content"));
        testTaskList.add(new Task(1L, "test title", "test content"));

        when(dbService.getAllTasks()).thenReturn(testTaskList);
        when(taskMapper.mapToTaskDtoList(testTaskList)).thenReturn(testTaskDtoList);

        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test title")))
                .andExpect(jsonPath("$[0].content", is("test content")));
    }

    @Test
    public void testGetTask() throws Exception {
        TaskDto testTaskDto = new TaskDto(1L, "test title", "test content");
        Task testTask = new Task(1L, "test title", "test content");

        when(dbService.getTaskById(ArgumentMatchers.anyLong())).thenReturn(testTask);
        when(taskMapper.mapToTaskDto(testTask)).thenReturn(testTaskDto);

        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void testCreateTask() throws Exception {
        TaskDto testTaskDto = new TaskDto(1L, "test title", "test content");
        Task testTask = new Task(1L, "test title", "test content");

        dbService.saveTask(testTask);
        taskMapper.mapToTask(testTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testTaskDto);

        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        TaskDto testTaskDto = new TaskDto(1L, "test title", "test content");
        Task testTask = new Task(1L, "test title", "test content");

        when(dbService.saveTask(taskMapper.mapToTask(testTaskDto))).thenReturn(testTask);
        when(taskMapper.mapToTaskDto(testTask)).thenReturn(testTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testTaskDto);

        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Task task = new Task(1L, "test title", "test content");

        dbService.deleteTask(1L);

        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}