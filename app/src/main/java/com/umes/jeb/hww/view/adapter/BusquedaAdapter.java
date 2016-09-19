package com.umes.jeb.hww.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.bs.service.GetImageFromURLTask;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.util.List;
import java.util.Random;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class BusquedaAdapter extends RecyclerView.Adapter<BusquedaAdapter.BusquedaViewHolder> {

    protected List<CobranzaBean> cobranzaBeans;
    private AbstractActivity mContext;

    static CallBack mCallBack;
    private int lastPosition = -1;

    public interface CallBack {
        void onClick(View v, CobranzaBean dto);
    }

    public BusquedaAdapter() {
        super();
    }

    public CobranzaBean getItem(int position) {
        return cobranzaBeans.get(position);
    }

    public BusquedaAdapter(List<CobranzaBean> cobranzaBeans, AbstractActivity activity) {
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
    public void onBindViewHolder(BusquedaViewHolder busquedaViewHolder, int position) {
        CobranzaBean bean = cobranzaBeans.get(position);

        loadBitmap(bean.getLogoCategoria(), busquedaViewHolder.imageCategoria, busquedaViewHolder.progressBarImage);
        busquedaViewHolder.nombreCategoria.setText(bean.getNombreCategoria());
        busquedaViewHolder.textViewTitle.setText(bean.getNombre());
        busquedaViewHolder.descripcionCobranza.setText(bean.getPalabraClave());
        loadBitmap(bean.getLogo(), busquedaViewHolder.imageCobranza, busquedaViewHolder.progressBarImage);

        //setAnimation(busquedaViewHolder.itemView, position);
    }

    public void loadBitmap(String url, ImageView imageView, ProgressBar progressBar) {
        new GetImageFromURLTask(mContext, url, imageView, progressBar).execute();
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        BusquedaAdapter.mCallBack = mCallBack;
    }

    @Override
    public BusquedaViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview, parent, false);
        int[] androidColors = mContext.getResources().getIntArray(R.array.cardcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        return new BusquedaViewHolder(cardView, parent, cobranzaBeans);
    }

    public static class BusquedaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageCategoria;
        protected TextView nombreCategoria;
        protected ImageView imageCobranza;
        protected TextView descripcionCobranza;
        protected TextView textViewTitle;
        protected ProgressBar progressBarImage;

        //private ViewGroup parent;
        protected List<CobranzaBean> items;

        public BusquedaViewHolder(View itemView, ViewGroup parent, List<CobranzaBean> list) {
            super(itemView);
            imageCategoria  = (ImageView) itemView.findViewById(R.id.image_card_view_cat);
            nombreCategoria= (TextView) itemView.findViewById(R.id.text_view_nombre_cat);
            imageCobranza = (ImageView) itemView.findViewById(R.id.image_card_view);
            textViewTitle = (TextView) itemView.findViewById(R.id.text_card_view);
            descripcionCobranza= (TextView) itemView.findViewById(R.id.text_card_view_descripcion);
            progressBarImage = (ProgressBar) itemView.findViewById(R.id.progress_bar_img_load);
            items = list;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mCallBack != null) {
                mCallBack.onClick(v, items.get(super.getPosition()));
            }
        }
    }

    /*private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.abc_slide_in_top);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }*/
}
