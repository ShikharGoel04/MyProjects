package com.example.shikhargoel.post;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText name_field;
    EditText surname_field;
    EditText course_field;
    EditText pass;
    Button add;
    String name;
    String course;
    String password;
    String surname;
    EditText imgname;
    Button choose,upload;
    TextView tv1;
    ImageView img;
    private final int Img_Request=1;
    private Bitmap bitmap;
    String url = "http://192.168.43.74/register.php";
    String url1="http://192.168.43.74/ImageUploadApp/updateinfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name_field = (EditText) findViewById(R.id.uname);
        surname_field = (EditText) findViewById(R.id.surnamev);
        course_field = (EditText) findViewById(R.id.course);
        pass = (EditText) findViewById(R.id.pass);
        choose=(Button)findViewById(R.id.choose);
        upload=(Button)findViewById(R.id.upload);
        imgname=(EditText)findViewById(R.id.imgname);
        img=(ImageView)findViewById(R.id.img);
        choose.setOnClickListener(this);
        upload.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add) {
             name = name_field.getText().toString().trim();
            surname = surname_field.getText().toString().trim();
            course = course_field.getText().toString().trim();
            password = pass.getText().toString().trim();


            StringRequest stringRequest = new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                        String success=jsonObject.getString("success");
                        String message=jsonObject.getString("message");
                        if(success.equals("Successfully inserted"))
                        {
                            Intent intent=new Intent(Register.this,MainActivity.class);
                            startActivity(intent);


                        }
                        if(message.equals("user already exist"))
                        {
                            ShowMessage("Error","Username Already Exist");
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }

            }, new Response.ErrorListener() {


                @Override
                public void onErrorResponse(VolleyError volleyError) {
                   volleyError.printStackTrace();
            }}){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();
                    params.put("name",name);
                    params.put("surname",surname);
                    params.put("course",course);
                    params.put("password",password);
                    params.put("group","No");
                    return params;
                }
            };

            MySingleton.getmInstance(Register.this).addToRequestQueue(stringRequest);


        }
        else if(v.getId()==R.id.choose)
        {
            selectImage();

        }
        else if(v.getId()==R.id.upload)
        {

        UploadImage();
        }


    }
    private void selectImage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,Img_Request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Img_Request && resultCode==RESULT_OK && data!=null)
        {
            Uri path=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);
                imgname.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    private void UploadImage()
    {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    String response=jsonObject.getString("response");
                    ShowMessage("Success",response);
                    img.setImageResource(0);
                    img.setVisibility(View.GONE);
                    imgname.setText("");
                    imgname.setVisibility(View.GONE);
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
                params.put("name",imgname.getText().toString().trim());
                params.put("image",imageToString(bitmap));
                return params;
            }
        };
        MySingleton.getmInstance(Register.this).addToRequestQueue(stringRequest);
    }
    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgbytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgbytes,Base64.DEFAULT);
    }

    public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}