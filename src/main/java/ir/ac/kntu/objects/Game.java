package ir.ac.kntu.objects;

import ir.ac.kntu.types.Beta;
import ir.ac.kntu.types.Failure;
import ir.ac.kntu.types.GameGenre;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Game {
    private int id;

    private String name;

    private String information;

    private int price=0;

    private double rate;

    private HashMap<String,Double> whoRated=new HashMap<>();

    private ArrayList<String > community=new ArrayList<>();

    private ArrayList<String> feedback=new ArrayList<>();

    private Beta isBeta;

    private Failure failure;

    private String image;

    private GameGenre gameGenre;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getRate() {
        setRate();
        DecimalFormat decimalFormat=new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format(rate));
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setRate() {
        double sum=0;
        double num=0;
        Collection<Double> values = whoRated.values();
        for (Double value : values) {
            sum+=value;
            num++;
        }
        if (sum==0){
            rate=0;
        }else {
            rate = sum / num;
        }
    }

    public HashMap<String, Double> getWhoRated() {
        return whoRated;
    }

    public void setWhoRated(HashMap<String, Double> whoRated) {
        this.whoRated = whoRated;
    }

    public void deleteWhoRate(String name){
        whoRated.remove(name);
    }

    public void addRate(String name, double rate) {
        whoRated.put(name,rate);
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

    public Beta getBeta() {
        return isBeta;
    }

    public void setBeta(Beta beta) {
        isBeta = beta;
    }

    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public GameGenre getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(GameGenre gameGenre) {
        this.gameGenre = gameGenre;
    }
}
