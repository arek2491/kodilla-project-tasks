package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestsSuite {

    @InjectMocks
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        List<TrelloBoardDto> testTrelloBoardDto = new ArrayList<>();
        List<TrelloListDto> testTrelloListDto = new ArrayList<>();
        testTrelloListDto.add(new TrelloListDto("1", "testList", false));
        testTrelloBoardDto.add(new TrelloBoardDto("1", "testBoard", testTrelloListDto));

        List<TrelloBoard> testList = trelloMapper.mapToBoards(testTrelloBoardDto);

        assertNotNull(testList);
        assertEquals("1", testList.get(0).getId());
        assertEquals("testBoard", testList.get(0).getName());
        assertEquals("1", testList.get(0).getLists().get(0).getId());
        assertEquals("testList", testList.get(0).getLists().get(0).getName());
        assertFalse(testList.get(0).getLists().get(0).isClosed());
        assertEquals(1, testList.size());
        testList.removeAll(testList);
    }

    @Test
    public void testMapToBoardsWithTrelloListIsEmpty() {
        List<TrelloBoardDto> testTrelloBoardDto = new ArrayList<>();
        List<TrelloListDto> testTrelloListDto = new ArrayList<>();
        testTrelloBoardDto.add(new TrelloBoardDto("1", "testBoard", testTrelloListDto));
        List<TrelloBoard> testList = trelloMapper.mapToBoards(testTrelloBoardDto);
        assertNotNull(testList);
        assertEquals(1, testList.size());
        assertTrue(testList.get(0).getLists().isEmpty());
    }

    @Test
    public void testMapToBoardsWithTrelloBoardsIsEmpty() {
        List<TrelloBoardDto> testTrelloBoardDto = new ArrayList<>();
        List<TrelloBoard> testList = trelloMapper.mapToBoards(testTrelloBoardDto);

        assertNotNull(testList);
        assertTrue(testList.isEmpty());
    }

    @Test
    public void testMapToBoardsDto() {
        List<TrelloList> testTrelloList = new ArrayList<>();
        List<TrelloBoard> testTrelloBoard = new ArrayList<>();
        testTrelloList.add(new TrelloList("1", "testList", false));
        testTrelloBoard.add(new TrelloBoard("1", "testBoard", testTrelloList));

        List<TrelloBoardDto> testList = trelloMapper.mapToBoardsDto(testTrelloBoard);

        assertNotNull(testList);
        assertEquals("1", testList.get(0).getId());
        assertEquals("testBoard", testList.get(0).getName());
        assertEquals("1", testList.get(0).getLists().get(0).getId());
        assertEquals("testList", testList.get(0).getLists().get(0).getName());
        assertFalse(testList.get(0).getLists().get(0).isClosed());
        assertEquals(1, testList.size());
        testList.removeAll(testList);
    }

    @Test
    public void testMapToBoardsDtoWhenTrelloListIsEmpty() {
        List<TrelloList> testTrelloList = new ArrayList<>();
        List<TrelloBoard> testTrelloBoard = new ArrayList<>();
        testTrelloBoard.add(new TrelloBoard("1", "testBoard", testTrelloList));

        List<TrelloBoardDto> testList = trelloMapper.mapToBoardsDto(testTrelloBoard);

        assertNotNull(testList);
        assertEquals(1, testList.size());
        assertTrue(testList.get(0).getLists().isEmpty());
    }

    @Test
    public void testMapToBoardDtoWhenTrelloBoardsIsEmpty() {
        List<TrelloBoard> testTrelloBoard = new ArrayList<>();
        List<TrelloBoardDto> testList = trelloMapper.mapToBoardsDto(testTrelloBoard);

        assertNotNull(testList);
        assertTrue(testList.isEmpty());
    }

    @Test
    public void testMapToList() {
        List<TrelloListDto> testTrelloListDto = new ArrayList<>();
        testTrelloListDto.add(new TrelloListDto("1", "testList", false));

        List<TrelloList> testList = trelloMapper.mapToList(testTrelloListDto);

        assertNotNull(testList);
        assertEquals("1", testList.get(0).getId());
        assertEquals("testList", testList.get(0).getName());
        assertFalse(testList.get(0).isClosed());
        testList.removeAll(testList);
    }

    @Test
    public void testMapToListWhenTrelloListDtoIsEmpty() {
        List<TrelloListDto> testTrelloListDto = new ArrayList<>();
        List<TrelloList> testList = trelloMapper.mapToList(testTrelloListDto);

        assertNotNull(testList);
        assertTrue(testList.isEmpty());
    }

    @Test
    public void testMapToListDto() {
        List<TrelloList> testTrelloList = new ArrayList<>();
        testTrelloList.add(new TrelloList("1", "testList", false));

        List<TrelloListDto> testList = trelloMapper.mapToListDto(testTrelloList);

        assertNotNull(testList);
        assertEquals("1", testList.get(0).getId());
        assertEquals("testList", testList.get(0).getName());
        assertFalse(testList.get(0).isClosed());
    }

    @Test
    public void testMapToListDtoWhenTrelloListIsEmpty() {
        List<TrelloList> testTrelloList = new ArrayList<>();
        List<TrelloListDto> testList = trelloMapper.mapToListDto(testTrelloList);

        assertNotNull(testList);
        assertTrue(testList.isEmpty());
    }

    @Test
    public void testMapToCardDto() {
        TrelloCard testTrelloCard = new TrelloCard("testName", "testDescription", "testPos", "testListId");
        TrelloCardDto testTrelloCardDto = trelloMapper.mapToCardDto(testTrelloCard);

        assertEquals("testName", testTrelloCardDto.getName());
        assertEquals("testDescription", testTrelloCardDto.getDescription());
        assertEquals("testPos", testTrelloCardDto.getPos());
        assertEquals("testListId", testTrelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        TrelloCardDto testTrelloCardDto = new TrelloCardDto("testName", "testDescription", "testPos", "testListId");
        TrelloCard testTrelloCard = trelloMapper.mapToCard(testTrelloCardDto);

        assertEquals("testName", testTrelloCard.getName());
        assertEquals("testDescription", testTrelloCard.getDescription());
        assertEquals("testPos", testTrelloCard.getPos());
        assertEquals("testListId", testTrelloCard.getListId());
    }
}
