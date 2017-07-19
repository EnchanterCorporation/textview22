package com.example.enchanter19.textview22;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {

    TextView txt1,txt2;
  //  Handler handler;
    String s,s1;
    private TrackGPS gps;
    int PERMISSION_ALL = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        txt1 = (TextView) findViewById(R.id.text1);
        txt2 = (TextView) findViewById(R.id.text2);
        gps = new TrackGPS(MainActivity.this);
        Double lat =gps .getLatitude();
        Double lng =  gps.getLongitude();
        s=Double.toString(lat);
        s1=Double.toString(lng);
        final Handler handler = new Handler();
        txt1.setText(s);
        txt2.setText(s1);

        handler.postDelayed(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(),"jhiaOI",Toast.LENGTH_SHORT).show();
                insertnew(s,s1);
                // this method will contain your almost-finished HTTP calls
                handler.postDelayed(this, 500);
            }
        }, 500);





    }
    public void insertnew(final String s,final  String s1) {

        StringRequest stringreqs=new StringRequest(Request.Method.POST, global_url.URL_INSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),"INTERNET CONNECTION NOT AVAILABLE",Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> uandme=new HashMap<String, String>();
                uandme.put("lat_andro",s);
                uandme.put("long_andro",s1);
                return uandme;
            }
        };
        AppController.getInstance().addToRequestQueue(stringreqs);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


}
