package com.crud.tasks.validator;

import com.crud.tasks.domain.Trello;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {
        TrelloList testTrelloList = new TrelloList("1", "testName", false);
        List<TrelloList> testList = new ArrayList<>();
        testList.add(testTrelloList);
        TrelloBoard testTrelloBoard = new TrelloBoard("1", "testName", testList);
        List<TrelloBoard> testTrelloBoardsList = new ArrayList<>();
        testTrelloBoardsList.add(testTrelloBoard);
        trelloValidator.validateTrelloBoards(testTrelloBoardsList);

        assertEquals(1, testTrelloBoardsList.size());
        assertEquals("1", testTrelloBoardsList.get(0).getId());
        assertEquals("testName", testTrelloBoardsList.get(0).getName());
        assertEquals("1", testTrelloBoardsList.get(0).getLists().get(0).getId());
        assertEquals("testName", testTrelloBoardsList.get(0).getLists().get(0).getName());
        assertFalse(testTrelloBoardsList.get(0).getLists().get(0).isClosed());
    }
}
