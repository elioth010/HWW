package com.umes.jeb.hww.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.bs.service.GetImageFromURLTask;
import com.umes.jeb.hww.eis.dto.BitacoraDTO;
import com.umes.jeb.hww.view.activity.AbstractActivity;

import java.util.List;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class TemperatureAdapter extends RecyclerView.Adapter<TemperatureAdapter.TemperatureViewHolder> {

    protected List<BitacoraDTO> cobranzaBeans;
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, BitacoraDTO dto);
    }

    public TemperatureAdapter() {
        super();
    }

    public BitacoraDTO getItem(int position) {
        return cobranzaBeans.get(position);
    }

    public TemperatureAdapter(List<BitacoraDTO> cobranzaBeans, AbstractActivity activity) {
        this.cobranzaBeans = cobranzaBeans;
        this.mContext = activity;
    }

    @Override
    public int getItemCount() {
        if (cobranzaBeans == null)
            return 0;
        return this.cobranzaBeans.size();
    }

    @Override
    public void onBindViewHolder(TemperatureViewHolder temperatureViewHolder, int position) {
        BitacoraDTO dto = cobranzaBeans.get(position);
        //loadBitmap(dto.getLogo(), temperatureViewHolder.imagenCobranza, temperatureViewHolder.progressBarImage);
        temperatureViewHolder.nombreCategoria.setText(dto.getDato().toString());
        temperatureViewHolder.descripcionCobranza.setText(dto.getMedidaSensor().getUnidadMedida().getTitulo());
        temperatureViewHolder.textCobranza.setText(dto.getMedidaSensor().getSensor().getTitulo());
        //loadBitmap(dto.getLogoCategoria(), temperatureViewHolder.imageCategoria, temperatureViewHolder.progressBarImage);
    }

    public void loadBitmap(String url, ImageView imageView, ProgressBar progressBar) {
        new GetImageFromURLTask(mContext, url, imageView, progressBar).execute();
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        TemperatureAdapter.mCallBack = mCallBack;
    }

    @Override
    public TemperatureViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview_pie, parent, false);
        (cardView.findViewById(R.id.relative_cardview)).setBackground(this.mContext.getResources().getDrawable(R.color.icons));

        return new TemperatureViewHolder(cardView, parent, cobranzaBeans);
    }

    public static class TemperatureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected View chartSensor;
        protected ImageView imagenCobranza;
        protected TextView textCobranza;

        protected TextView nombreCategoria;
        protected TextView descripcionCobranza;


        //private ViewGroup parent;
        protected List<BitacoraDTO> items;

        public TemperatureViewHolder(View itemView, ViewGroup parent, List<BitacoraDTO> list) {
            super(itemView);
            chartSensor  = (View) itemView.findViewById(R.id.chart_card_view);
            imagenCobranza = (ImageView) itemView.findViewById(R.id.image_card_view);
            //nombreCategoria= (TextView) itemView.findViewById(R.id.text_view_nombre_cat);
            textCobranza = (TextView) itemView.findViewById(R.id.text_card_view);
            items=list;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mCallBack != null) {
                mCallBack.onClick(v, items.get(super.getPosition()));
            }
        }
    }
}
