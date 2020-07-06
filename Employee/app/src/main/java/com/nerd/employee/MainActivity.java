package com.nerd.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nerd.employee.adapter.RecyclerViewAdapter;
import com.nerd.employee.model.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 네트워크 통신 라이브러리인, volley 라이브러리를 멤버변수로 선언
    RequestQueue requestQueue;
    public static final String URL = "http://dummy.restapiexample.com/api/v1/employees";    // 상수는 무조건 대문자.
    RecyclerView recyclerView;          // 메인 화면에 있는 리사이클러 뷰    (이거부터아래 세개는 세트임.)
    RecyclerViewAdapter recyclerViewAdapter;            // 우리가 만든, 하나의 셀을 연결시키는 어댑터
    ArrayList<Employee> employeeArrayList = new ArrayList<>();          // 데이터베이스에서 읽어온 주소록 정보를 저장할 리스트 (나중에 네트워크 연결해서 가져올거임)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리사이클러뷰 연결.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // 1. 발리의 리퀘스트큐 객체를 가져온다.
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        // 2. 제이슨리퀘스트 객체 생성 : 서버로부터 응답 받았을때 어떻게 처리할지를 코딩.
        JsonObjectRequest jsonObjectRequest =
                // http 프로토콜의 get 메소드설정,
                // 요청할 URL,
                // 요청할때 필요한 json,
                // 서버로부터 정상적 응답받았을시,
                // 서버로부터 응답 에러발생시
                new JsonObjectRequest(
                        Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {       // response 에 데이터가 담겨온당.
                                Log.i("AAA","wwwwwwwwwwww");
                                Log.i("AA", "result : "+response.toString());
                                // status 의 값은 뭐냐? status 에 무슨값이 담겨있는지 로그확인.
                                try {
                                    String value = response.getString("status");
                                    Log.i("AA","key status 의 값음 : "+value);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // jsonArray 값 가져오는거.
                                try {
                                    JSONArray dataArray = response.getJSONArray("data");
                                    Log.i("AA","key data 의 값은 : "+ dataArray.toString());

                                    // Data Parsing 데이터 파싱
                                    for (int i = 0; i < dataArray.length(); i++){
                                        JSONObject object = dataArray.getJSONObject(i);
                                        // Log.i("AA","루프 "+i+"번쨰 : "+object.toString());

                                        // 직원의 이름을 24명 다 로그에 찍어보자.
                                        String name = object.getString("employee_name");
                                        Log.i("AA", "루프 "+i +"번쟤 employee name : "+name);
                                        // id, 샐러리, 나이를 가져와서, 로그에 찍는다.
                                        int id = object.getInt("id");
                                        int age = object.getInt("employee_age");
                                        int salary = object.getInt("employee_salary");
                                        Log.i("AA", "루프 "+i +"번쟤 id : "+id+", 나이 : "+age+", salary : "+salary);

                                        // 파싱한 데이터를, 클래스의 객체로 저장.
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
