package com.example.shikhargoel.post;
import android.content.Intent;
import android.support.v4.app.Fragment;
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

public class delete extends AppCompatActivity implements View.OnClickListener {
TextView delete;
    Button del;
    String id,idd,username,surname,course;
    String url="http://192.168.43.74/delete.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        idd=intent.getStringExtra("idd");
        username=intent.getStringExtra("user");
        surname=intent.getStringExtra("surname");
        course=intent.getStringExtra("course");
        del=(Button)findViewById(R.id.del);
        del.setOnClickListener(this);
        delete=(TextView)findViewById(R.id.delete);
                                            }


    @Override
    public void onClick(View v) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray=new JSONArray(s);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    String success=jsonObject.getString("success");
                   if(success.equals("successfully deleted"))
                   {
                       Intent intent=new Intent(delete.this,Space.class);
                       intent.putExtra("idd",idd);
                       intent.putExtra("user",username);
                       intent.putExtra("surname",surname);
                       intent.putExtra("course",course);
                       startActivity(intent);

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
                params.put("id",id);
                return params;
            }
        };
        MySingleton.getmInstance(delete.this).addToRequestQueue(stringRequest);
    }
        public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
