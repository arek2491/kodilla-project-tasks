package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BadgesTestSuite {

    @Test
    public void testGetBadges() {
        Trello testTrello = new Trello(2,3);
        AttachmentsByType testAttachmentsByType = new AttachmentsByType(testTrello);
        BadgesDto testBadges = new BadgesDto(1, testAttachmentsByType);

        assertNotNull(testBadges);
        assertNotNull(testAttachmentsByType);
        assertNotNull(testTrello);
        assertEquals(testAttachmentsByType, testBadges.getAttachments());
        assertEquals(testTrello, testBadges.getAttachments().getTrello());
        assertEquals(1, testBadges.getVotes());
    }

    @Test
    public void testGetBadgesWhenTrelloIsEmpty() {
        Trello testTrello = null;
        BadgesDto testBadgesDto = new BadgesDto(1, new AttachmentsByType(testTrello));

        assertNotNull(testBadgesDto);
        assertEquals(1, testBadgesDto.getVotes());
        assertNull(testBadgesDto.getAttachments().getTrello());
    }

    @Test
    public void testGetBadgesWhenAttachmentsIsEmpty() {
        AttachmentsByType testAttachmentsByType = null;
        BadgesDto testBadgesDto = new BadgesDto(1, testAttachmentsByType);

        assertNotNull(testBadgesDto);
        assertEquals(1, testBadgesDto.getVotes());
        assertNull(testBadgesDto.getAttachments());
    }

    @Test
    public void testGetVotes() {
        Trello testTrello = new Trello(2,3);
        AttachmentsByType testAttachmentsByType = new AttachmentsByType(testTrello);
        BadgesDto testBadges = new BadgesDto(1, testAttachmentsByType);

        assertEquals(1, testBadges.getVotes());
    }

    @Test
    public void testGetAttachmentsByType() {
        Trello testTrello = new Trello(2,3);
        AttachmentsByType testAttachmentsByType = new AttachmentsByType(testTrello);
        BadgesDto testBadges = new BadgesDto(1, testAttachmentsByType);

        assertEquals(2, testBadges.getAttachments().getTrello().getBoard());
        assertEquals(3, testBadges.getAttachments().getTrello().getCard());
    }
}
