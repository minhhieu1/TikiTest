package com.minhhieu.tikitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecycleViewAdapter recycleViewAdapter;
    TextView textView;
    JSONArray jsonArray;
    ArrayList<String> listdata=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recycle_view);

        try {
            jsonArray = new JSONArray(readJSONFromAsset());
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    listdata.add(jsonArray.getString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recycleViewAdapter = new RecycleViewAdapter(listdata);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

         recyclerView.setLayoutManager(layoutManager);
         recyclerView.setAdapter(recycleViewAdapter);
         setDevideDecoration();


    }
    public void setDevideDecoration ()
    {
        DividerItemDecoration divider = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(
                ContextCompat.getDrawable(getBaseContext(), R.drawable.line_divide)
        );
        recyclerView.addItemDecoration(divider);
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("keywords.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
