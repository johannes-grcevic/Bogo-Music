package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    private String username;
    private String password;
    private String email;
    private String profilePicture;
    private String bio;

    @BeforeEach
    public void setUp() {
        username = "John-Doe";
        password = "Password123";
        email = "john.doe@gmail.com";
        profilePicture = "profile.jpg";
        bio = "I am a music lover";

        user = new User(username, password, email, profilePicture, bio);
    }

    @AfterEach
    public void tearDown() {
        user = null;
        username = null;
        password = null;
        email = null;
        profilePicture = null;
        bio = null;
    }

    @Test
    public void testConstructor() {
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
        assertEquals(profilePicture, user.getProfilePicture());
        assertEquals(bio, user.getBio());
        assertFalse(user.isBanned());
    }

    @Test
    public void testSetPassword() {
        String newPassword = "Password123!";
        user.setPassword(newPassword);

        assertEquals(newPassword, user.getPassword());

        // should not be the old password
        assertNotEquals(password, user.getPassword());
        assertNotEquals(newPassword, password);
    }

    @Test
    public void testSetUsername() {
        String newUsername = "john-doe123";
        user.setUsername(newUsername);

        assertEquals(newUsername, user.getUsername());

        // should not be the old username
        assertNotEquals(username, user.getUsername());
        assertNotEquals(newUsername, username);

        String longUsername = "john-doe_1234567890132626252590285029859028590829085092850928095";
        user.setUsername(longUsername);

        // should be truncated to 20 characters
        assertEquals(20, user.getUsername().length());
        assertEquals(longUsername.substring(0, 20), user.getUsername());
    }

    @Test
    public void testSetEmail() {
        String newEmail = "john-doe67@gmail.com";
        user.setEmail(newEmail);

        assertEquals(newEmail, user.getEmail());

        // should not be the old email
        assertNotEquals(email, user.getEmail());
        assertNotEquals(newEmail, email);

        // should contain @ symbol
        assertTrue(user.getEmail().contains("@"));
    }

    @Test
    public void testSetProfilePicture() {
        String newProfilePicture = "new-profile.jpg";
        user.setProfilePicture(newProfilePicture);

        assertEquals(newProfilePicture, user.getProfilePicture());

        // should not be the old profile picture
        assertNotEquals(profilePicture, user.getProfilePicture());
    }

    @Test
    public void testSetBio() {
        String newBio = "I am a music lover and a musician";
        user.setBio(newBio);

        assertEquals(newBio, user.getBio());

        // should not be the old bio
        assertNotEquals(bio, user.getBio());
    }

    @Test
    public void testBan() {
        assertFalse(user.isBanned());

        user.setBanned(true);

        assertTrue(user.isBanned());
    }
}
