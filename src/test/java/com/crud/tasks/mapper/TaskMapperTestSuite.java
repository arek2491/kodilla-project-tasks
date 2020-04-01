package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        TaskDto testTaskDto = new TaskDto(1L, "testTitle", "testContent");
        Task testTask = taskMapper.mapToTask(testTaskDto);

        assertEquals(1L, testTask.getId(), 0);
        assertEquals("testTitle", testTask.getTitle());
        assertEquals("testContent", testTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        Task testTask = new Task(1L, "testTitle", "testContent");
        TaskDto testTaskDto = taskMapper.mapToTaskDto(testTask);

        assertEquals(1L, testTask.getId(), 0);
        assertEquals("testTitle", testTask.getTitle());
        assertEquals("testContent", testTask.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        List<Task> testTaskList = new ArrayList<>();
        testTaskList.add(new Task(1L, "testTitle", "testContent"));

        List<TaskDto> testList = taskMapper.mapToTaskDtoList(testTaskList);

        assertEquals(1L, testList.get(0).getId(), 0);
        assertEquals("testTitle", testList.get(0).getTitle());
        assertEquals("testContent", testList.get(0).getContent());
        assertEquals(1, testList.size());
        testList.removeAll(testList);
    }

    @Test
    public void testMapToTaskDtoListWithEmptyList() {
        List<Task> testTaskList = new ArrayList<>();

        List<TaskDto> testList = taskMapper.mapToTaskDtoList(testTaskList);

        assertEquals(0, testList.size());
    }
}
