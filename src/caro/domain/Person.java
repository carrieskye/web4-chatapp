package caro.domain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private String userId;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private Role role;
    private ArrayList<String> friends;

    public Person(String userId, String password, String firstName, String lastName, String gender, Role role, ArrayList<String> friends) {
        setUserId(userId);
        setHashedPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setStatus("offline");
        setRole(role);
        setFriends(friends);
    }

    public Person(String userId, String password, String salt, String firstName, String lastName, String gender, Role role, ArrayList<String> friends) {
        setUserId(userId);
        setPassword(password);
        setSalt(salt);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setStatus("offline");
        setRole(role);
        setFriends(friends);
    }

    public Person() {
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public void setUserId(String userId) {
        if (userId.isEmpty()) {
            throw new IllegalArgumentException("No id given");
        }
        String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(userId);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        return getPassword().equals(hashPassword(password, getSalt()));
    }

    public void setPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.password = password;
    }

    public void setHashedPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] seed = random.generateSeed(20);

        String salt = new BigInteger(1, seed).toString(16);
        this.setSalt(salt);

        return hashPassword(password, salt);
    }

    private String hashPassword(String password, String seed) {
        String hashedPassword = null;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(salt.getBytes(StandardCharsets.UTF_8));
            crypt.update(password.getBytes(StandardCharsets.UTF_8));
            hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new DomainException(e.getMessage(), e);
        }
        return hashedPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;// firstName;

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("No status given");
        }
        this.status = status;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void addFriend(String friendId) throws IllegalArgumentException {
        if (friends.contains(friendId)) {
            throw new IllegalArgumentException("Friend already added");
        }
        if (friendId.equals(getUserId())) {
            throw new IllegalArgumentException("You cannot add yourself");
        }
        friends.add(friendId);
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

}
