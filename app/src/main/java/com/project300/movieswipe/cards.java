package com.project300.movieswipe;

public class cards {

    private String userId;
    private String name;
    public cards (String userId, String name){
        this.userId = userId;
        this.name = name;
    }


    public String  getUSerId(){
        return userId;
    }

    public void setUserId(String userID){
        this.userId = userId;

    }

    public String getName(){
       return name;

    }

    public void setNameId(String name){
        this.name = name;

    }


}
