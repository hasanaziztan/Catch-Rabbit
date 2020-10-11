package com.azo.yumito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> userNamesFromParse;
    ArrayList<String> userCommentFromParse;
    ArrayList<Bitmap> userImageFromParse;
    PostClass postClass;
    //hangi menu bağlayacağız
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    //tıklandığında ne olacak
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_post){
            Intent intent = new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.logout) {
            // Çıkış yapmak için
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e!=null){
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        listView = findViewById(R.id.listview);

        userNamesFromParse = new ArrayList<>();
        userCommentFromParse = new ArrayList<>();
        userImageFromParse = new ArrayList<>();


        postClass = new PostClass(userNamesFromParse,userCommentFromParse,userImageFromParse,this);

        listView.setAdapter(postClass);

        download();

    }

    public void download() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e != null) {

                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {

                    if (objects.size() > 0){
                        for (final ParseObject object : objects){

                                ParseFile parseFile = (ParseFile) object.get("image");

                                parseFile.getDataInBackground(new GetDataCallback() {
                                    @Override
                                    public void done(byte[] data, ParseException e) {

                                        if (e == null && data != null){
                                            Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);

                                            userImageFromParse.add(bitmap);
                                            userNamesFromParse.add(object.getString("username"));
                                            userCommentFromParse.add(object.getString("comment"));

                                            postClass.notifyDataSetChanged();


                                        }

                                    }
                                });

                        }
                    }

                }
            }
        });

    }


}
