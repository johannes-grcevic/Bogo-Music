package model;

public class Track {

    private String title;
    private String artist;
    private int duration;
    private String audioQuality;
    private long likes;
    private long dislikes;

    public Track(String title, String artist, int duration, String audioQuality) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.audioQuality = audioQuality;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAudioQuality() {
        return audioQuality;
    }

    public void setAudioQuality(String audioQuality) {
        this.audioQuality = audioQuality;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public void addDislike() {
        if (dislikes < Long.MAX_VALUE) {
            dislikes++;
        }
    }

    public void addLike() {
        if (likes < Long.MAX_VALUE) {
            likes++;
        }
    }

    public void removeLike() {
        if (likes > 0) {
            likes--;
        }
    }

    public void removeDislike() {
        if (dislikes > 0) {
            dislikes--;
        }
    }

    public String getFormattedDuration() {
        int minutes = duration / 60;
        int seconds = duration % 60;

        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public String toString() {
        return "Track: " + title +
                " | Artist: " + artist +
                " | Duration: " + getFormattedDuration() +
                " | Quality: " + audioQuality +
                " | Likes: " + likes +
                " | Dislikes: " + dislikes;
    }
}
