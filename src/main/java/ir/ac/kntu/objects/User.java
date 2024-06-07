package ir.ac.kntu.objects;

import ir.ac.kntu.types.AccessLevel;
import ir.ac.kntu.types.Level;

import java.util.ArrayList;

public class User {
    private AccessLevel accessLevel=AccessLevel.user;

    private int id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String Phone;

    private int age;

    private String password;

    private int wallet;

    private ArrayList<String> friends=new ArrayList<>();

    private ArrayList<String> games=new ArrayList<>();

    private Level level;

    private long timeSpend;

    private ArrayList<String> equipment=new ArrayList<>();

    private int point;

    private ArrayList<String> inbox=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void addFriends(String line){
        friends.add(line);
    }

    public ArrayList<String> getGames() {
        return games;
    }

    public void setGames(ArrayList<String> games) {
        this.games = games;
    }

    public Level getLevel() {
        return level;
    }

    //todo set level by time
    public void setLevel() {
        setPoint();
        if (point<20){
            level=Level.LEVEL_1;
        }else if (point<50){
            level=Level.LEVEL_2;
        }else if (point<100){
            level=Level.LEVEL_3;
        }else {
            level=Level.LEVEL_4;
        }
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public long getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(long timeSpend) {
        this.timeSpend = timeSpend;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint() {
        int time=0;
        point=-1;
        while (time<timeSpend){
            point++;
            time+=60;
        }
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<String> equipment) {
        this.equipment = equipment;
    }

    public ArrayList<String> getInbox() {
        return inbox;
    }

    public void setInbox(ArrayList<String> inbox) {
        this.inbox = inbox;
    }

    public void addInbox(String line){
        inbox.add(line);
    }
}
