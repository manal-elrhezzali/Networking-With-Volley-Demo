package me.elrhezzalimanal.networkingwithvolleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();//receive a Json file from the website JsonPlaceHolder
            }
        });

    }

    private void getData() {

        Gson gson = new Gson();

//        String url = "https://jsonplaceholder.typicode.com/posts/1";

        String url = "https://jsonplaceholder.typicode.com/posts";

//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                textView.setText(response);
//                Post post = gson.fromJson(response, Post.class);
//                Post[] posts = gson.fromJson(response, Post[].class);

//                textView.setText(post.getTitle());

//                for (Post p : posts) {
//                    textView.setText(textView.getText() + "\n" + p.getTitle() + "\n\n" );
//                }
                Post post = gson.fromJson(response, Post.class);
                textView.setText(post.getTitle());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> post = new HashMap<>();
                post.put("userId", "102");
                post.put("title", "A new Post");
                post.put("body", "manal manal manal chab3a 3ya9a tamalha tal9aha 3lina  shab rassha diva 3mri ma hmltha ma9bltha madayzach liyaaaaaaw");
                return post;
                //the response of the post request is the post that you sent to the server
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        queue.start();//runs on a worker thread

    }
}