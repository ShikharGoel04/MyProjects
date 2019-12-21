package com.example.shikhargoel.post;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import static android.view.View.*;

public class Fragmentview1 extends Fragment implements OnClickListener   {
    String url="http://192.168.43.74/showmem.php";
    String id, name,post;
    TableLayout tableLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.table, null);
        final String idd = getArguments().getString("idd2");
        tableLayout = (TableLayout) rootView.findViewById(R.id.table_main);
        MySingleton mySingleton=MySingleton.getmInstance(getActivity().getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray=new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        name=jsonObject.getString("GroupMembers");
                        TableRow tr=new TableRow(getActivity());
                        TextView tv1=new TextView(getActivity());
                        tv1.setText(name);
                        tv1.setTextColor(Color.GREEN);
                        tv1.setTextSize(40);
                        tr.addView(tv1);
                        tableLayout.addView(tr);

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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("id",idd);
                return params;
            }
        };
        mySingleton.addToRequestQueue(stringRequest);
        mySingleton.getRequestQueue().start();
        return rootView;


    }
    public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    public void onClick(View v) {

    }
}
