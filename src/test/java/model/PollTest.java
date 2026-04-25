package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PollTest {

    private Poll poll;
    private User user;
    private String question;
    private float duration;

    @BeforeEach
    public void setUp() {
        user = new User("john-doe1", "password123", "john.doe@gmail.com", "john_doe.jpg", "John Doe who loves music");
        poll = new Poll(question, duration, user);
    }

    @AfterEach
    public void tearDown() {
        poll = null;
        user = null;
        question = null;
        duration = 0;
    }

    @Test
    public void testConstructor() {
        assertNotNull(poll);
        assertEquals(question, poll.getQuestion());
        assertEquals(duration, poll.getDuration());
        assertEquals(user, poll.getCreator());
        assertFalse(poll.isClosed());
    }

    @Test
    public void testGetQuestion() {
        assertEquals(question, poll.getQuestion());
    }

    @Test
    public void testAddOption() {
        poll.addOption("Option 1");
        poll.addOption("Option 2");
        poll.addOption("Option 3");

        assertEquals(3, poll.getOptions().size());
    }

    @Test
    public void testVote() {
        poll.addOption("Option 1");
        poll.vote("Option 1");

        assertEquals(1, poll.getVotes("Option 1"));
        assertTrue(poll.isVoted("Option 1"));
    }

    @Test
    public void testClose() {
        poll.close();
        assertTrue(poll.isClosed());
    }

    @Test
    public void testIsCreator() {
        assertEquals(user, poll.getCreator());
        assertTrue(poll.isCreator(user));
        assertTrue(poll.isCreator("john-doe1"));

        assertFalse(poll.isCreator("other-user"));
    }

    @Test
    public void testToString() {
        String output = poll.toString();

        assertTrue(output.contains("question="));
        assertTrue(output.contains("options="));
        assertTrue(output.contains("duration="));
        assertTrue(output.contains("creator="));
        assertTrue(output.contains("closed="));
    }
}
