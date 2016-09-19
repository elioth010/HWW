package com.umes.jeb.hww.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.MedioPago;
import com.umes.jeb.hww.util.persistence.ListaParametrosDTO;
import com.umes.jeb.hww.util.persistence.Parametro;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.ConfirmaPagoActivity;
import com.umes.jeb.hww.view.activity.DatosFacturacionActivity;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ItemBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.util.ArrayList;
import java.util.List;

public class ListItemsAdapter extends BaseAdapter {

    private Activity mContext;
    private List<ItemBean> rowItem;
    private Integer medioDePagoSeleccionado;
    private Integer medioPagoSeleccionado;
    private String nameActivity;
    private String cajero;
    private boolean clave;
    private String monto;

    private ServicioBean servicioBean;
    private ArrayList<CobranzaCampoBean> beans;

    public ListItemsAdapter(Activity context, List<ItemBean> rowItem, Integer medioDePagoSeleccionado, String nameActivity, String cajero, boolean clave, String monto,ArrayList<CobranzaCampoBean> beans, ServicioBean servicioBean) {
        super();
        this.mContext = context;
        this.rowItem = rowItem;
        this.medioDePagoSeleccionado = medioDePagoSeleccionado;
        this.nameActivity = nameActivity;
        this.cajero = cajero;
        this.clave = clave;
        this.monto = monto;
        this.beans=beans;
        this.servicioBean=servicioBean;
    }

    @Override
    public int getCount() {
        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.fragment_list_items, null);
        }
        try {
            ImageView iconImageView = (ImageView) convertView.findViewById(R.id.list_item_icon);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.list_items_title);

            final ItemBean row = rowItem.get(position);
            iconImageView.setImageResource(row.getIcon());

            titleTextView.setText(row.getTitle());

            convertView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(mContext, DatosFacturacionActivity.class);
                    intent.putExtra("clave", clave);
                    intent.putExtra("cajero", cajero);
                    intent.putExtra("monto", monto);
                    ListaParametrosDTO parametros = new ListaParametrosDTO();
                    parametros.getParametros().add(new Parametro("NO_TARJETA = ?", row.getDetail()));
                    parametros.getParametros().add(new Parametro("AND MEDIO_SELECCIONADO = ?", medioDePagoSeleccionado));
                    List<MedioPago> medioPagos = ((AbstractActivity) mContext).findByClassWithParameters(MedioPago.class, parametros);
                    if (medioPagos != null && medioPagos.size() > 0) {
                        intent.putExtra("medioSeleccionado", medioPagos.get(0));
                        intent.putExtra("medioPagoSeleccionado", medioDePagoSeleccionado);
                    }
                    mContext.startActivity(intent);
                    mContext.finish();*/
                    Intent intent = new Intent(mContext, ConfirmaPagoActivity.class);
                    intent.putExtra("listaBeans", beans);
                    intent.putExtra("servicioBean", servicioBean);
                    intent.putExtra("tarjetaSeleccionada", row.getTitle());
                    intent.putExtra("total", ((AbstractActivity) mContext).getMontoTotal());
                    intent.putExtra("totalSinImpuestos", ((AbstractActivity)mContext).getMontoSinImpuestos());
                    intent.putExtra("impuestos", ((AbstractActivity) mContext).getImpuestos());
                    mContext.startActivity(intent);
                    mContext.finish();

                }

            });

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }

        return convertView;
    }

}
