package com.mj.cloudsfrom0061.learnrecyclerview.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mj.cloudsfrom0061.learnrecyclerview.Adapter.RecyclerViewAdapter;
import com.mj.cloudsfrom0061.learnrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<String> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.simple_recycler_view);
        adapter = new RecyclerViewAdapter(getApplicationContext(),datas);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(v.getContext(),"点击"+datas.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View v, int position) {
                Toast.makeText(v.getContext(),"长点击"+datas.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ListItemDecoration2(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_first:
                datas.add(0,"add first");
                adapter.notifyDataSetChanged();
                break;
            case R.id.add_last:
                datas.add(adapter.getItemCount(), "add last");
                adapter.notifyDataSetChanged();
                break;
            case R.id.remove_first:
                String value = datas.remove(0);
                Toast.makeText(getApplicationContext(),"移除:"+value,Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case R.id.remove_last:
                String value1 = datas.remove(adapter.getItemCount()-1);
                Toast.makeText(getApplicationContext(),"移除:"+value1,Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case R.id.horizontal:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.HORIZONTAL));
                break;
            case R.id.vertical:
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                recyclerView.addItemDecoration(new ListItemDecoration(this,LinearLayoutManager.VERTICAL));
                break;
            case R.id.grid_vr:
                recyclerView.setLayoutManager(new GridLayoutManager(this,4));
                recyclerView.addItemDecoration(new GirdItemDecoration(this));
            case R.id.grid_hor:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
                recyclerView.addItemDecoration(new GirdItemDecoration(this));
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        datas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            datas.add(String.valueOf(i));
        }
    }

}
