package com.example.volleyactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.volleyactivity.model.GitHubUser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JSonArrayRequest extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView textView;
    List<GitHubUser> list = new ArrayList<>();
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_array_request);
        ButterKnife.bind(this);
        callVolley();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void callVolley() {
        String url = "https://api.github.com/users";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int len = response.length();
                Gson gson = new Gson();

                for(int i=0;i<len;i++){
                    GitHubUser user = null;
                    try {
                        user = gson.fromJson(response.getJSONObject(i).toString(), GitHubUser.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(JSonArrayRequest.this, "size : "+list.size(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            GitHubUser user = list.get(position);
            holder.login.setText(user.getId());
            Glide.with(getApplicationContext()).load(user.getAvatarUrl()).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView login;
            ImageView img;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                login= itemView.findViewById(R.id.loginTxt);
                img = itemView.findViewById(R.id.image1);
            }
        }
    }

}
