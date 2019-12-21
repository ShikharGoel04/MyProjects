package com.example.shikhargoel.post;
import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shikhargoel.post.R;
import com.example.shikhargoel.post.Space;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Fragmentfour extends Fragment implements Space.DataFromActivityToFragment{
    TextView user;
    TextView surnamev;
    TextView coursev;
    TextView pi;
    TextView post1;
    String username;
    EditText quote4;
    TextView name;
    TextView surname1;
    TextView course1;

    String idd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_fragmentfour,null);
        user=(TextView)rootView.findViewById(R.id.user);
    surnamev=(TextView)rootView.findViewById(R.id.surnamev);
    coursev=(TextView)rootView.findViewById(R.id.coursev);
    pi=(TextView)rootView.findViewById(R.id.PI);
    name=(TextView)rootView.findViewById(R.id.name);
    surname1=(TextView)rootView.findViewById(R.id.surname);
    course1=(TextView)rootView.findViewById(R.id.course);
    return rootView;
}
    @Override
    public void sendData(String data, String surname, String course,String id) {
        name.setText("Name: "+data);
        name.setTypeface(null, Typeface.BOLD_ITALIC);
        name.setPadding(2,3,4,5);
        surname1.setText("surname: "+surname);
        surname1.setTypeface(null,Typeface.BOLD_ITALIC);
        surname1.setPadding(4,5,6,7);
        course1.setText("Course: "+course);
        course1.setPadding(9,10,11,12);
        course1.setTypeface(null,Typeface.BOLD_ITALIC);
        username=data;
        idd=id;
        pi.setText("Personal Information");
        pi.setTypeface(null,Typeface.BOLD);
        pi.setTextSize(30);
    }


    public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
