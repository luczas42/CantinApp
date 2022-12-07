package com.example.cantinappmobile.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.ActivityListsBinding;

import java.util.HashMap;
import java.util.Map;

public class ListsActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.cantinappmobile.databinding.ActivityListsBinding binding = ActivityListsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        callWebService();

    }

    private void callWebService() {
        String urlWebService = "http://192.168.5.104/testephp/getProducts.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest;

        stringRequest = new StringRequest(Request.Method.POST, urlWebService, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //o que acontece quando a resposta está ok
                Log.i("logLogin", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // o que acontece quando a resposta da erro
                Log.i("logLogin", "onErrorResponse: " + error);

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //parametros da request
                Map<String, String> params = new HashMap<>();
//                params.put("product_name", "kevytos shrek");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_lists);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}