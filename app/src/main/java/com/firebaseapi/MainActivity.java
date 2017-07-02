package com.firebaseapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.firebaseapi.network.AppController;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private String TAG = "VolleySampleWithFireBaseAPI";

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         txt = (TextView) findViewById(R.id.activity_main_txt);
        jsonObjectRequest();
    }

    private void jsonObjectRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        String tag_json_obj = "AndroidBootCampJsonObjectRequest";

        //adsoyad, yas ve email bilgisi icerir.
        String url = "https://fir-api-b95e3.firebaseio.com/.json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Json Object : ", response.toString());
                        txt.setText(response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.e(TAG, "Error: " + error.getMessage());

                pDialog.hide();
            }
        });

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}