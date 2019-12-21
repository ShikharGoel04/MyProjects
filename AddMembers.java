package com.example.shikhargoel.post;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import static com.android.volley.Response.*;
public class AddMembers extends AppCompatActivity implements View.OnClickListener {
EditText member1,member2,member3,member4;
    String member11,member22,member33,member44;
    String id;
    Button created;
    String url="http://192.168.43.74/addmem.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);
        member1=(EditText)findViewById(R.id.member1);
        member2=(EditText)findViewById(R.id.member2);
        member3=(EditText)findViewById(R.id.member3);
        member4=(EditText)findViewById(R.id.member4);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        created=(Button)findViewById(R.id.created);
        created.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray=new JSONArray(s);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    String success=jsonObject.getString("success");
                    if(success.equals("successfully added member"))
                    {
                        Intent intent=new Intent(AddMembers.this,MainActivity.class);
                        startActivity(intent);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ShowMessage("Error","Something went wrong");
                volleyError.printStackTrace();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                String member11=member1.getText().toString();
                String member22=member2.getText().toString();
                String member33=member3.getText().toString();
                String member44=member4.getText().toString();
                params.put("member1",member11);
                params.put("member2",member22);
                params.put("member3",member33);
                params.put("member4",member44);
                params.put("id",id);
                 return params;
            }
        };
        MySingleton.getmInstance(AddMembers.this).addToRequestQueue(stringRequest);

    }

    public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
