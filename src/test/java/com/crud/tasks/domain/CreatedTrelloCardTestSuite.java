package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CreatedTrelloCardTestSuite {

    @Test
    public void testCreateTrelloCard() {
        CreatedTrelloCardDto testCreatedTrelloCard = new CreatedTrelloCardDto("1", "testName", "testUrl");

        assertNotNull(testCreatedTrelloCard);
        assertEquals("1", testCreatedTrelloCard.getId());
        assertEquals("testName", testCreatedTrelloCard.getName());
        assertEquals("testUrl", testCreatedTrelloCard.getShortUrl());
    }

    @Test
    public void testCreateTrelloCardWhenItIsNull() {
        CreatedTrelloCardDto testCreatedTrelloCardDto = null;
        assertNull(testCreatedTrelloCardDto);
    }

    @Test
    public void testGetId() {
        CreatedTrelloCardDto testCreatedTrelloCard = new CreatedTrelloCardDto("1", "testName", "testUrl");

        assertEquals("1", testCreatedTrelloCard.getId());
    }

    @Test
    public void testGetName() {
        CreatedTrelloCardDto testCreatedTrelloCard = new CreatedTrelloCardDto("1", "testName", "testUrl");

        assertEquals("testName", testCreatedTrelloCard.getName());
    }

    @Test
    public void testGetUrl() {
        CreatedTrelloCardDto testCreatedTrelloCard = new CreatedTrelloCardDto("1", "testName", "testUrl");
        assertEquals("testUrl", testCreatedTrelloCard.getShortUrl());
    }
}
