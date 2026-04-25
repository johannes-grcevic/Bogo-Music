package model;

import java.util.*;

public class CommunityRoom {
    private final List<User> connectedUsers;
    private final List<User> bannedUsers = new ArrayList<>();
    private final HashMap<User, String> chats = new HashMap<>();
    private final HashMap<User, Poll> polls = new HashMap<>();
    private final HashMap<String, Track> trackQueue;

    private Track currentTrack;
    private Track nextTrack;
    private Track previousTrack;

    public CommunityRoom(List<User> users, HashMap<String, Track> trackQueue) {
        this.connectedUsers = users;
        this.trackQueue = trackQueue;
    }

    public List<User> getConnectedUsers() {
        return connectedUsers;
    }

    public HashMap<String, Track> getTrackQueue() {
        return trackQueue;
    }

    public Track getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(Track currentTrack) {
        this.currentTrack = currentTrack;
    }

    public Track getNextTrack() {
        return nextTrack;
    }

    public void setNextTrack(Track nextTrack) {
        this.nextTrack = nextTrack;
    }

    public Track getPreviousTrack() {
        return previousTrack;
    }

    public void setPreviousTrack(Track previousTrack) {
        this.previousTrack = previousTrack;
    }

    public void addTrackToQueue(Track track) {
        if (!trackQueue.containsKey(track.getTitle())) {
            trackQueue.put(track.getTitle(), track);
        }
    }

    public void removeTrackFromQueue(Track track) {
        if (track != null) {
            trackQueue.remove(track.getTitle());
        }
    }

    public boolean addUser(User user) {
        if (!connectedUsers.contains(user)) {
            return connectedUsers.add(user);
        }

        return false;
    }

    public boolean removeUser(User user) {
        if (user != null) {
            return connectedUsers.remove(user);
        }

        return false;
    }

    public List<User> getBannedUsers() {
        return bannedUsers;
    }

    public boolean banUser(User user) {
        if (user != null && !bannedUsers.contains(user)) {
            removeUser(user);
            return bannedUsers.add(user);
        }

        return false;
    }

    public boolean unbanUser(User user) {
        if (user != null) {
            return bannedUsers.remove(user);
        }

        return false;
    }

    public void addChat(User user, String message) {
        if (user != null && !message.isEmpty() && message.length() <= 120) {
            chats.put(user, message);
        }
    }

    public boolean removeChat(User user, String message) {
        if (user != null && !message.isEmpty()) {
            return chats.remove(user, message);
        }

        return false;
    }

    public List<String> getChatMessages() {
        return new ArrayList<>(chats.values());
    }

    public void clearChat() {
        chats.clear();
    }

    public void clearTrackQueue() {
        trackQueue.clear();
        currentTrack = null;
        nextTrack = null;
        previousTrack = null;
    }

    public List<Poll> getPolls(User user) {
        // return all the polls created by the user
        return polls.values().stream().filter(poll -> poll.getCreator().equals(user)).toList();
    }

    public void addPoll(User user, Poll poll) {
        if (!user.isBanned() && !polls.containsValue(poll)) {
            polls.put(user, poll);
        }
    }

    public void removePoll(User user, Poll poll) {
        // remove the poll only if the user is not banned and the poll creator is the user
        if (!user.isBanned() && poll.getCreator().equals(user)) {
            polls.remove(poll.getCreator());
        }
    }

    public void closePoll(Poll poll) {
        poll.close();
    }

    public String getChatMessages(User user) {
        List<String> messages = new ArrayList<>();

        for (Map.Entry<User, String> entry : chats.entrySet()) {
            if (entry.getKey().equals(user)) {
                messages.add(entry.getValue());
            }
        }

        // return all the messages of the user
        return String.join("\n", messages);
    }

    public Track getMostPopularTrack() {
        // sort the tracks by likes and return the highest-liked track
        List<Track> tracks = new ArrayList<>(trackQueue.values());
        tracks.sort(Comparator.comparingLong(Track::getLikes));

        return tracks.getLast();
    }

    public Track getLeastPopularTrack() {
        // sort the tracks by dislikes and return the highest-disliked track
        List<Track> tracks = new ArrayList<>(trackQueue.values());
        tracks.sort(Comparator.comparingLong(Track::getDislikes));

        return tracks.getLast();
    }

    public void shuffleTrackQueue() {
        List<Track> shuffled = new ArrayList<>(trackQueue.values());
        Collections.shuffle(shuffled);

        trackQueue.clear();
        for (Track track : shuffled) {
            trackQueue.put(track.getTitle(), track);
        }
    }
}
