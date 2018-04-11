package com.example.gsonstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.text_view);

        requestJson();//Volley请求Json
//        requestJsonArray();//Volley请求JsonArray
//        requestJsonFixed();//Volley请求Json套JsonArray
//        javaToJson();//将Java对象转换为Json
    }

    private void requestJson() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://wkxjc.github.io/test_json.json",
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonWithGson(response.toString());//Gson解析Json对象
                    }
                }, null);
                mQueue.add(jsonObjectRequest);
            }
        });
    }

    private void parseJsonWithGson(String jsonString) {
        Gson gson = new Gson();
        final Message message = gson.fromJson(jsonString,Message.class);
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(message.toString());
            }
        });
    }

    private void requestJsonArray() {
        textView.setText("");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://wkxjc.github.io/test_json_array.json",
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(final JSONArray response) {
                                parseJsonArrayWithGson(response.toString());//Gson解析JsonArray
                            }
                        },null);
                mQueue.add(jsonArrayRequest);
            }
        });
    }

    private void parseJsonArrayWithGson(String jsonArrayString) {
        Gson gson = new Gson();
        final List<Message> messageList = gson.fromJson(jsonArrayString, new TypeToken<List<Message>>(){}.getType());
        textView.post(new Runnable() {
            @Override
            public void run() {
                for(Message message:messageList){
                    textView.append(message.toString()+"\n");
                }
            }
        });
    }

    private void requestJsonFixed() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://wkxjc.github.io/test_json_fixed.json",
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonFixedWithGson(response.toString());//Gson解析Json对象
                    }
                }, null);
                mQueue.add(jsonObjectRequest);
            }
        });
    }

    private void parseJsonFixedWithGson(String jsonString) {
        Gson gson = new Gson();
        final Classroom classroom = gson.fromJson(jsonString,Classroom.class);
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(classroom.toString());
            }
        });
    }

    private void javaToJson() {
        Gson gson = new Gson();
        Message message = new Message();
        message.setId("1");
        message.setName("Name One");
        message.setAge("1 year");
        String jsonStringMessage = gson.toJson(message);
        textView.setText(jsonStringMessage);

//        Gson gson = new Gson();
//        Message message1 = new Message();
//        message1.setId("1");
//        message1.setName("Name One");
//        message1.setAge("1 year");
//        Message message2 = new Message();
//        message2.setId("2");
//        message2.setName("Name Two");
//        message2.setAge("2 years");
//        List<Message> messageList = new ArrayList<>();
//        messageList.add(message1);
//        messageList.add(message2);
//        String jsonArrayStringMessageList = gson.toJson(messageList);
//        textView.setText(jsonArrayStringMessageList);
//
//        Gson gson = new Gson();
//        Classroom classroom = new Classroom();
//        Message message = new Message();
//        message.setId("1");
//        message.setName("Name One");
//        message.setAge("1 year");
//        classroom.setNumber("13");
//        classroom.setHouse("402");
//        classroom.setMessage(message);
//        String jsonStringClassroom = gson.toJson(classroom);
//        textView.setText(jsonStringClassroom);
    }
}
