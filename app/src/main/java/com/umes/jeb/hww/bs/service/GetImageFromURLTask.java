package com.umes.jeb.hww.bs.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.view.activity.AbstractActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by elioth010 on 4/14/16.
 */
public class GetImageFromURLTask extends AbstractGetTask<String, Void, Bitmap> {

    private AbstractActivity parentActivity;

    private final String mResourceURL = "http://52.10.165.229:2443/services";
    //private final String mResourceURL = "http://172.16.5.82:8080/services";
    private String urlImage;
    private final WeakReference<ImageView> imageViewReference;
    private final WeakReference<ProgressBar> progressBarReference;

    public GetImageFromURLTask(AbstractActivity parentActivity, String url, ImageView imageView, ProgressBar progressBar) {
        super(parentActivity);
        this.parentActivity = parentActivity;
        this.urlImage = url;
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.progressBarReference = new WeakReference<ProgressBar>(progressBar);
    }

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        if (progressBarReference != null) {
            final ProgressBar progressBar = progressBarReference.get();
            if (progressBar != null) {
//                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            final Bitmap bitmap = parentActivity.getBitmapFromMemCache(urlImage);
            if(bitmap !=null){
                return bitmap;
            }
            URL url = new URL(this.mResourceURL + urlImage);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            parentActivity.addBitmapToMemoryCache(urlImage, bmp);
            return bmp;
        } catch (IOException e) {
            //this.getParentActivity().show("RE");
            return BitmapFactory.decodeResource(this.getParentActivity().getResources(), R.drawable.ic_launcher);
        } catch (Exception e) {
            onError(this.getParentActivity().getString(R.string.app_toast_error_server_unknow_message), e);
            return BitmapFactory.decodeResource(this.getParentActivity().getResources(), R.drawable.ic_launcher);
        }
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

        if(progressBarReference!= null){
            final ProgressBar progressBar = progressBarReference.get();
            if(progressBar!=null){
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    public AbstractActivity getParentActivity() {
        return parentActivity;
    }

    public void setParentActivity(AbstractActivity parentActivity) {
        this.parentActivity = parentActivity;
    }
}
