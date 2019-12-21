package com.example.shikhargoel.post;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
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
import android.os.Handler;
public class FragmentFive extends Fragment {
    String url="http://192.168.43.74/quotes.php";
    String url1="http://192.168.43.74/post.php";
    TableLayout tableLayout,tableLayout1;
    String id;
    String username,surname,course;
    MySingleton queue;
    String quotes;
    ScrollView scroll;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.table, null);
        final Handler handler=new Handler();

        Space activity=(Space)getActivity();
        id=activity.getmydata();
        username=activity.getall();
        surname=activity.getSurname();
        course=activity.getcourse();
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
                        String name=jsonObject.getString("name");
                        String post=jsonObject.getString("post");
                        final String idd1=jsonObject.getString("idd1");
                        TableRow tableRow = new TableRow(getActivity());

                        if(name.equals(username)) {
                            TextView textView = new TextView(getActivity());
                            textView.setText(name);
                            textView.setTextColor(Color.BLACK);
                            textView.setBackgroundResource(R.drawable.cell_shape);
                            textView.setPadding(2, 3, 4, 5);
                            tableRow.addView(textView);
                        }
                        else
                        {
                          TextView textView5=new TextView(getActivity());
                            textView5.setText(name);
                            textView5.setTextColor(Color.BLACK);
                            textView5.setBackgroundResource(R.drawable.user);
                            textView5.setPadding(2,3,4,5);
                            tableRow.addView(textView5);
                        }
                        if(name.equals(username)) {
                            TextView textView7 = new TextView(getActivity());
                            textView7.setText(post);
                            textView7.setTextColor(Color.BLACK);
                            textView7.setBackgroundResource(R.drawable.cell_shape);
                            textView7.setPadding(2, 3, 4, 5);
                            tableRow.addView(textView7);
                        }
                        else
                        {
                            TextView textView9=new TextView(getActivity());
                            textView9.setText(post);
                            textView9.setTextColor(Color.BLACK);
                            textView9.setBackgroundResource(R.drawable.user);
                            textView9.setPadding(2,3,4,5);
                            tableRow.addView(textView9);
                        }
                        if(name.equals(username))
                        {
                            TextView textView2=new TextView(getActivity());
                            textView2.setText("Delete");
                            textView2.setTextColor(Color.WHITE);
                            textView2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(getActivity(),delete.class);
                                    intent.putExtra("idd",id);
                                    intent.putExtra("user",username);
                                    intent.putExtra("id",idd1);
                                    intent.putExtra("surname",surname);
                                    intent.putExtra("course",course);

                                    startActivity(intent);
                                }
                            });
                        tableRow.addView(textView2);
                        }
                        tableLayout.addView(tableRow);
                        ((ScrollView)rootView.findViewById(R.id.scroll)).fullScroll(View.FOCUS_DOWN);


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
                Map<String,String> params=new HashMap<String,String>();
                params.put("id",id);
                return params;
            }
        };
        mySingleton.addToRequestQueue(stringRequest);
        mySingleton.getRequestQueue().start();
        scroll = (ScrollView) rootView.findViewById(R.id.scroll);
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.scrollTo(0,scroll.getBottom());
            }
        });
        tableLayout1=(TableLayout)rootView.findViewById(R.id.table_main);
        TableRow  tr1=new TableRow(getActivity());
        Button log=new Button(getActivity());
        log.setText("Logout");
        log.setTextSize(20);
        log.setWidth(20);
        log.setTypeface(null, Typeface.BOLD);
        log.setTextColor(Color.WHITE);
        tr1.addView(log);
        tableLayout1.addView(tr1);
        TableRow tr=new TableRow(getActivity());
        final EditText quote=new EditText(getActivity());
        quote.setHint("What's On Your Mind");
        tr.addView(quote);
        Button post=new Button(getActivity());
        post.setText("POST");
        post.setTextColor(Color.WHITE);
        post.setTextSize(20);
        post.setWidth(20);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                quotes=quote.getText().toString();
                queue = MySingleton.getmInstance(getActivity().getApplicationContext());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONArray jsonArray=new JSONArray(s);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String message=jsonObject.getString("message");
                            if(message.equals("Successfully posted"))
                            {
                                quote.getText().clear();
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
                        params.put("name",username);
                        params.put("post",quotes);
                        params.put("id",id);
                        return params;
                    }
                };
                queue.addToRequestQueue(stringRequest);
                queue.getRequestQueue().start();

            }
        });
        tr.addView(post);



        tableLayout1.addView(tr);


        return rootView;

    }

    public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
