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
import com.umes.jeb.hww.view.bean.CobranzaBean;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class CobranzaAdapter extends RecyclerView.Adapter<CobranzaAdapter.CobranzaViewHolder> {

    protected List<CobranzaBean> cobranzaBeans;
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, CobranzaBean dto);
    }

    public CobranzaAdapter() {
        super();
    }

    public CobranzaBean getItem(int position) {
        return cobranzaBeans.get(position);
    }

    public CobranzaAdapter(List<CobranzaBean> cobranzaBeans, AbstractActivity activity) {
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
    public void onBindViewHolder(CobranzaViewHolder cobranzaViewHolder, int position) {
        CobranzaBean dto = cobranzaBeans.get(position);
        loadBitmap(dto.getLogo(), cobranzaViewHolder.imagenCobranza, cobranzaViewHolder.progressBarImage);
        cobranzaViewHolder.nombreCategoria.setText(dto.getNombreCategoria());
        cobranzaViewHolder.descripcionCobranza.setText(dto.getPalabraClave());
        cobranzaViewHolder.textCobranza.setText(dto.getNombre());
        loadBitmap(dto.getLogoCategoria(), cobranzaViewHolder.imageCategoria, cobranzaViewHolder.progressBarImage);
    }

    public void loadBitmap(String url, ImageView imageView, ProgressBar progressBar) {
        new GetImageFromURLTask(mContext, url, imageView, progressBar).execute();
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        CobranzaAdapter.mCallBack = mCallBack;
    }

    @Override
    public CobranzaViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview, parent, false);
        (cardView.findViewById(R.id.relative_cardview)).setBackground(this.mContext.getResources().getDrawable(R.color.icons));

        return new CobranzaViewHolder(cardView, parent, cobranzaBeans);
    }

    public static class CobranzaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageCategoria;
        protected ImageView imagenCobranza;
        protected TextView textCobranza;
        protected ProgressBar progressBarImage;

        protected TextView nombreCategoria;
        protected TextView descripcionCobranza;


        //private ViewGroup parent;
        protected List<CobranzaBean> items;

        public CobranzaViewHolder(View itemView, ViewGroup parent, List<CobranzaBean> list) {
            super(itemView);
            imageCategoria  = (ImageView) itemView.findViewById(R.id.image_card_view_cat);
            imagenCobranza = (ImageView) itemView.findViewById(R.id.image_card_view);
            nombreCategoria= (TextView) itemView.findViewById(R.id.text_view_nombre_cat);
            textCobranza = (TextView) itemView.findViewById(R.id.text_card_view);
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
