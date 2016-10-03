package com.umes.jeb.hww.view.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.dto.BitacoraDTO;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.adapter.PulseOxygenAdapter;
import com.umes.jeb.hww.view.bean.HomeBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.formatter.AxisValueFormatter;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by elioth010 on 4/19/16.
 */
public class PulseOxygenFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RelativeLayout mainLayout;
    private PulseOxygenAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeBean homeBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<BitacoraDTO> beans = homeBean.getHistorialBeanList();
        mainLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_historic_cardview_column, container, false);
        mRecyclerView = (RecyclerView) mainLayout.findViewById(R.id.recyclerview_list);
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new PulseOxygenAdapter(beans, (AbstractActivity)getActivity());
        mAdapter.setCallBack(new PulseOxygenAdapter.CallBack() {
            @Override
            public void onClick(View v, BitacoraDTO dto) {

            }
        });
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return mainLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ColumnChartData data = new ColumnChartData();
        ColumnChartView chartView = (ColumnChartView) view.findViewById(R.id.chart_card_view);
        List<Column> columns = new ArrayList<>();
        List<AxisValue> axisValues = new ArrayList<>();
        int i= 0;
        Column columnTemp = new Column();
        List<SubcolumnValue> values = new ArrayList<>();
        for(BitacoraDTO bitacora : getHomeBean().getHistorialResumenBeanList()){
            if(i%2==0){
                columnTemp = new Column();
                values = new ArrayList<>();
                columns.add(columnTemp);
                SimpleDateFormat df = new SimpleDateFormat("MMM");
                axisValues.add(new AxisValue(i).setLabel(df.format(bitacora.getFechaHora())));
            }
            columnTemp.setHasLabels(true);
            columnTemp.setHasLabels(true);
            int color = 0;
            if(Build.VERSION.SDK_INT>=23) {
                Random rnd = new Random();
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            } else {
                Random rnd = new Random();
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            }
            SubcolumnValue value = new SubcolumnValue(bitacora.getDato().floatValue(), color);
            value.setLabel(bitacora.getMedidaSensor().getUnidadMedida().getTitulo());
            values.add(value);
            columnTemp.setValues(values);
            i++;
        }
        data.setColumns(columns);
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setValues(axisValues);
        axisX.setName(getString(R.string.chart_historic_text));
        axisX.setHasTiltedLabels(true);
        axisY.setName(getString(R.string.sensor_pulse_oxigen_name));
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        chartView.setColumnChartData(data);

        super.onViewCreated(view, savedInstanceState);
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public PulseOxygenAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(PulseOxygenAdapter mAdapter) {
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
