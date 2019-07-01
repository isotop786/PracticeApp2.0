package com.example.practiceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPost, btnShow;
    private EditText editPost,shwoingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPost = findViewById(R.id.btnPost);
        editPost = findViewById(R.id.editPost);
        btnShow = findViewById(R.id.show);
        shwoingText=findViewById(R.id.showingText);

        btnPost.setOnClickListener(MainActivity.this);
        btnShow.setOnClickListener(MainActivity.this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        ParseObject post = new ParseObject("Posts");


        switch (v.getId()) {

            case R.id.btnPost:

            String postText = editPost.getText().toString();


            if (postText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please write something ", Toast.LENGTH_LONG).show();
            } else {




                post.put("post", postText);

                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "Successfully posted", Toast.LENGTH_SHORT).show();

                            editPost.setText("");

                        } else {
                            Toast.makeText(MainActivity.this, "Something worng :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            break;
            case R.id.showingText:

                shwoingText.setVisibility(View.VISIBLE);


                String postName = (String) post.get("post");

                shwoingText.setText(postName);
                break;
        }
    }
}