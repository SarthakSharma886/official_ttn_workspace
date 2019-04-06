package com.example.integrationwithwebservices.Activities;

import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.integrationwithwebservices.Adapters.RetroAdapter;
import com.example.integrationwithwebservices.Async.Async;
import com.example.integrationwithwebservices.Handler.RetroHandler;
import com.example.integrationwithwebservices.Interfaces.FailureAPICallback;
import com.example.integrationwithwebservices.Interfaces.IRetroData;
import com.example.integrationwithwebservices.Interfaces.SuccessAPICallback;
import com.example.integrationwithwebservices.POJO.Post;
import com.example.integrationwithwebservices.POJO.RetroModel;
import com.example.integrationwithwebservices.R;
import com.example.integrationwithwebservices.Retrofit.RetroInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {


    final String BACKGROUND_URL = "https://i.stack.imgur.com/7vMmx.jpg1";
    final String RECYCLER_URL = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json";

    ArrayList<Post> mPostArrayList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RetroAdapter mRetroAdapter;
    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btHttp = findViewById(R.id.bt_http);
        Button btGlide = findViewById(R.id.bt_glide);
        Button btRetro = findViewById(R.id.bt_retro);

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRetroAdapter = new RetroAdapter();
        mConstraintLayout =  findViewById(R.id.constraint_bg);


        btHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncSet();
            }
        });


        btGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glideSet();
            }
        });


        btRetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retroSet();
            }
        });




    }


    public void glideSet(){

        if(haveNetworkConnection()) {

        Glide.with(this).load(BACKGROUND_URL).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mConstraintLayout.setBackground(resource);
                }
            }
        });
        }

        else {
            Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT).show();
        }

    }


    public void retroSet(){

        if(haveNetworkConnection()) {
            Toast.makeText(this, "Using Retrofit", Toast.LENGTH_SHORT).show();

            IRetroData iRetroData = RetroInstance.getInstance().create(IRetroData.class);
            Call<RetroModel> data = iRetroData.getUsers();
            data.enqueue(new RetroHandler<RetroModel>(this, new SuccessAPICallback<RetroModel>() {
                @Override
                public void onResponse(RetroModel retroModel) {

                    ArrayList<Post> retroModelPosts = retroModel.getPosts();
                    mRetroAdapter.setRetroAdapter(retroModelPosts);
                    mRecyclerView.setAdapter(mRetroAdapter);
                }
            }, new FailureAPICallback() {
                @Override
                public void onFaliure(Object errCode, Object errMsg) {

                }
            }));
        }

        else {
            Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT).show();
        }

    }


    public void asyncSet(){

        if(haveNetworkConnection()) {

            Toast.makeText(this, "Using Http", Toast.LENGTH_SHORT).show();

            Async async = new Async();
            String result = "";
            try {
                result = async.execute(RECYCLER_URL).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                JSONObject parentObject = new JSONObject(result);
                JSONArray postArray = parentObject.getJSONArray("posts");

                for (int i = 0; i < postArray.length(); i++) {
                    JSONObject jsonObject = postArray.getJSONObject(i);
                    Post post = new Post();
                    post.setName(jsonObject.getString("name"));
                    post.setMessage(jsonObject.getString("message"));
                    post.setProfileImage(jsonObject.getString("profileImage"));
                    mPostArrayList.add(post);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            mRetroAdapter.setRetroAdapter(mPostArrayList);
            mRecyclerView.setAdapter(mRetroAdapter);
        }

        else {
            Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT).show();
        }

    }


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}
