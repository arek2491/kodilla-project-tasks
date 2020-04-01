package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloBoardTestSuite {

    @Test
    public void getTrelloBoard() {
        TrelloListDto testTrelloListDto = new TrelloListDto("1", "testName", false);
        List<TrelloListDto> testList = new ArrayList<>();
        testList.add(testTrelloListDto);
        TrelloBoardDto testTrelloBoardDto = new TrelloBoardDto("1", "testName", testList);

        assertNotNull(testList);
        assertNotNull(testTrelloBoardDto);
        assertEquals("1", testTrelloBoardDto.getId());
        assertEquals("testName", testTrelloBoardDto.getName());
        assertEquals("1", testList.get(0).getId());
        assertEquals("testName", testList.get(0).getName());
        assertEquals(1, testList.size());
        assertFalse(testList.get(0).isClosed());
    }

    @Test
    public void testGetTrelloBoardWhenTrelloListIsEmpty() {

        List<TrelloListDto> testList = new ArrayList<>();
        TrelloBoardDto testTrelloBoardDto = new TrelloBoardDto("1", "testName", testList);

        assertNotNull(testTrelloBoardDto);
        assertEquals("1", testTrelloBoardDto.getId());
        assertEquals("testName", testTrelloBoardDto.getName());
        assertEquals(0, testList.size());
    }
}
