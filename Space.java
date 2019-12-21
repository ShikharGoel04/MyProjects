package com.example.shikhargoel.post;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Space extends AppCompatActivity {
    DataFromActivityToFragment dataFromActivityToFragment;
    String username;
    String surname;
    String course;
    TextView name;
    TextView user;
    TextView pi;
    EditText quote1;
    TextView post;
    TextView surnamev;
    TextView coursev;
    TextView surname1;
    TextView course1;
    String idd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);
        surnamev=(TextView)findViewById(R.id.surnamev);
        coursev=(TextView)findViewById(R.id.coursev);
        surname1=(TextView)findViewById(R.id.surname);
        course1=(TextView)findViewById(R.id.course);
        Intent intent=getIntent();
        username = intent.getStringExtra("user");
        idd=intent.getStringExtra("idd");
        surname=intent.getStringExtra("surname");
        course=intent.getStringExtra("course");
        name=(TextView)findViewById(R.id.name);
        user=(TextView)findViewById(R.id.user);
        pi=(TextView)findViewById(R.id.PI);
        pi.setText("Hi "+username);
        pi.setTextSize(30);
        pi.setTypeface(null, Typeface.BOLD_ITALIC);
        name.setText("Name: "+username);
        name.setTypeface(null,Typeface.BOLD_ITALIC);
        surname1.setText("surname: "+surname);
        surname1.setTypeface(null,Typeface.BOLD_ITALIC);
        course1.setText("Course: "+course);
        course1.setTypeface(null,Typeface.BOLD_ITALIC);




    }

    public void ChangeFragment(View view) {

        if (view == findViewById(R.id.space)) {

            Fragmentfour frag = new Fragmentfour();
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            dataFromActivityToFragment=(DataFromActivityToFragment)frag;
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, frag);
            ft.commit();
            Handler handler=new Handler();
            final Runnable r=new Runnable() {
                @Override
                public void run() {
                    dataFromActivityToFragment.sendData(username,surname,course,idd);
                }
            };
            handler.postDelayed(r,1);

        }
        if (view == findViewById(R.id.others)) {

            name.setVisibility(View.INVISIBLE);
            user.setVisibility(View.INVISIBLE);
            course1.setVisibility(View.INVISIBLE);
            coursev.setVisibility(View.INVISIBLE);
            surname1.setVisibility(View.INVISIBLE);
            surnamev.setVisibility(View.INVISIBLE);

            FragmentFive frag = new FragmentFive();

            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, frag);
            ft.commit();
        }
        if(view==findViewById(R.id.data))
        {

            name.setVisibility(View.INVISIBLE);
            user.setVisibility(View.INVISIBLE);
            course1.setVisibility(View.INVISIBLE);
            coursev.setVisibility(View.INVISIBLE);
            surname1.setVisibility(View.INVISIBLE);
            surnamev.setVisibility(View.INVISIBLE);
            Bundle arguments=new Bundle();
            arguments.putString("idd2",idd);
            Fragmentview1 frag=new Fragmentview1();
            frag.setArguments(arguments);
            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment_place,frag);
            ft.commit();

        }

    }
    public interface DataFromActivityToFragment
    {
        void sendData(String data,String surname,String course,String id);
    }
    public String getmydata()
    {

        return idd;
    }
    public String getall()
    {
        return username;
    }
    public String getSurname(){return surname;}
    public String getcourse(){return course;}
}
