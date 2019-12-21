package com.example.shikhargoel.post;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Display extends AppCompatActivity {
String url="http://192.168.43.74/display.php";

    TableLayout tableLayout;
    String username,course,surname,group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        Intent intent=getIntent();
        username = intent.getStringExtra("user");
         surname=intent.getStringExtra("surname");
         course=intent.getStringExtra("course");

        tableLayout=(TableLayout)findViewById(R.id.table_main);
        tableLayout.setBackgroundResource(R.drawable.chat);
        Button scr=new Button(Display.this);
        scr.setText("Go to login screen");
        scr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Display.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        tableLayout.addView(scr);
        TableRow tr=new TableRow(this);
        TextView textView4=new TextView(this);
        textView4.setText("Your Groups");
        textView4.setTextColor(Color.BLACK);
        textView4.setTextSize(40);
        tr.addView(textView4);
        tableLayout.addView(tr);


        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        final String groupname = jsonObject.getString("groupnames");
                        final String idd = jsonObject.getString("ids");

                            TableRow tbrow = new TableRow(Display.this);
                            final TextView textView1 = new TextView(Display.this);
                            textView1.setText(groupname);
                            textView1.setPadding(11, 12, 13, 14);
                            textView1.setBackgroundResource(R.drawable.cell_shape);
                            textView1.setTextColor(Color.BLACK);
                            textView1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(Display.this, Space.class);
                                    intent.putExtra("idd", idd);
                                    intent.putExtra("user", username);
                                    intent.putExtra("surname", surname);
                                    intent.putExtra("course", course);
                                    startActivity(intent);
                                }
                            });

                            tbrow.addView(textView1);

                            final TextView textView2 = new TextView(Display.this);
                            textView2.setText("Delete Group");
                            textView2.setPadding(12, 13, 14, 15);
                            textView2.setTextColor(Color.BLACK);
                            textView2.setBackgroundResource(R.drawable.cell_shape);
                            textView2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(Display.this, Delgroup.class);
                                    intent.putExtra("idd", idd);
                                    intent.putExtra("user", username);
                                    intent.putExtra("surname", surname);
                                    intent.putExtra("course", course);
                                    intent.putExtra("group", groupname);
                                    startActivity(intent);
                                }
                            });
                            tbrow.addView(textView2);
                            tableLayout.addView(tbrow);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("name",username);
                return params;
            }
        };
        MySingleton.getmInstance(Display.this).addToRequestQueue(stringRequest);
    }


}
