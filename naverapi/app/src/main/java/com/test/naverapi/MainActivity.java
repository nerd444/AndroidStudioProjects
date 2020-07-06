package com.test.naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    String baseUrl = "https://openapi.naver.com/v1/papago/n2mt";

    RadioGroup radioGroup;
    RadioButton eng;
    RadioButton cn;     // 중국어 간체
    RadioButton tw;     // 중국어 번체
    RadioButton thai;
    EditText edittxt;
    TextView txtGet;
    ImageButton btnSet;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        eng = findViewById(R.id.eng);
        cn = findViewById(R.id.cn);
        tw = findViewById(R.id.tw);
        thai = findViewById(R.id.thai);
        edittxt = findViewById(R.id.edittxt);
        txtGet = findViewById(R.id.txtGet);
        btnSet = findViewById(R.id.btnSet);
        img = findViewById(R.id.img);

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        // JSONObjectRequest, JSONArrayRequest 이것은 전에 사용해봤다.

        // 문자열로 JSON 을 받아오는 클래스 : StringRequest
        // 헤더에 데이터를 넣어서 요청하는 방법

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt = edittxt.getText().toString().trim();

                if (txt.isEmpty()){
                    Toast.makeText(MainActivity.this, "번역할 언어를 입력하세요.", Toast.LENGTH_SHORT).show();
                }

                if (eng.isChecked()){
                    img.setImageResource(R.drawable.eng);
                    Toast.makeText(MainActivity.this, "영어로 번역되었습니다.", Toast.LENGTH_SHORT).show();
                }else if (cn.isChecked()){
                    img.setImageResource(R.drawable.chi);
                    Toast.makeText(MainActivity.this, "중국어 간체로 번역되었습니다.", Toast.LENGTH_SHORT).show();
                }else if (tw.isChecked()){
                    img.setImageResource(R.drawable.chi);
                    Toast.makeText(MainActivity.this, "중국어 번체로 번역되었습니다.", Toast.LENGTH_SHORT).show();
                }else if (thai.isChecked()){
                    img.setImageResource(R.drawable.thai);
                    Toast.makeText(MainActivity.this, "태국어로 번역되었습니다.", Toast.LENGTH_SHORT).show();
                }

                StringRequest request = new StringRequest(
                        Request.Method.POST, baseUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("AAA", response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    // translatedText 항목을 뽑아 올 수 있습니다.
                                    JSONObject message = jsonObject.getJSONObject("message");
                                    JSONObject result = message.getJSONObject("result");
                                    final String translatedText = result.getString("translatedText");

                                    txtGet.setText(translatedText);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("AAA", error.toString());
                            }
                        }
                ){
                    // 네이버 API 헤더 셋팅 부분을 여기에 작성한다.
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                        params.put("X-Naver-Client-Id", "rSaq2W_AsP_OmEo_gJLz");
                        params.put("X-Naver-Client-Secret", "H7GV7fY4nr");
                        return params;
                    }
                    // 네이버에 요청할 파라미터를 셋팅한다.
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        final Map<String, String> params = new HashMap<String, String>();

                        String txt = edittxt.getText().toString().trim();
                        params.put("source","ko");

                        if(eng.isChecked()){
                            params.put("target","en");
                        }else if(cn.isChecked()){
                            params.put("target","zh-CN");
                        }else if(tw.isChecked()) {
                            params.put("target","zh-TW");
                        }else if(thai.isChecked()){
                            params.put("target","th");
                        }

                        params.put("text",txt);
                        return params;
                    }

                };
                // 실제로 네트워크로 API 호출 ( 요청 )
                requestQueue.add(request);
            }
        });


    }
}
