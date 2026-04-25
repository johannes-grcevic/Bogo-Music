package model;

public class AIShuffle {

    private String playlistMood;
    private String userMood;
    private String lastRecommended;

    public AIShuffle(String playlistMood, String userMood, String lastRecommended) {
        this.playlistMood = playlistMood;
        this.userMood = userMood;
        this.lastRecommended = lastRecommended;
    }

    public String getPlaylistMood() {
        return playlistMood;
    }

    public void setPlaylistMood(String playlistMood) {
        this.playlistMood = playlistMood;
    }

    public String getUserMood() {
        return userMood;
    }

    public void setUserMood(String userMood) {
        this.userMood = userMood;
    }

    public String getLastRecommended() {
        return lastRecommended;
    }

    public void setLastRecommended(String lastRecommended) {
        this.lastRecommended = lastRecommended;
    }

    public int getRecommendationStrength() {
        if (playlistMood.equalsIgnoreCase(userMood)) {
            return 100;
        }

        if ((playlistMood.equalsIgnoreCase("Chill") && userMood.equalsIgnoreCase("Relaxed")) ||
                (playlistMood.equalsIgnoreCase("Energetic") && userMood.equalsIgnoreCase("Happy"))) {
            return 70;
        }
        return 30;
    }

    @Override
    public String toString() {
        return "Playlist Mood: " + playlistMood +
                " | User Mood: " + userMood +
                " | Last Recommended: " + lastRecommended;
    }
}
