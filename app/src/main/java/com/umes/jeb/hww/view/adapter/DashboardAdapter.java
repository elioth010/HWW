package com.umes.jeb.hww.view.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.bs.service.GetImageFromURLTask;
import com.umes.jeb.hww.eis.bo.dominio.SensorType;
import com.umes.jeb.hww.eis.dto.BitacoraDTO;
import com.umes.jeb.hww.view.activity.AbstractActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    protected List<BitacoraDTO> sensorBeen;
    private AbstractActivity mContext;

    public DashboardAdapter() {
        super();
    }

    public BitacoraDTO getItem(int position) {
        return sensorBeen.get(position);
    }

    public DashboardAdapter(List<BitacoraDTO> sensorBeen, AbstractActivity activity) {
        this.sensorBeen = sensorBeen;
        this.mContext = activity;
    }

    @Override
    public int getItemCount() {
        if (sensorBeen == null)
            return 0;
        return this.sensorBeen.size();
    }

    @Override
    public void onBindViewHolder(DashboardViewHolder dashboardViewHolder, int position) {
        BitacoraDTO dto = sensorBeen.get(position);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH:mm");
        if(dto.getMedidaSensor().getSensor().getSensorType() == SensorType.PO){
            PieChartData data = new PieChartData();
            PieChartView chartView = (PieChartView) dashboardViewHolder.chartSensor;

            List<SliceValue> values = new ArrayList<>();
            if(Build.VERSION.SDK_INT>=23) {
                values.add(new SliceValue(dto.getDato().floatValue(), mContext.getResources().getColor(R.color.light_blue_300, null)));
                values.add(new SliceValue(100 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light, null)));
                data.setCenterText1Color(mContext.getResources().getColor(R.color.primary_text, null));
                data.setCenterText2Color(mContext.getResources().getColor(R.color.secondary_text, null));
            } else {
                values.add(new SliceValue(dto.getDato().floatValue(), mContext.getResources().getColor(R.color.light_blue_300)));
                values.add(new SliceValue(100 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light)));
                data.setCenterText1Color(mContext.getResources().getColor(R.color.primary_text));
                data.setCenterText2Color(mContext.getResources().getColor(R.color.secondary_text));
            }
            data.setValues(values);
            data.setCenterText1(dto.getDato().toString());
            data.setCenterText2(dto.getMedidaSensor().getUnidadMedida().getTitulo());
            data.setHasCenterCircle(true);
            chartView.setPieChartData(data);
            /*ViewGroup.LayoutParams params = dashboardViewHolder.chartSensor.getLayoutParams();
            ViewGroup parent = (ViewGroup) dashboardViewHolder.chartSensor.getParent();
            int index = parent.indexOfChild(dashboardViewHolder.chartSensor);
            parent.removeView(dashboardViewHolder.chartSensor);
            chartView.setLayoutParams(params);
            chartView.startDataAnimation();
            parent.addView(chartView, index);*/

        }else if(dto.getMedidaSensor().getSensor().getSensorType() == SensorType.TP){
            ColumnChartData data = new ColumnChartData();
            ColumnChartView chartView = (ColumnChartView) dashboardViewHolder.chartSensor;
            List<Column> columns = new ArrayList<Column>();
            Column columnTemp = new Column();
            columnTemp.setHasLabels(true);
            columnTemp.setHasLabels(true);
            List<SubcolumnValue> values = new ArrayList<>();
            if(Build.VERSION.SDK_INT>=23) {
                values.add(new SubcolumnValue(dto.getDato().floatValue(), mContext.getColor(R.color.green_400)));
            } else {
                values.add(new SubcolumnValue(dto.getDato().floatValue(), mContext.getResources().getColor(R.color.green_400)));
            }
            columnTemp.setValues(values);
            columns.add(columnTemp);
            data.setColumns(columns);
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            List<AxisValue> axisValues = new ArrayList<>();
            axisValues.add(new AxisValue(1));
            axisX.setValues(axisValues);
            axisX.setName("Temperatura Sensor");
            axisY.setName(dto.getMedidaSensor().getUnidadMedida().getTitulo());
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
            chartView.setColumnChartData(data);
        }else if(dto.getMedidaSensor().getSensor().getSensorType() == SensorType.BS){
            ColumnChartData data = new ColumnChartData();
            ColumnChartView chartView = (ColumnChartView) dashboardViewHolder.chartSensor;

            List<Column> columns = new ArrayList<Column>();
            Column columnTemp = new Column();
            columnTemp.setHasLabels(true);
            columnTemp.setHasLabels(true);
            List<SubcolumnValue> values = new ArrayList<>();
            if(Build.VERSION.SDK_INT>=23) {
                values.add(new SubcolumnValue(dto.getDato().floatValue(), mContext.getColor(R.color.green_400)));
            } else {
                values.add(new SubcolumnValue(dto.getDato().floatValue(), mContext.getResources().getColor(R.color.green_400)));
            }
            columnTemp.setValues(values);
            columns.add(columnTemp);
            data.setColumns(columns);
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            List<AxisValue> axisValues = new ArrayList<>();
            axisValues.add(new AxisValue(1));
            axisX.setValues(axisValues);
            axisX.setName("Respiraciones Sensor");
            axisY.setName(dto.getMedidaSensor().getUnidadMedida().getTitulo());
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
            chartView.setColumnChartData(data);
        }
        dashboardViewHolder.textSensor.setText(dto.getMedidaSensor().getSensor().getTitulo());
        dashboardViewHolder.dateSensor.setText(df.format(dto.getFechaHora()));
    }

    public void loadBitmap(String url, ImageView imageView, ProgressBar progressBar) {
        new GetImageFromURLTask(mContext, url, imageView, progressBar).execute();
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if(getItem(parent.getChildCount()).getMedidaSensor().getSensor().getSensorType() == SensorType.PO){
            View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview_pie, parent, false);
            return new DashboardViewHolder(cardView, parent, sensorBeen);
        }else{
            View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview_column, parent, false);
            return new DashboardViewHolder(cardView, parent, sensorBeen);
        }
    }

    public static class DashboardViewHolder extends RecyclerView.ViewHolder {

        protected View chartSensor;
        protected TextView textSensor;
        protected TextView dateSensor;

        //private ViewGroup parent;
        protected List<BitacoraDTO> items;

        public DashboardViewHolder(View itemView, ViewGroup parent, List<BitacoraDTO> list) {
            super(itemView);
            chartSensor = itemView.findViewById(R.id.chart_card_view);
            textSensor = (TextView) itemView.findViewById(R.id.text_view_nombre_sensor);
            dateSensor = (TextView) itemView.findViewById(R.id.text_view_fecha_sensor);
            items=list;
            //itemView.setOnClickListener(this);
        }

    }
}
