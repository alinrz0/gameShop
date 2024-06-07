package ir.ac.kntu.objects;

import ir.ac.kntu.types.Connection0;
import ir.ac.kntu.types.Device;

import java.util.ArrayList;

public class Equipment {
    private int id;

    private String name;

    private String information;

    private Device device;

    private String compatible;

    private Connection0 connection;

    private String refreshRate;

    private double size;

    private double responseTime;

    private int price;

    private int number;

    private ArrayList<String > community=new ArrayList<>();

    private ArrayList<String> feedback=new ArrayList<>();

    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getCompatible() {
        return compatible;
    }

    public void setCompatible(String compatible) {
        this.compatible = compatible;
    }

    public Connection0 getConnection() {
        return connection;
    }

    public void setConnection(Connection0 connection) {
        this.connection = connection;
    }

    public String getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(String refreshRate) {
        this.refreshRate = refreshRate;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<String> getCommunity() {
        return community;
    }

    public void setCommunity(ArrayList<String> community) {
        this.community = community;
    }

    public void addCommunity(String line){
        community.add(line);
    }

    public ArrayList<String> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<String> feedback) {
        this.feedback = feedback;
    }

    public void addFeedback(String line){
        feedback.add(line);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
