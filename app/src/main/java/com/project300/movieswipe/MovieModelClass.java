package com.project300.movieswipe;

public class MovieModelClass {

    //String id;
    String name;
    String img;

    public MovieModelClass(String id, String name, String img) {
    //    this.id = id;
        this.name = name;
        this.img = img;
    }
    public MovieModelClass() {

    }

 //   public void setId(String id) {
     //   this.id = id;
 //   }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

  //  public String getId() {
   //     return id;
  //  }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }


}
