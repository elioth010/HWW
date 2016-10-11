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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
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

    public DashboardAdapter(List<BitacoraDTO> sensorBeen, AbstractActivity activity) {
        this.sensorBeen = sensorBeen;
        this.mContext = activity;
    }

    public BitacoraDTO getItem(int position) {
        return sensorBeen.get(position);
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
        //if(dto.getMedidaSensor().getSensor().getSensorType() == SensorType.PO){
        PieChartData data = new PieChartData();
        PieChartView chartView = (PieChartView) dashboardViewHolder.chartSensor;

        List<SliceValue> values = new ArrayList<>();
        if (dto.getMedidaSensor().getSensor().getSensorType() == SensorType.PO) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (dto.getMedidaSensor().getUnidadMedida().getTitulo().equalsIgnoreCase("PRbpm")) {
                    int color = 0;
                    if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 60) {
                        color = mContext.getColor(R.color.red_900);
                    }
                    if (dto.getDato().floatValue() > 64 && dto.getDato().floatValue() < 80) {
                        color = mContext.getColor(R.color.green_400);
                    }
                    if (dto.getDato().floatValue() > 80) {
                        color = mContext.getColor(R.color.orange);
                    }

                    values.add(new SliceValue(dto.getDato().floatValue(), color));
                    //if()
                    values.add(new SliceValue(200 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light, null)));
                } else {
                    int color = 0;
                    if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 95) {
                        color = mContext.getColor(R.color.red_900);
                    }
                    if (dto.getDato().floatValue() > 95.0 && dto.getDato().floatValue() < 98.5) {
                        color = mContext.getColor(R.color.green_400);
                    }
                    if (dto.getDato().floatValue() > 98.4) {
                        color = mContext.getColor(R.color.orange);
                    }

                    values.add(new SliceValue(dto.getDato().floatValue(), color));
                    //if()
                    values.add(new SliceValue(100 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light, null)));
                }
            } else {
                if (dto.getMedidaSensor().getUnidadMedida().getTitulo().equalsIgnoreCase("PRbpm")) {
                    int color = 0;
                    if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 60) {
                        color = mContext.getResources().getColor(R.color.red_900);
                    }
                    if (dto.getDato().floatValue() > 64 && dto.getDato().floatValue() < 80) {
                        color = mContext.getResources().getColor(R.color.green_400);
                    }
                    if (dto.getDato().floatValue() >= 80) {
                        color = mContext.getResources().getColor(R.color.orange);
                    }

                    values.add(new SliceValue(dto.getDato().floatValue(), color));
                    values.add(new SliceValue(200 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light)));

                    data.setCenterText2Color(mContext.getResources().getColor(R.color.secondary_text));
                } else {
                    int color = 0;
                    if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 95) {
                        color = mContext.getResources().getColor(R.color.red_900);
                    }
                    if (dto.getDato().floatValue() > 95.0 && dto.getDato().floatValue() < 98.5) {
                        color = mContext.getResources().getColor(R.color.green_400);
                    }
                    if (dto.getDato().floatValue() >= 98.5) {
                        color = mContext.getResources().getColor(R.color.orange);
                    }

                    values.add(new SliceValue(dto.getDato().floatValue(), color));
                    values.add(new SliceValue(100 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light)));

                    data.setCenterText2Color(mContext.getResources().getColor(R.color.secondary_text));
                }

            }
        } else if (dto.getMedidaSensor().getSensor().getSensorType() == SensorType.BS) {
            if (Build.VERSION.SDK_INT >= 23) {

                int color = 0;
                if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 30) {
                    color = mContext.getColor(R.color.green_400);
                }
                if (dto.getDato().floatValue() > 30 && dto.getDato().floatValue() <= 45) {
                    color = mContext.getColor(R.color.orange);
                }
                if (dto.getDato().floatValue() > 45) {
                    color = mContext.getColor(R.color.red_500);
                }
                values.add(new SliceValue(dto.getDato().floatValue(), color));
                values.add(new SliceValue(60 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light, null)));

            } else {
                int color = 0;
                if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 30) {
                    color = mContext.getResources().getColor(R.color.green_400);
                }
                if (dto.getDato().floatValue() > 30 && dto.getDato().floatValue() <= 45) {
                    color = mContext.getResources().getColor(R.color.orange);
                }
                if (dto.getDato().floatValue() > 45) {
                    color = mContext.getResources().getColor(R.color.red_500);
                }

                values.add(new SliceValue(dto.getDato().floatValue(), color));
                values.add(new SliceValue(60 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light)));
            }

        } else if (dto.getMedidaSensor().getSensor().getSensorType() == SensorType.TP) {
            if (Build.VERSION.SDK_INT >= 23) {

                int color = 0;
                if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 34) {
                    color = mContext.getColor(R.color.orange);
                }
                if (dto.getDato().floatValue() > 34 && dto.getDato().floatValue() < 38) {
                    color = mContext.getColor(R.color.green_400);
                }
                if (dto.getDato().floatValue() >= 38) {
                    color = mContext.getColor(R.color.red_500);
                }
                values.add(new SliceValue(dto.getDato().floatValue(), color));
                values.add(new SliceValue(42 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light, null)));

            } else {
                int color = 0;
                if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 34) {
                    color = mContext.getResources().getColor(R.color.green_400);
                }
                if (dto.getDato().floatValue() > 34 && dto.getDato().floatValue() < 38) {
                    color = mContext.getResources().getColor(R.color.orange);
                }
                if (dto.getDato().floatValue() >= 38) {
                    color = mContext.getResources().getColor(R.color.red_500);
                }

                values.add(new SliceValue(dto.getDato().floatValue(), color));
                values.add(new SliceValue(42 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light)));
            }
        } else if (dto.getMedidaSensor().getSensor().getSensorType() == SensorType.BP) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (dto.getMedidaSensor().getUnidadMedida().getTitulo().equalsIgnoreCase("SISmmHg")) {
                    int color = 0;
                    if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 90) {
                        color = mContext.getColor(R.color.blue_800);
                    }
                    if (dto.getDato().floatValue() > 90 && dto.getDato().floatValue() <= 120) {
                        color = mContext.getColor(R.color.green_400);
                    }
                    if (dto.getDato().floatValue() > 120 && dto.getDato().floatValue() < 139) {
                        color = mContext.getColor(R.color.orange);
                    }
                    if (dto.getDato().floatValue() >= 140 && dto.getDato().floatValue() < 159) {
                        color = mContext.getColor(R.color.deep_orange_400);
                    }
                    if (dto.getDato().floatValue() >= 160 && dto.getDato().floatValue() < 179) {
                        color = mContext.getColor(R.color.red_500);
                    }
                    if (dto.getDato().floatValue() > 180) {
                        color = mContext.getColor(R.color.red_900);
                    }

                    values.add(new SliceValue(dto.getDato().floatValue(), color));
                    values.add(new SliceValue(180 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light, null)));

                } else {
                    int color = 0;
                    if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 60) {
                        color = mContext.getColor(R.color.blue_800);
                    }
                    if (dto.getDato().floatValue() > 60 && dto.getDato().floatValue() <= 79) {
                        color = mContext.getColor(R.color.green_400);
                    }
                    if (dto.getDato().floatValue() > 80 && dto.getDato().floatValue() < 89) {
                        color = mContext.getColor(R.color.orange);
                    }
                    if (dto.getDato().floatValue() >= 90 && dto.getDato().floatValue() < 99) {
                        color = mContext.getColor(R.color.deep_orange_400);
                    }
                    if (dto.getDato().floatValue() >= 100 && dto.getDato().floatValue() < 109) {
                        color = mContext.getColor(R.color.red_500);
                    }
                    if (dto.getDato().floatValue() > 110) {
                        color = mContext.getColor(R.color.red_900);
                    }
                    values.add(new SliceValue(dto.getDato().floatValue(), color));
                    values.add(new SliceValue(110 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light, null)));
                }

            } else {
                if (dto.getMedidaSensor().getUnidadMedida().getTitulo().equalsIgnoreCase("SISmmHg")) {
                    int color = 0;
                    if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 90) {
                        color = mContext.getResources().getColor(R.color.blue_800);
                    }
                    if (dto.getDato().floatValue() > 90 && dto.getDato().floatValue() <= 120) {
                        color = mContext.getResources().getColor(R.color.green_400);
                    }
                    if (dto.getDato().floatValue() > 120 && dto.getDato().floatValue() < 139) {
                        color = mContext.getResources().getColor(R.color.orange);
                    }
                    if (dto.getDato().floatValue() >= 140 && dto.getDato().floatValue() < 159) {
                        color = mContext.getResources().getColor(R.color.deep_orange_400);
                    }
                    if (dto.getDato().floatValue() >= 160 && dto.getDato().floatValue() < 179) {
                        color = mContext.getResources().getColor(R.color.red_500);
                    }
                    if (dto.getDato().floatValue() > 180) {
                        color = mContext.getResources().getColor(R.color.red_900);
                    }

                    values.add(new SliceValue(dto.getDato().floatValue(), color));
                    values.add(new SliceValue(180 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light)));
                } else {
                    int color = 0;
                    if (dto.getDato().floatValue() > 0 && dto.getDato().floatValue() < 60) {
                        color = mContext.getResources().getColor(R.color.blue_800);
                    }
                    if (dto.getDato().floatValue() > 60 && dto.getDato().floatValue() <= 79) {
                        color = mContext.getResources().getColor(R.color.green_400);
                    }
                    if (dto.getDato().floatValue() > 80 && dto.getDato().floatValue() < 89) {
                        color = mContext.getResources().getColor(R.color.orange);
                    }
                    if (dto.getDato().floatValue() >= 90 && dto.getDato().floatValue() < 99) {
                        color = mContext.getResources().getColor(R.color.deep_orange_400);
                    }
                    if (dto.getDato().floatValue() >= 100 && dto.getDato().floatValue() < 109) {
                        color = mContext.getResources().getColor(R.color.red_500);
                    }
                    if (dto.getDato().floatValue() > 110) {
                        color = mContext.getResources().getColor(R.color.red_900);
                    }

                    values.add(new SliceValue(dto.getDato().floatValue(), color));
                    values.add(new SliceValue(110 - dto.getDato().floatValue(), mContext.getResources().getColor(R.color.gray_light)));
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            data.setCenterText1Color(mContext.getColor(R.color.primary_text));
            data.setCenterText2Color(mContext.getColor(R.color.secondary_text));
        } else {
            data.setCenterText1Color(mContext.getResources().getColor(R.color.primary_text));
            data.setCenterText2Color(mContext.getResources().getColor(R.color.secondary_text));
        }

        data.setCenterText1FontSize(14);
        data.setValues(values);
        data.setCenterText1(dto.getDato().toString());
        data.setCenterText2(dto.getMedidaSensor().getUnidadMedida().getTitulo());
        data.setHasCenterCircle(true);
        chartView.setPieChartData(data);
        dashboardViewHolder.textSensor.setText(dto.getMedidaSensor().getSensor().getTitulo());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dashboardViewHolder.dateSensor.setText(df.format(dateFormat.parse(dto.getFechaHora())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void loadBitmap(String url, ImageView imageView, ProgressBar progressBar) {
        new GetImageFromURLTask(mContext, url, imageView, progressBar).execute();
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview_pie, parent, false);
        return new DashboardViewHolder(cardView, parent, sensorBeen);
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
            items = list;
            //itemView.setOnClickListener(this);
        }

    }
}
