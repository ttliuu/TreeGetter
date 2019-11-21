package com.example.treegetter;

import java.util.ArrayList;

public class Tree {
    double latitude = 0.0;
    double longitude = 0.0;
    String type = "";
    String latin = "";
    String description = "";
    public static ArrayList<Tree> treeArray = new ArrayList<Tree>();
    public Tree(){};
    public Tree(double latitude, double longitude, String type, String latin, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.latin = latin;
        this.description = description;
    }
    public Tree(double latitude,double longitude)
    {
        this.latitude =latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", type='" + type + '\'' +
                ", latin='" + latin + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDistance(Tree a){
        double b = Math.sqrt(Math.pow(this.getLatitude()-a.getLatitude(),2) + Math.pow(this.getLongitude()-a.getLongitude(),2));
        return b;
    }

    public Tree getNearestTree(ArrayList<Tree> a){
        int j = 0;
        double sDist = this.getDistance(a.get(0));
        double dist;
        for(int i = 0; i < a.size(); i++){
            dist = this.getDistance(a.get(i));
            if(dist < sDist) {
                j = i;
                sDist = dist;
            }
        }
        return a.get(j);
    }

    public int getNearestTreeLoc(ArrayList<Tree> a){
        int j = 0;
        double sDist = this.getDistance(a.get(0));
        double dist;
        for(int i = 0; i < a.size(); i++){
            dist = this.getDistance(a.get(i));
            if(dist < sDist) {
                j = i;
                sDist = dist;
            }
        }
        return j;
    }


}
