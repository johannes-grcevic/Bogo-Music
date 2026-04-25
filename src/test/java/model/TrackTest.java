package model;

import ie.setu.bogomusic.model.Track;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrackTest {

    @Test
    void getTitle() {
        Track t = new Track("Song A", "Artist X", 200, "Lossless");
        assertEquals("Song A", t.getTitle());
    }

    @Test
    void setTitle() {
        Track t = new Track("Old Title", "Artist X", 200, "Lossless");
        t.setTitle("New Title");
        assertEquals("New Title", t.getTitle());
    }

    @Test
    void getArtist() {
        Track t = new Track("Song A", "Artist X", 200, "Lossless");
        assertEquals("Artist X", t.getArtist());
    }

    @Test
    void setArtist() {
        Track t = new Track("Song A", "Old Artist", 200, "Lossless");
        t.setArtist("New Artist");
        assertEquals("New Artist", t.getArtist());
    }

    @Test
    void getDuration() {
        Track t = new Track("Song A", "Artist X", 185, "Lossless");
        assertEquals(185, t.getDuration());
    }

    @Test
    void setDuration() {
        Track t = new Track("Song A", "Artist X", 100, "Lossless");
        t.setDuration(240);
        assertEquals(240, t.getDuration());
    }

    @Test
    void getAudioQuality() {
        Track t = new Track("Song A", "Artist X", 200, "Hi-Res");
        assertEquals("Hi-Res", t.getAudioQuality());
    }

    @Test
    void setAudioQuality() {
        Track t = new Track("Song A", "Artist X", 200, "Lossless");
        t.setAudioQuality("Hi-Res");
        assertEquals("Hi-Res", t.getAudioQuality());
    }

    @Test
    public void testAddLikeDislike() {
        Track t = new Track("Song A", "Artist X", 200, "Lossless");

        t.addLike();
        assertEquals(1, t.getLikes());

        t.addDislike();
        assertEquals(1, t.getDislikes());

        for (long i = 0; i < 9999999999L; i++) {
            t.addLike();
            t.addDislike();
        }

        // should support very high likes and dislikes
        assertEquals(9999999999L + 1, t.getLikes());
        assertEquals(9999999999L + 1, t.getDislikes());
    }

    @Test
    public void testRemoveLikeDislike() {
        Track t = new Track("Song A", "Artist X", 200, "Lossless");

        t.addLike();
        t.addDislike();

        t.removeLike();
        t.removeDislike();
        assertEquals(0, t.getLikes());
        assertEquals(0, t.getDislikes());

        for (long i = 0; i < 99999999L; i++) {
            t.removeLike();
            t.removeDislike();
        }

        // should not go below 0
        assertEquals(0, t.getLikes());
        assertEquals(0, t.getDislikes());
    }

    @Test
    void getFormattedDuration() {
        Track t = new Track("Song A", "Artist X", 185, "Lossless");
        assertEquals("3:05", t.getFormattedDuration());
    }

    @Test
    void testToString() {
        Track t = new Track("Song A", "Artist X", 200, "Lossless");
        String output = t.toString();

        assertTrue(output.contains("Song A"));
        assertTrue(output.contains("Artist X"));
        assertTrue(output.contains("3:20"));
        assertTrue(output.contains("Lossless"));
    }
}
