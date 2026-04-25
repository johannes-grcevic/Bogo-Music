package ie.setu.bogomusic.model;

import java.util.HashMap;
import java.util.List;

public class Poll {
    private final String question;
    private final HashMap<String, Integer> options = new HashMap<>();
    private final float duration;
    private final User creator;
    private boolean closed = false;

    public Poll(String question, float duration, User creator) {
        this.question = question;
        this.duration = duration;
        this.creator = creator;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        // return a list of option values from the options HashMap
        return options.values().stream().map(String::valueOf).toList();
    }

    public int getVotes(String option) {
        return options.getOrDefault(option, 0);
    }

    public float getDuration() {
        return duration;
    }

    public User getCreator() {
        return creator;
    }

    public void addOption(String option) {
        if (!isClosed()) {
            options.put(option, options.getOrDefault(option, 0));
        }
    }

    public void vote(String option) {
        if (!isClosed()) {
            options.replace(option, options.get(option) + 1);
        }
    }

    public void close() {
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isVoted(String option) {
        return options.getOrDefault(option, 0) > 0;
    }

    public boolean isCreator(User user) {
        return creator.equals(user);
    }

    public boolean isCreator(String username) {
        return creator.getUsername().equals(username);
    }

    @Override
    public String toString() {
        return "Poll{" +
                "question='" + question + '\'' +
                ", options=" + options +
                ", duration=" + duration +
                ", creator=" + creator +
                ", closed=" + closed +
                '}';
    }
}
