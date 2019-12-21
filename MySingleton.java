package com.example.shikhargoel.post;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Shikhar Goel on 29-Mar-17.
 */
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public MySingleton(Context context)
    {
        mCtx=context;
        requestQueue=getRequestQueue();
    }
    public static synchronized MySingleton getmInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new MySingleton(context);
        }
        return mInstance;

    }

   public<T> void addToRequestQueue(Request<T> request){
       requestQueue.add(request);
   }
}
