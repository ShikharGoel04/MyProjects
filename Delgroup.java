package com.example.shikhargoel.post;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Delgroup extends AppCompatActivity {
    String username,surname,course, idd;
    Button del1;
    TextView groupdel;
    String group;
    String url = "http://192.168.43.74/delgroup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delgroup);
        Intent intent = getIntent();
        surname=intent.getStringExtra("surname");
        course=intent.getStringExtra("course");
        username = intent.getStringExtra("user");
        group=intent.getStringExtra("group");
        groupdel=(TextView)findViewById(R.id.groupdel);
        groupdel.setText("Are you sure you want to exit from " +group);
        idd = intent.getStringExtra("idd");
        del1 = (Button) findViewById(R.id.del1);
        del1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String success = jsonObject.getString("success");
                            if(success.equals("successfully deleted group"))
                            {
                                Intent intent1=new Intent(Delgroup.this,Display.class);
                                intent1.putExtra("user",username);
                                intent1.putExtra("surname",surname);
                                intent1.putExtra("course",course);
                                startActivity(intent1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.printStackTrace();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", username);
                        params.put("id", idd);
                        return params;
                    }
                };
                MySingleton.getmInstance(Delgroup.this).addToRequestQueue(stringRequest);
            }

        });

    }

    public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}