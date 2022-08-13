package com.wandika.outofthefridge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
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

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;


public class ApiRequest extends AppCompatActivity {

    TextView test, txt_label, txt_calories, txt_time;
    RecyclerView recyclerView;
    URL r;
    String label, calories, time, id, url;



    public static String encode(String url)
    {
        try {
            String encodeURL= URLEncoder.encode( url, "UTF-8" );
            return encodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" +e.getMessage();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_request);

        //test = (TextView)findViewById(R.id.test);
        txt_label = (TextView)findViewById(R.id.label_recipe);
        txt_calories = (TextView)findViewById(R.id.calories_recipe);
        txt_time = (TextView)findViewById(R.id.time_recipe);

        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first
        // activity
        String s = intent.getStringExtra("message_key");

        // display the string into textView
        //test.setText(s);
        String r = encode(s);
        //test.setText(r);

        RequestQueue queue = Volley.newRequestQueue(ApiRequest.this);
        String url = "https://api.edamam.com/api/recipes/v2?type=public&q="+r+"&app_id=90b80eb0&app_key=91a5ff9be23f2cab35ad8c1aa4de2e6c";
        //test.setText(url);

        //Request Ke API
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String label = "", calories = "", time="";

                        JSONObject data;

                        try {
                            JSONArray hits = response.getJSONArray("hits");
                            //test.setText("Response: " +hits.toString());
                            JSONObject obj = hits.getJSONObject(0);
                            //test.setText("Response: " +obj.toString());
                            JSONObject recipe = obj.getJSONObject("recipe");
                            //test.setText("Response: " +recipe.toString());

                            label = recipe.getString("label");
                            calories = recipe.getString("calories");
                            time = recipe.getString("totalTime");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        txt_label.setText(label.toString());
                        txt_calories.setText("Calories: " + calories.toString());
                        txt_time.setText("Time To Cook: " + time.toString() + " Minutes");
//                        txt_viewCount.setText("View Count: "+ viewCount.toString());
//                        txt_desc.setText("Channel Description: " + desc.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ApiRequest.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);

                //Set Layout Manager
                //recyclerView.setLayoutManager(new LinearLayoutManager(this));

                //Set Adapter
                //recyclerView.setAdapter(new Adapter(mContext, mData));
    }





//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.api_request);
//
//        test = (TextView)findViewById(
//                R.id.test);
//
//        Intent intent = getIntent();
//
//        // receive the value by getStringExtra() method
//        // and key must be same which is send by first
//        // activity
//        String s = intent.getStringExtra("message_key");
//
//        // display the string into textView
//        test.setText(s);
//    }
}
