package ir.ac.kntu.objects;

import ir.ac.kntu.types.AccessLevel;
import ir.ac.kntu.types.DeveloperLevel;

import java.util.ArrayList;

public class Developer {
    private AccessLevel accessLevel=AccessLevel.developer;

    private int id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String Phone;

    private int age;

    private String password;

    private ArrayList<String> games=new ArrayList<>();

    private ArrayList<String> inbox=new ArrayList<>();

    private ArrayList<String> scheduledEvents=new ArrayList<>();

    private DeveloperLevel developerLevel;

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

    public ArrayList<String> getGames() {
        return games;
    }

    public void setGames(ArrayList<String> games) {
        this.games = games;
    }

    public void addGame(String id){
        games.add(id);
    }

    public ArrayList<String> getInbox() {
        return inbox;
    }

    public void addInbox(String name){
        inbox.add(name);
    }

    public void deleteInbox(String name){
        inbox.remove(name);
    }

    public void setInbox(ArrayList<String> inbox) {
        this.inbox = inbox;
    }

    public ArrayList<String> getScheduledEvents() {
        return scheduledEvents;
    }

    public void setScheduledEvents(ArrayList<String> scheduledEvents) {
        this.scheduledEvents = scheduledEvents;
    }

    public void addScheduledEvents(String name){
        scheduledEvents.add(name);
    }

    public void deleteScheduledEvents(String name){
        scheduledEvents.remove(name);
    }

    public DeveloperLevel getDeveloperLevel() {
        return developerLevel;
    }

    public void setDeveloperLevel(DeveloperLevel developerLevel) {
        this.developerLevel = developerLevel;
    }
}
