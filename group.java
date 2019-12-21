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

public class group extends AppCompatActivity implements View.OnClickListener {
    EditText group45;
    String  groupp;
    Button create;
    Button members;
    String id;
    String url = "http://192.168.43.74/groupid.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        group45 = (EditText) findViewById(R.id.group12);
        create = (Button) findViewById(R.id.create);
        create.setOnClickListener(this);
                                                        }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.create) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                  @Override
                public void onResponse(String s) {
                    try {
                        JSONArray jsonArray = new JSONArray(s);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String success = jsonObject.getString("success");
                        id = jsonObject.getString("id");
                        if(success.equals("Group is created"))
                        {
                            Intent intent=new Intent(group.this,AddMembers.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    ShowMessage("Error", "Something went wrong");
                    volleyError.printStackTrace();
                }

            }
            ) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    groupp = group45.getText().toString();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("groupss", groupp);
                    return params;
                }
            };
            MySingleton.getmInstance(group.this).addToRequestQueue(stringRequest);

        }

    }
    public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}