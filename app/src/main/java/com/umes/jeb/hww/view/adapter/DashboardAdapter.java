package com.umes.jeb.hww.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.bs.service.GetImageFromURLTask;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.bean.SensorBean;

import java.util.List;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    protected List<SensorBean> sensorBeen;
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, SensorBean dto);
    }

    public DashboardAdapter() {
        super();
    }

    public SensorBean getItem(int position) {
        return sensorBeen.get(position);
    }

    public DashboardAdapter(List<SensorBean> sensorBeen, AbstractActivity activity) {
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
        SensorBean dto = sensorBeen.get(position);
        loadBitmap(dto.getImage(), dashboardViewHolder.imagenCategoria, dashboardViewHolder.progressBarImage);
        //dashboardViewHolder.nombreCategoria.setText(dto.getNombreCategoria());
        dashboardViewHolder.descripcionCobranza.setText("");
        dashboardViewHolder.textCategoria.setText(dto.getNombre());
    }

    public void loadBitmap(String url, ImageView imageView, ProgressBar progressBar) {
        new GetImageFromURLTask(mContext, url, imageView, progressBar).execute();
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        DashboardAdapter.mCallBack = mCallBack;
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview, parent, false);
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams) (cardView.findViewById(R.id.contenedor)).getLayoutParams();
        layoutParams.setMargins(25,0,0,0);

        (cardView.findViewById(R.id.relative_cardview)).setBackground(this.mContext.getResources().getDrawable(R.color.icons));
        ((RelativeLayout)cardView.findViewById(R.id.contenedor)).removeView(((cardView.findViewById(R.id.contenedor)).findViewById(R.id.text_view_nombre_cat)));
        ((RelativeLayout)cardView.findViewById(R.id.relative_cardview)).removeView(((cardView.findViewById(R.id.relative_cardview)).findViewById(R.id.image_card_view_cat)));


        return new DashboardViewHolder(cardView, parent, sensorBeen);
    }

    public static class DashboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imagenCategoria;
        protected TextView textCategoria;
        protected ProgressBar progressBarImage;
        protected TextView descripcionCobranza;
        protected TextView nombreCategoria;

        //private ViewGroup parent;
        protected List<SensorBean> items;

        public DashboardViewHolder(View itemView, ViewGroup parent, List<SensorBean> list) {
            super(itemView);
            imagenCategoria = (ImageView) itemView.findViewById(R.id.image_card_view);
            textCategoria = (TextView) itemView.findViewById(R.id.text_card_view);
            RelativeLayout.LayoutParams layoutParams =
                    (RelativeLayout.LayoutParams)textCategoria.getLayoutParams();
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            nombreCategoria= (TextView) itemView.findViewById(R.id.text_view_nombre_cat);
            descripcionCobranza= (TextView) itemView.findViewById(R.id.text_card_view_descripcion);
            progressBarImage = (ProgressBar) itemView.findViewById(R.id.progress_bar_img_load);
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
