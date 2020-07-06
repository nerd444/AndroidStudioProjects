package com.nerd.parsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // https://jsonplaceholder.typicode.com/todos

    String url = "https://jsonplaceholder.typicode.com/todos/1";
    // volley 라이브러리를 이용해서, 위의 url 로 api 호출하여,
    // 결과인 json 을, 로그에 남깁니다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Parsing","onCreate 함수");

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Parsing",response.toString());
                try {
                    int userId = response.getInt("userId");
                    int id = response.getInt("id");
                    String title = response.getString("title");
                    boolean completed = response.getBoolean("completed");
                    Log.i("Parsing","UserID : " + userId);
                    Log.i("Parsing","id : " + id);
                    Log.i("Parsing","title : " + title);
                    Log.i("Parsing","completed : " + completed);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Parsing","error : " + error.toString());
            }
        });
    requestQueue.add(jsonObjectRequest);
    }
}
