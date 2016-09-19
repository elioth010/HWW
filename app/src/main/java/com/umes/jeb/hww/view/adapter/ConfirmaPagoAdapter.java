package com.umes.jeb.hww.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoDescripcionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class ConfirmaPagoAdapter extends RecyclerView.Adapter<ConfirmaPagoAdapter.ConfirmaPagoViewHolder> {

    protected List<CobranzaCampoBean> cobranzaCampoBeans;
    protected List<CobranzaCampoBean> cobranzaCamposBeanShow = new ArrayList<>();
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, CobranzaCampoBean dto);
    }

    public ConfirmaPagoAdapter() {
        super();
    }

    public CobranzaCampoBean getItem(int position) {
        return cobranzaCamposBeanShow.get(position);
    }

    public ConfirmaPagoAdapter(List<CobranzaCampoBean> cobranzaCampoBeans, AbstractActivity activity) {
        this.cobranzaCampoBeans = cobranzaCampoBeans;
        this.mContext = activity;
        if(cobranzaCampoBeans != null ) {
            for (CobranzaCampoBean bean : cobranzaCampoBeans) {
                if (bean.getVisibleConsulta()) {
                    this.cobranzaCamposBeanShow.add(bean);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (cobranzaCampoBeans == null)
            return 0;
        return this.cobranzaCampoBeans.size();
    }

    @Override
    public void onBindViewHolder(final ConfirmaPagoViewHolder confirmaPagoViewHolder, int position) {
        final CobranzaCampoBean dto = cobranzaCamposBeanShow.get(position);
        if(dto.getVisiblePago()) {
            confirmaPagoViewHolder.descripcionCampo.setText(dto.getDescripcion());
            if (dto!= null && dto.getEsSeleccionable() != null && dto.getEsSeleccionable()) {
                ArrayAdapter<CobranzaCampoDescripcionBean> adapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, dto.getListaDescripcion());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                confirmaPagoViewHolder.seleccionableCampoServicio.setAdapter(adapter);
                confirmaPagoViewHolder.seleccionableCampoServicio.setVisibility(View.VISIBLE);
                confirmaPagoViewHolder.seleccionableCampoServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        CobranzaCampoDescripcionBean selected = (CobranzaCampoDescripcionBean) confirmaPagoViewHolder.seleccionableCampoServicio.getSelectedItem();
                        dto.setValorIngreso(selected.getValor());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        dto.setValorIngreso(null);
                    }
                });
                confirmaPagoViewHolder.campoServicio.setVisibility(View.INVISIBLE);
            } else {
                //confirmaPagoViewHolder.campoServicio.setHint(dto.getDescripcion());
                InputFilter[] filter = new InputFilter[1];
                filter[0] = new InputFilter.LengthFilter(dto.getLongitud());
                confirmaPagoViewHolder.campoServicio.setFilters(filter);
                confirmaPagoViewHolder.campoServicio.setText(dto.getValorIngreso());
                confirmaPagoViewHolder.campoServicio.setEnabled(false);
                confirmaPagoViewHolder.campoServicio.setFocusable(false);
            }
        }else{
            confirmaPagoViewHolder.campoServicio.setVisibility(View.INVISIBLE);
            confirmaPagoViewHolder.descripcionCampo.setVisibility(View.INVISIBLE);
        }
            //confirmaPagoViewHolder.campoServicio.setInputType(InputType.);
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        ConfirmaPagoAdapter.mCallBack = mCallBack;
    }

    @Override
    public ConfirmaPagoViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_list_campos, parent, false);
        return new ConfirmaPagoViewHolder(cardView, parent, cobranzaCampoBeans);
    }

    public static class ConfirmaPagoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected EditText campoServicio;
        protected Spinner seleccionableCampoServicio;
        protected TextView descripcionCampo;

        //private ViewGroup parent;
        protected List<CobranzaCampoBean> items;

        public ConfirmaPagoViewHolder(View itemView, ViewGroup parent, List<CobranzaCampoBean> list) {
            super(itemView);
            campoServicio = (EditText) itemView.findViewById(R.id.edit_campo_servicio);
            seleccionableCampoServicio = (Spinner) itemView.findViewById(R.id.select_campo_servicio);
            descripcionCampo = (TextView) itemView.findViewById(R.id.text_view_descripcion_campo);
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
