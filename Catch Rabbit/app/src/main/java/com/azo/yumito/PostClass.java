package com.azo.yumito;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> userName;
    private final ArrayList<String> userComment;
    private final ArrayList<Bitmap> userImages;
    private final Activity context;


    public PostClass(ArrayList<String> userName, ArrayList<String> userComment, ArrayList<Bitmap> userImages, Activity context) {
        super(context,R.layout.customview,userName);
        this.userName = userName;
        this.userComment = userComment;
        this.userImages = userImages;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //POst bulunduğu clas yazdık bunu da feed aktiviti ile bağlamamız lazım listview yani
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.customview,null,true);
        TextView userNameText = customView.findViewById(R.id.custom_view_username_text);
        TextView commentText = customView.findViewById(R.id.custom_view_comment_text);
        ImageView imageView = customView.findViewById(R.id.custom_view_image_view);

        userNameText.setText(userName.get(position));
        imageView.setImageBitmap(userImages.get(position));
        commentText.setText(userComment.get(position));
        return customView;
    }
}
