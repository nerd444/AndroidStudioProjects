package com.nerd.customalert;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nerd.customalert.adapter.RecyclerViewAdapter;
import com.nerd.customalert.model.CustomAlert;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    public static final String URL = "https://jsonplaceholder.typicode.com/posts ";
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<CustomAlert> customAlertArrayList = new ArrayList<>();

    EditText editTitle;
    EditText editBody;
    TextView txtNo;
    TextView txtYes;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("AAA", "array 의 값은 : "+response.toString());

                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                int userId = jsonObject.getInt("userId");
                                int id = jsonObject.getInt("id");
                                String title = jsonObject.getString("title");
                                String body = jsonObject.getString("body");
                                Log.i("AAA", "루프 "+i+"번째 userId : "+userId+" id : "+id+" title : "+title+" body : "+body);

                                CustomAlert customAlert = new CustomAlert(userId, id, title, body);
                                customAlertArrayList.add(customAlert);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, customAlertArrayList);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
            }
        });
    }

    public void createPopupDialog(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        final View alertView = getLayoutInflater().inflate(R.layout.alert,null);

        editTitle = alertView.findViewById(R.id.editTitle);
        editBody = alertView.findViewById(R.id.editBody);
        txtNo = alertView.findViewById(R.id.txtNo);
        txtYes = alertView.findViewById(R.id.txtYes);

        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String body = editBody.getText().toString().trim();

                if (title.isEmpty() || body.isEmpty()){
                    Toast.makeText(MainActivity.this, "글자 입력 필수", Toast.LENGTH_SHORT).show();
                    return;
                }

                CustomAlert customAlert = new CustomAlert();
                customAlert.setTitle(title);
                customAlert.setBody(body);

                customAlertArrayList.add(customAlert);
                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, customAlertArrayList);
                recyclerView.setAdapter(recyclerViewAdapter);

                dialog.cancel();
            }
        });

        txtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        alert.setView(alertView);
        alert.setCancelable(false);

        dialog = alert.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_add) {
            Intent i = new Intent(MainActivity.this, AddPosting.class);
            // startActivity(i);
            startActivityForResult(i,0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK){

            CustomAlert customAlert = (CustomAlert) data.getSerializableExtra("data");
            customAlertArrayList.add(customAlert);
            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, customAlertArrayList);
            recyclerView.setAdapter(recyclerViewAdapter);

        }
    }


}
