package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AIShuffleTest {

    @Test
    void getPlaylistMood() {
        // create a AIShuffle object check that the getter returns the correct playlist mood
        AIShuffle ai = new AIShuffle("Chill", "Relaxed", "Song A");
        assertEquals("Chill", ai.getPlaylistMood());
    }

    @Test
    void setPlaylistMood() {
        // change the playlist mood the setter updates the value correctly
        AIShuffle ai = new AIShuffle("Old", "Relaxed", "Song A");
        ai.setPlaylistMood("Energetic");
        assertEquals("Energetic", ai.getPlaylistMood());
    }

    @Test
    void getUserMood() {
        // the getter returns the correct user mood
        AIShuffle ai = new AIShuffle("Chill", "Happy", "Song A");
        assertEquals("Happy", ai.getUserMood());
    }

    @Test
    void setUserMood() {
        // update the user mood
        AIShuffle ai = new AIShuffle("Chill", "Old", "Song A");
        ai.setUserMood("Sad");
        assertEquals("Sad", ai.getUserMood());
    }

    @Test
    void getLastRecommended() {
        // check that the getter returns the right last recommended track
        AIShuffle ai = new AIShuffle("Chill", "Relaxed", "model.Track X");
        assertEquals("model.Track X", ai.getLastRecommended());
    }

    @Test
    void setLastRecommended() {
        // update the last recommended track and verify the change
        AIShuffle ai = new AIShuffle("Chill", "Relaxed", "Old model.Track");
        ai.setLastRecommended("New Track");
        assertEquals("New Track", ai.getLastRecommended());
    }

    @Test
    void getRecommendationStrength() {
        // exact mood match should return 100
        AIShuffle ai = new AIShuffle("Chill", "Chill", "Song A");
        assertEquals(100, ai.getRecommendationStrength());

        // similar moods return 70
        AIShuffle ai2 = new AIShuffle("Energetic", "Happy", "Song B");
        assertEquals(70, ai2.getRecommendationStrength());

        // different moods should return 30
        AIShuffle ai3 = new AIShuffle("Chill", "Sad", "Song C");
        assertEquals(30, ai3.getRecommendationStrength());
    }

    @Test
    void testToString() {
        // toString output contains has all fields
        AIShuffle ai = new AIShuffle("Chill", "Relaxed", "Song A");
        String output = ai.toString();

        assertTrue(output.contains("Chill"));
        assertTrue(output.contains("Relaxed"));
        assertTrue(output.contains("Song A"));
    }
}
