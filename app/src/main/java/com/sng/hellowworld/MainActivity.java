package com.sng.hellowworld;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
    private TextView textViewPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPost = findViewById(R.id.textview_post);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceholderApi placeholderApi = retrofit.create(PlaceholderApi.class);

        Call<List<Post>> call = placeholderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewPost.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String postData = "";
                    postData += "ID: " + post.getId() + "\n";
                    postData += "User ID: " + post.getUserId() + "\n";
                    postData += "Title: " + post.getTitle() + "\n";
                    postData += "Description: " + post.getDescription() + "\n\n";

                    textViewPost.append(postData);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewPost.setText(t.getMessage());
            }
        });

    }

    public void showDate(View view) {
        TextView helloDate=(TextView)findViewById(R.id.hello_label);
        helloDate.setText(currentDate + System.getProperty("line.separator") + currentTime);
    }
}