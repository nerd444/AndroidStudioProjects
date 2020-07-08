package com.nerd.employee2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nerd.employee2.adapter.RecyclerViewAdapter;
import com.nerd.employee2.model.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    public static final String URL = "http://dummy.restapiexample.com/api/v1/employees";
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Employee> employeeArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리사이클러뷰 연결.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("AA", "result : "+response.toString());
                                try {
                                    JSONArray dataArray = response.getJSONArray("data");
                                    Log.i("AA","key data 의 값은 : "+ dataArray.toString());

                                    for (int i = 0; i < dataArray.length(); i++){
                                        JSONObject object = dataArray.getJSONObject(i);
                                        int id = object.getInt("id");
                                        String name = object.getString("employee_name");
                                        int age = object.getInt("employee_age");
                                        int salary = object.getInt("employee_salary");
                                        Log.i("AA", "루프 "+i +"번쟤 id : "+id+", 나이 : "+age+", salary : "+salary);

                                        Employee employee = new Employee(id, name, age, salary);
                                        employeeArrayList.add(employee);

                                    }
                                    // for 루프 끝나야, 모든 데이터가 다 어레이리스트에 들어있다.
                                    // 이렇게 어레이 리스트에 데이터가 다 들어있는 후에, 리사이클러뷰를 표시.
                                    // 우리가 만든 하나의 셀 표시하는 어댑터를 생성해서, 리사이클러뷰에 연결
                                    recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, employeeArrayList);
                                    recyclerView.setAdapter(recyclerViewAdapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

        requestQueue.add(jsonObjectRequest);
        // 네트워크 갔다와서, 어레이리스트에 데이터가 쌓여있는 상태 X => 주의
        // 요 아래에다가 어댑터 연결하는 코드 넣으면 절대 안됩니다. (제일 좋은방법은 log 찍기.)
        Log.i("AAA","qqqqqqqqqqq");

    }
}
