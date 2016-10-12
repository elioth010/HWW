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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class PulseOxygenAdapter extends RecyclerView.Adapter<PulseOxygenAdapter.PulseOxygenViewHolder> {

    protected List<BitacoraDTO> bitacoraDTOList;
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, BitacoraDTO dto);
    }

    public PulseOxygenAdapter() {
        super();
    }

    public BitacoraDTO getItem(int position) {
        return bitacoraDTOList.get(position);
    }

    public PulseOxygenAdapter(List<BitacoraDTO> bitacoraDTOList, AbstractActivity activity) {
        this.bitacoraDTOList = bitacoraDTOList;
        this.mContext = activity;
    }

    @Override
    public int getItemCount() {
        if (bitacoraDTOList == null)
            return 0;
        return this.bitacoraDTOList.size();
    }

    @Override
    public void onBindViewHolder(PulseOxygenViewHolder pulseOxygenViewHolder, int position) {
        BitacoraDTO dto = bitacoraDTOList.get(position);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pulseOxygenViewHolder.textSensor.setText(dto.getMedidaSensor().getSensor().getTitulo());
        try {
            pulseOxygenViewHolder.textFecha.setText(df.format(df2.parse(dto.getFechaHora())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pulseOxygenViewHolder.textValor.setText(dto.getMedidaSensor().getUnidadMedida().getTitulo()+" : " +String.format("%2.2f", dto.getDato()));
    }

    @Override
    public PulseOxygenViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_list_items, parent, false);
        return new PulseOxygenViewHolder(cardView, parent, bitacoraDTOList);
    }

    public static class PulseOxygenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView textSensor;
        protected TextView textValor;
        protected TextView textFecha;


        //private ViewGroup parent;
        protected List<BitacoraDTO> items;

        public PulseOxygenViewHolder(View itemView, ViewGroup parent, List<BitacoraDTO> list) {
            super(itemView);
            textSensor = (TextView) itemView.findViewById(R.id.list_items_sensor);
            textValor = (TextView) itemView.findViewById(R.id.list_items_valor);
            textFecha = (TextView) itemView.findViewById(R.id.list_items_fecha);
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
