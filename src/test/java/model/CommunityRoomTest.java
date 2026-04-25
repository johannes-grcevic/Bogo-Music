package model;

import ie.setu.bogomusic.model.CommunityRoom;
import ie.setu.bogomusic.model.Poll;
import ie.setu.bogomusic.model.Track;
import ie.setu.bogomusic.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CommunityRoomTest {

    private CommunityRoom room;
    private List<User> users;
    private HashMap<String, Track> trackQueue;

    @BeforeEach
    public void setUp() {
        users = new ArrayList<>();
        users.add(new User("user1", "password1", "john-doe@gmail.com", "profilePicture1", "bio1"));

        trackQueue = new HashMap<>();
        trackQueue.put("track1", new Track("track1", "artist1", 100, "Lossless"));

        room = new CommunityRoom(users, trackQueue);
    }

    @AfterEach
    public void tearDown() {
        users = null;
        trackQueue = null;
        room = null;
    }

    @Test
    public void testConstructor() {
        assertNotNull(room);
        assertEquals(users, room.getConnectedUsers());
        assertEquals(trackQueue, room.getTrackQueue());
    }

    @Test
    public void testSetCurrentTrack() {
        Track track = new Track("track2", "artist2", 200, "Hi-Res");
        room.setCurrentTrack(track);

        assertEquals(track, room.getCurrentTrack());
    }

    @Test
    public void testSetNextTrack() {
        Track track = new Track("track2", "artist2", 200, "Hi-Res");
        room.setNextTrack(track);

        assertEquals(track, room.getNextTrack());
    }

    @Test
    public void testSetPreviousTrack() {
        Track track = new Track("track2", "artist2", 200, "Hi-Res");
        room.setPreviousTrack(track);

        assertEquals(track, room.getPreviousTrack());
    }

    @Test
    public void testAddTrackToQueue() {
        Track track = new Track("track2", "artist2", 200, "Hi-Res");
        room.addTrackToQueue(track);

        assertEquals(track, room.getTrackQueue().get("track2"));
        assertEquals(2, room.getTrackQueue().size());
        assertTrue(room.getTrackQueue().containsKey("track2"));
    }

    @Test
    public void testRemoveTrackFromQueue() {
        Track track = new Track("track2", "artist2", 200, "Hi-Res");
        room.removeTrackFromQueue(track);

        assertEquals(1, room.getTrackQueue().size());
        assertFalse(room.getTrackQueue().containsKey("track2"));
        assertNull(room.getTrackQueue().get("track2"));
    }

    @Test
    public void testAddUser() {
        User newUser = new User("john-doe2", "Password124!", "john-doe2@gmail.com", "profilePicture2", "bio2");

        assertTrue(room.addUser(newUser));

        assertTrue(room.getConnectedUsers().contains(newUser));
        assertEquals(2, room.getConnectedUsers().size());
        assertEquals(users, room.getConnectedUsers());
    }

    @Test
    public void testRemoveUser() {
        User user = new User("user1", "password1", "john-doe@gmail.com", "profilePicture1", "bio1");
        users.add(user);

        assertTrue(room.removeUser(user));

        assertFalse(room.getConnectedUsers().contains(user));
        assertEquals(1, room.getConnectedUsers().size());
        assertEquals(users, room.getConnectedUsers());
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> room.getConnectedUsers().get(1));
    }

    @Test
    public void testBanUser() {
        User user = new User("john-doe2", "Password124!", "john-doe2@gmail.com", "profilePicture2", "bio2");
        room.addUser(user);

        assertTrue(room.banUser(user));

        // should not be in connected users list
        assertFalse(room.getConnectedUsers().contains(user));
        assertEquals(1, room.getConnectedUsers().size());
        assertEquals(users, room.getConnectedUsers());

        // should be in banned users list
        assertTrue(room.getBannedUsers().contains(user));
        assertEquals(1, room.getBannedUsers().size());

        // should not ban all users
        assertNotEquals(users, room.getBannedUsers());
    }

    @Test
    public void testUnbanUser() {
        User user = new User("john-doe2", "Password124!", "john-doe2@gmail.com", "profilePicture2", "bio2");
        room.addUser(user);
        room.banUser(user);

        assertTrue(room.unbanUser(user));

        // should not be in banned users list
        assertFalse(room.getBannedUsers().contains(user));
        assertEquals(0, room.getBannedUsers().size());

        // should not be in connected users list as they have to reconnect to the room
        assertFalse(room.getConnectedUsers().contains(user));
        assertEquals(1, room.getConnectedUsers().size());
        assertEquals(users, room.getConnectedUsers());
    }

    @Test
    public void testAddChat() {
        User user = new User("john-doe2", "Password124!", "john-doe2@gmail.com", "profilePicture2", "bio2");
        room.addChat(user, "Hello everyone!");

        assertEquals(1, room.getChatMessages().size());
        assertEquals("Hello everyone!", room.getChatMessages(user));
        assertNotEquals("Hi everyone!", room.getChatMessages(user));
    }

    @Test
    public void testRemoveChat() {
        User user = new User("john-doe2", "Password124!", "john-doe2@gmail.com", "profilePicture2", "bio2");
        room.addChat(user, "Hello everyone!");

        assertTrue(room.removeChat(user, "Hello everyone!"));

        assertEquals(0, room.getChatMessages().size());
        assertEquals("", room.getChatMessages(user));
        assertNotEquals("Hello everyone!", room.getChatMessages(user));
        assertNotEquals("Hi everyone!", room.getChatMessages(user));
    }

    @Test
    public void testClearChat() {
        room.addChat(users.getFirst(), "Hello everyone!");
        room.clearChat();

        assertEquals(0, room.getChatMessages().size());
        assertEquals("", room.getChatMessages(users.getFirst()));

        // user should not have any chat messages
        assertNotEquals("Hello everyone!", room.getChatMessages(users.getFirst()));
    }

    @Test
    public void testClearTrackQueue() {
        room.addTrackToQueue(trackQueue.get("track1"));
        room.clearTrackQueue();

        assertEquals(0, room.getTrackQueue().size());
        assertNull(room.getTrackQueue().get("track1"));
        assertNull(room.getCurrentTrack());
        assertNull(room.getNextTrack());
        assertNull(room.getPreviousTrack());
    }

    @Test
    public void testAddPoll() {
        User user = new User("john-doe2", "Password124!", "john-doe2@gmail.com", "profilePicture2", "bio2");
        Poll newPoll = new Poll("What is your favorite song?", 10, user);

        room.addPoll(user, newPoll);

        assertEquals(1, room.getPolls(user).size());
        assertEquals(newPoll, room.getPolls(user).getFirst());
        assertTrue(room.getPolls(user).contains(newPoll));
    }

    @Test
    public void testRemovePoll() {
        User user = new User("john-doe2", "Password124!", "john-doe2@gmail.com", "profilePicture2", "bio2");
        Poll poll = new Poll("What is your favorite song?", 10, user);

        room.addPoll(user, poll);
        room.removePoll(user, poll);

        assertEquals(0, room.getPolls(user).size());
        assertThrowsExactly(NoSuchElementException.class, () -> room.getPolls(user).getFirst());
        assertFalse(room.getPolls(user).contains(poll));
    }

    @Test
    public void testClosePoll() {
        Poll poll = new Poll("What is your favorite song?", 10, users.getFirst());
        room.addPoll(users.getFirst(), poll);

        room.closePoll(poll);
        assertTrue(poll.isClosed());
        assertTrue(room.getPolls(users.getFirst()).contains(poll));
        assertEquals(1, room.getPolls(users.getFirst()).size());
    }

    @Test
    public void testGetMostPopularTrack() {
        Track track2 = new Track("track2", "artist1", 100, "Lossless");
        Track track3 = new Track("track3", "artist2", 200, "Hi-Res");
        Track track4 = new Track("track4", "artist3", 300, "Hi-Res");

        room.addTrackToQueue(track2);
        room.addTrackToQueue(track3);
        room.addTrackToQueue(track4);

        // 4 likes for track 2
        track2.addLike();
        track2.addLike();
        track2.addLike();
        track2.addLike();

        // 1 like for track 3
        track3.addLike();

        // 2 likes for track 4
        track4.addLike();
        track4.addLike();

        // track 1 with 4 likes should be the most popular track
        assertNotNull(room.getMostPopularTrack());
        assertEquals(track2, room.getMostPopularTrack());
    }

    @Test
    public void testGetLeastPopularTrack() {
        Track track2 = new Track("track2", "artist1", 100, "Lossless");
        Track track3 = new Track("track3", "artist2", 200, "Hi-Res");
        Track track4 = new Track("track4", "artist3", 300, "Hi-Res");

        room.addTrackToQueue(track2);
        room.addTrackToQueue(track3);
        room.addTrackToQueue(track4);

        // 4 dislikes for track 2
        track2.addDislike();
        track2.addDislike();
        track2.addDislike();
        track2.addDislike();

        // 1 dislike for track 3
        track2.addDislike();

        // 2 dislikes for track 4
        track3.addDislike();
        track3.addDislike();

        // track 1 with 4 dislikes should be the least popular track
        assertEquals(track2, room.getLeastPopularTrack());
    }

    @Test
    public void testShuffleTrackQueue() {
        Track track1 = new Track("track1", "artist1", 100, "Lossless");
        Track track2 = new Track("track2", "artist2", 200, "Hi-Res");
        Track track3 = new Track("track3", "artist3", 300, "Hi-Res");
        Track track4 = new Track("track4", "artist4", 400, "Hi-Res");
        Track track5 = new Track("track5", "artist5", 500, "Hi-Res");
        Track track6 = new Track("track6", "artist6", 600, "Hi-Res");
        Track track7 = new Track("track7", "artist7", 700, "Hi-Res");
        Track track8 = new Track("track8", "artist8", 800, "Hi-Res");
        Track track9 = new Track("track9", "artist9", 900, "Hi-Res");
        Track track10 = new Track("track10", "artist10", 1000, "Hi-Res");
        Track track11 = new Track("track11", "artist11", 1100, "Hi-Res");
        Track track12 = new Track("track12", "artist12", 1200, "Hi-Res");
        Track track13 = new Track("track13", "artist13", 1300, "Hi-Res");
        Track track14 = new Track("track14", "artist14", 1400, "Hi-Res");
        Track track15 = new Track("track15", "artist15", 1500, "Hi-Res");
        Track track16 = new Track("track16", "artist16", 1600, "Hi-Res");
        Track track17 = new Track("track17", "artist17", 1700, "Hi-Res");
        Track track18 = new Track("track18", "artist18", 1800, "Hi-Res");
        Track track19 = new Track("track19", "artist19", 1900, "Hi-Res");
        Track track20 = new Track("track20", "artist20", 2000, "Hi-Res");

        room.clearTrackQueue();
        room.addTrackToQueue(track1);
        room.addTrackToQueue(track2);
        room.addTrackToQueue(track3);
        room.addTrackToQueue(track4);
        room.addTrackToQueue(track5);
        room.addTrackToQueue(track6);
        room.addTrackToQueue(track7);
        room.addTrackToQueue(track8);
        room.addTrackToQueue(track9);
        room.addTrackToQueue(track10);
        room.addTrackToQueue(track11);
        room.addTrackToQueue(track12);
        room.addTrackToQueue(track13);
        room.addTrackToQueue(track14);
        room.addTrackToQueue(track15);
        room.addTrackToQueue(track16);
        room.addTrackToQueue(track17);
        room.addTrackToQueue(track18);
        room.addTrackToQueue(track19);
        room.addTrackToQueue(track20);

        HashMap<String, Track> unshuffledTrackQueue = room.getTrackQueue();
        room.shuffleTrackQueue();

        // should be the same reference
        assertSame(unshuffledTrackQueue, room.getTrackQueue());

        // should be the same objects
        assertEquals(unshuffledTrackQueue, room.getTrackQueue());

        // should be the same size
        assertEquals(unshuffledTrackQueue.size(), room.getTrackQueue().size());
        assertEquals(20, room.getTrackQueue().size());
    }
}
