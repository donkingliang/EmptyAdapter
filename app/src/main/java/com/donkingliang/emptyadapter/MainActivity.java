package com.donkingliang.emptyadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private EmptyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = findViewById(R.id.rv_list);

        mAdapter = new EmptyAdapter(this);
        mAdapter.showEmptyView(true);
        setLinearLayoutManager();
        rvList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_ment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ll_manager:
                setLinearLayoutManager();
                return true;

            case R.id.gl_manager:
                setGridLayoutManager();
                return true;

            case R.id.clear:
                mAdapter.setList(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setLinearLayoutManager() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setGridLayoutManager() {
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 如果是空布局，让它占满一行
                if (mAdapter.isEmptyPosition(position)) {
                    return layoutManager.getSpanCount();
                }
                return 1;
            }
        });
        rvList.setLayoutManager(layoutManager);
    }
}
