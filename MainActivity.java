package com.example.shikhargoel.post;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText password;
    Button group1;
    Button login;
    private TextView reg;
    String name1,surname1,password1,course1;
    String code;
    String url="http://192.168.43.74/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.course);
        group1=(Button)findViewById(R.id.group1);
        group1.setOnClickListener(this);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
        userRegister();

    }
    public void userRegister() {
        Toast.makeText(this, "register", Toast.LENGTH_SHORT).show();
        reg = (TextView) findViewById(R.id.regis);
        reg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Register.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.login)

        {
            name1 = name.getText().toString();
            password1 = password.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONArray jsonArray = new JSONArray(s);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        surname1 = jsonObject.getString("surname");
                        course1 = jsonObject.getString("course");
                        code = jsonObject.getString("code");
                        Intent intent = new Intent(MainActivity.this,Display.class);
                        intent.putExtra("user", name1);
                        intent.putExtra("pass", password1);
                        intent.putExtra("surname", surname1);
                        intent.putExtra("course", course1);
                        if (code.equals("login success")) {
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
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name1);
                    params.put("password", password1);
                    return params;
                }
            };
            MySingleton.getmInstance(
                    MainActivity.this).addToRequestQueue(stringRequest);
        }
        else if(v.getId()==R.id.group1)
        {

            Intent intent=new Intent(MainActivity.this,group.class);
            startActivity(intent);
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
