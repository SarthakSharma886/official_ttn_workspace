package com.example.integrationwithwebservices.POJO;

        import java.util.ArrayList;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class RetroModel  {

    @SerializedName("posts")
    @Expose
    private ArrayList<Post> posts = null;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

}