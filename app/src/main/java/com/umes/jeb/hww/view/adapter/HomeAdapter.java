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
import com.umes.jeb.hww.view.bean.HomeBean;

import java.util.List;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    protected List<HomeBean> homeBeans;
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, HomeBean dto);
    }

    public HomeAdapter() {
        super();
    }

    public HomeBean getItem(int position) {
        return homeBeans.get(position);
    }

    public HomeAdapter(List<HomeBean> homeBeans, AbstractActivity activity) {
        this.homeBeans = homeBeans;
        this.mContext = activity;
    }

    @Override
    public int getItemCount() {
        if (homeBeans == null)
            return 0;
        return this.homeBeans.size();
    }

    @Override
    public void onBindViewHolder(HomeViewHolder homeViewHolder, int position) {
        HomeBean dto = homeBeans.get(position);

        /*if(dto.getIsCategoria()) {
           // loadBitmap(dto.getImagen(), homeViewHolder.imageCobranza, homeViewHolder.progressBarImage);
            homeViewHolder.imagenHome.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.categorias));
        }else if(dto.getIsListaCobranza()){
            homeViewHolder.imagenHome.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.todas_cobranzas));
        }else if(dto.getIsFavorita()){
            homeViewHolder.imagenHome.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.cobranzas_favorito));
        }else if(dto.getIsUsoReciente()){
            homeViewHolder.imagenHome.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.cobranzas_recientes));
        }*/
        homeViewHolder.textHome.setText(dto.getNombre());
    }


    public void loadBitmap(String url, ImageView imageView, ProgressBar progressBar) {
        new GetImageFromURLTask(mContext, url, imageView, progressBar).execute();
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        HomeAdapter.mCallBack = mCallBack;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cardview, parent, false);
        (cardView.findViewById(R.id.relative_cardview)).setBackground(this.mContext.getResources().getDrawable(R.color.icons));
        return new HomeViewHolder(cardView, parent, homeBeans);
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imagenHome;
        protected TextView textHome;
        protected ProgressBar progressBarImage;

        //private ViewGroup parent;
        protected List<HomeBean> items;

        public HomeViewHolder(View itemView, ViewGroup parent, List<HomeBean> list) {
            super(itemView);
            imagenHome = (ImageView) itemView.findViewById(R.id.image_card_view);
            textHome = (TextView) itemView.findViewById(R.id.text_card_view);
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
