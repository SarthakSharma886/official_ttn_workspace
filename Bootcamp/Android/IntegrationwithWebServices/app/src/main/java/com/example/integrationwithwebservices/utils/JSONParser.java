package com.example.integrationwithwebservices.utils;

import com.example.integrationwithwebservices.POJO.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {

    private static final String USER_ARRAY = "posts";
    private static final String USER_NAME = "name";
    private static final String USER_MESSAGE = "message";
    private static final String USER_PROFILE = "profileImage";

    public static ArrayList<Post> apiDataParser(String result) {

        ArrayList<Post> resultArrayList = new ArrayList<>();


        try {
            JSONObject parentObject = new JSONObject(result);

            JSONArray postArray = parentObject.getJSONArray(USER_ARRAY);

            for (int i = 0; i < postArray.length(); i++) {
                JSONObject jsonObject = postArray.getJSONObject(i);
                Post post = new Post();
                post.setName(jsonObject.getString(USER_NAME));
                post.setMessage(jsonObject.getString(USER_MESSAGE));
                post.setProfileImage(jsonObject.getString(USER_PROFILE));
                resultArrayList.add(post);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultArrayList;
    }
}