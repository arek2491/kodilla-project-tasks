package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AttachmentsByTypeTestSuite {

    @Test
    public void testGetTrello() {
        Trello testTrello = new Trello(2, 3);
        AttachmentsByType testAttaachmentsByType = new AttachmentsByType(testTrello);
        assertNotNull(testAttaachmentsByType);
        assertEquals(testTrello, testAttaachmentsByType.getTrello());
    }
    @Test
    public void testGetTrelloBoard() {
        AttachmentsByType attachmentsByType = new AttachmentsByType(new Trello(2, 3));
        assertNotNull(attachmentsByType);
        assertEquals(2, attachmentsByType.getTrello().getBoard());
        assertEquals(3, attachmentsByType.getTrello().getCard());
    }
}
