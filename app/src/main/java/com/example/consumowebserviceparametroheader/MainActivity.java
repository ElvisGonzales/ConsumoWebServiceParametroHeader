package com.example.consumowebserviceparametroheader;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.consumowebserviceparametroheader.WebServices.Asynchtask;
import com.example.consumowebserviceparametroheader.WebServices.WebService;
import com.example.consumowebserviceparametroheader.adactadores.ClienteAdapter;
import com.example.consumowebserviceparametroheader.models.Clientes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    RecyclerView revistarecicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        revistarecicle = (RecyclerView) findViewById(R.id.clientes_recicle);
        revistarecicle.setHasFixedSize(true);
        revistarecicle.setLayoutManager(new LinearLayoutManager(this));
        revistarecicle.setItemAnimator(new DefaultItemAnimator());
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.uealecpeterson.net/public/clientes/search";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){


                        try {
                            JSONObject JSONlista = new JSONObject(response);
                            JSONArray JSONlistaClientes= JSONlista.getJSONArray ("clientes");
                            ArrayList<Clientes> clientes = Clientes.JsonObjectsBuild(JSONlistaClientes);
                            //clientes = Clientes.JsonObjectsBuild(JSONlistaClientes);
                            int resId = R.anim.layout_animation_down_to_up;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
                            revistarecicle.setLayoutAnimation(animation);
                            ClienteAdapter adapatorrevista = new ClienteAdapter(getApplicationContext(), clientes);
                            revistarecicle.setAdapter(adapatorrevista);

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),  error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZHVzciI6OSwiZW1haWwiOiJjemFtYnJhbm9AdXRlcS5lZHUuZWMiLCJpYXQiOjE2NzExNTIzODQsImV4cCI6MTY3MTUxMjM4NH0.IdzNJMWek-mxJYd8dMHrA-akybg1AKDRJKx32cCTMfU");
                return param;
            }
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("fuente","1");
                return params;
            }
        };
        queue.add(stringRequest);
    }
    /*public void Enviar(View view){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api.uealecpeterson.net/public/login?correo=$czambrano@uteq.edu.ec&clave=$12345678",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("POST");
    }*/
    /*public void PostDatos(View view){
        final RecyclerView recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        //final TextView textView = (TextView) findViewById(R.id.txtlista);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.uealecpeterson.net/public/productos/search";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        //RecyclerView.setText(response);
                        //RecyclerView.setMovementMethod(new ScrollingMovementMethod());
                        //textView.setText(response);
                        //textView.setMovementMethod(new ScrollingMovementMethod());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast=Toast.makeText(MainActivity.this,"Error Verifique su Url de API Restfull!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                //textView.setText("That didn't work!");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZHVzciI6OSwiZW1haWwiOiJjemFtYnJhbm9AdXRlcS5lZHUuZWMiLCJpYXQiOjE2NzExNTIzODQsImV4cCI6MTY3MTUxMjM4NH0.IdzNJMWek-mxJYd8dMHrA-akybg1AKDRJKx32cCTMfU");
                return param;
            }
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("fuente","1");
                return params;
            }
        };
        queue.add(stringRequest);
    }*/
    /*@Override
    public void processFinish(String result) throws JSONException {

        ArrayList<Clientes> listaUsuarios = new ArrayList<Clientes> ();
        try {
            JSONObject JSONlista =  new JSONObject(result);
            JSONArray JSONlistaUsuarios=  JSONlista.getJSONArray("clientes");
            listaUsuarios = Clientes.JsonObjectsBuild(JSONlistaUsuarios);
            ClienteAdapter adapatorCliente = new ClienteAdapter(this, listaUsuarios);
            int resId = R.anim.layout_animation_down_to_up;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(adapatorCliente);

        }catch (JSONException e)
        {
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }*/
    /*private void getDataVolley(String param){
        queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://revistas.uteq.edu.ec/ws/issues.php?j_id="+param,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        sendData("Volley",param,response.toString());
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast= Toast.makeText(MainActivity.this, "Error en Volley", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
        );
        queue.add(request);
    }*/
    /*public void  btnEnviarDatos(View view){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.uealecpeterson.net/public/productos/search";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<String> lstProductos = new ArrayList<String> ();
                        JSONArray JSONlista = null;
                        try {
                            JSONlista = new JSONArray(response);
                            for(int i=0; i< JSONlista.length();i++){
                                JSONObject producto=  JSONlista.getJSONObject(i);
                                lstProductos.add(producto.getString("productos").toString());
                            }
                            TextView txtvolley = findViewById(R.id.txtJson);
                            txtvolley.setText(lstProductos.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),  error.getMessage(), Toast.LENGTH_LONG).show();
                TextView txtvolley = findViewById(R.id.txtJson);
                txtvolley.setText(error.networkResponse.statusCode +"  Error");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("czambrano@uteq.edu.ec", "12345678");
                return headerMap;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }*/
}