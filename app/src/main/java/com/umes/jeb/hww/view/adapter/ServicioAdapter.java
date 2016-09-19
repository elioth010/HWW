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
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.util.List;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {

    protected List<ServicioBean> servicioBeans;
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, ServicioBean dto);
    }

    public ServicioAdapter() {
        super();
    }

    public ServicioBean getItem(int position) {
        return servicioBeans.get(position);
    }

    public ServicioAdapter(List<ServicioBean> servicioBeans, AbstractActivity activity) {
        this.servicioBeans = servicioBeans;
        this.mContext = activity;
    }

    @Override
    public int getItemCount() {
        if (servicioBeans == null)
            return 0;
        return this.servicioBeans.size();
    }

    @Override
    public void onBindViewHolder(ServicioViewHolder servicioViewHolder, int position) {
        ServicioBean dto = servicioBeans.get(position);

        loadBitmap(dto.getLogoCategoria(), servicioViewHolder.imageCategoria, servicioViewHolder.progressBarImage);
        servicioViewHolder.nombreCategoria.setText(dto.getNombreCategoria());

        loadBitmap(dto.getUrlLogo(), servicioViewHolder.imagenServicio, servicioViewHolder.progressBarImage);
        servicioViewHolder.textServicio.setText(dto.getDescripcion());
    }


    public void loadBitmap(String url, ImageView imageView, ProgressBar progressBar) {
        new GetImageFromURLTask(mContext, url, imageView, progressBar).execute();
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        ServicioAdapter.mCallBack = mCallBack;
    }

    @Override
    public ServicioViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview, parent, false);
        (cardView.findViewById(R.id.relative_cardview)).setBackground(this.mContext.getResources().getDrawable(R.color.icons));
        return new ServicioViewHolder(cardView, parent, servicioBeans);
    }

    public static class ServicioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageCategoria;
        protected TextView nombreCategoria;
        protected ImageView imagenServicio;
        protected TextView textServicio;
        protected TextView descripcionServicio;
        protected ProgressBar progressBarImage;

        //private ViewGroup parent;
        protected List<ServicioBean> items;

        public ServicioViewHolder(View itemView, ViewGroup parent, List<ServicioBean> list) {
            super(itemView);
            imageCategoria  = (ImageView) itemView.findViewById(R.id.image_card_view_cat);
            nombreCategoria= (TextView) itemView.findViewById(R.id.text_view_nombre_cat);
            descripcionServicio= (TextView) itemView.findViewById(R.id.text_card_view_descripcion);
            imagenServicio = (ImageView) itemView.findViewById(R.id.image_card_view);
            textServicio = (TextView) itemView.findViewById(R.id.text_card_view);
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
