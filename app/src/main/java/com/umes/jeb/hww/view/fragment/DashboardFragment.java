package com.umes.jeb.hww.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.CobranzaActivity;
import com.umes.jeb.hww.view.adapter.DashboardAdapter;
import com.umes.jeb.hww.view.bean.CategoriaBean;
import com.umes.jeb.hww.view.bean.HomeBean;

import java.util.List;

/**
 * Created by elioth010 on 4/19/16.
 */
public class DashboardFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DashboardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeBean homeBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<CategoriaBean> beans = homeBean.getListaCategorias();
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recyclerview, container, false);
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        mAdapter = new DashboardAdapter(beans, (AbstractActivity)getContext());
        mAdapter.setCallBack(new DashboardAdapter.CallBack() {
            @Override
            public void onClick(View v, CategoriaBean dto) {
                Intent intent = new Intent(getContext(), CobranzaActivity.class);
                intent.putExtra("categoriaBean", dto);
                intent.putExtra("activity", "categoriaActivity");
                startActivity(intent);
                //CategoriaActivity.this.finish();
            }
        });
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public DashboardAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(DashboardAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public RecyclerView.LayoutManager getmLayoutManager() {
        return mLayoutManager;
    }

    public void setmLayoutManager(RecyclerView.LayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
