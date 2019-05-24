package com.example.recyclerviewjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ExampleAdapter exampleAdapter;

    RequestQueue mRequestQueue;

    ArrayList<ExampleItems> mExampleItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRequestQueue = Volley.newRequestQueue(this);

        mExampleItems = new ArrayList<>();

        parseJSON();

    }

    public void parseJSON(){

        String URL = "https://pixabay.com/api/?key=12585160-2b097d5ea36c81fac3fdcf3f0&q=yellow+flowers&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for (int i = 0; i<jsonArray.length(); i++){

                        JSONObject hit = jsonArray.getJSONObject(i);

                        String creatorName = hit.getString("user");
                        String imageURL = hit.getString("webformatURL");
                        int likesCount = hit.getInt("likes");

                        mExampleItems.add(new ExampleItems(imageURL, creatorName, likesCount));

                    }

                    exampleAdapter = new ExampleAdapter(MainActivity.this, mExampleItems);
                    recyclerView.setAdapter(exampleAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();

            }
        });

        mRequestQueue.add(request);

    }

}
