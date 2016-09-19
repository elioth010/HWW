package com.umes.jeb.hww.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
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
public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.PagoViewHolder> {

    protected List<CobranzaCampoBean> cobranzaCampoBeans;
    protected List<CobranzaCampoBean> cobranzaCamposBeanShow = new ArrayList<>();
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, CobranzaCampoBean dto);
    }

    public PagoAdapter() {
        super();
    }

    public CobranzaCampoBean getItem(int position) {
        return cobranzaCamposBeanShow.get(position);
    }

    public PagoAdapter(List<CobranzaCampoBean> cobranzaCampoBeans, AbstractActivity activity) {
        this.cobranzaCampoBeans = cobranzaCampoBeans;
        if(cobranzaCampoBeans != null ) {
            for (CobranzaCampoBean bean : cobranzaCampoBeans) {
                if (bean.getVisiblePago()) {
                    this.cobranzaCamposBeanShow.add(bean);
                }
            }
        }
        this.mContext = activity;
    }

    @Override
    public int getItemCount() {
        if (cobranzaCampoBeans == null){
            return 0;
        }
        return this.cobranzaCamposBeanShow.size();
    }

    @Override
    public void onBindViewHolder(final PagoViewHolder pagoViewHolder, int position) {
        final CobranzaCampoBean dto = cobranzaCamposBeanShow.get(position);
        pagoViewHolder.parent.setVisibility(View.VISIBLE);
        if(dto.getVisiblePago()) {
            pagoViewHolder.descripcionCampo.setText(dto.getDescripcion());
            if (dto!= null && dto.getEsSeleccionable() != null && dto.getEsSeleccionable()) {
                ArrayAdapter<CobranzaCampoDescripcionBean> adapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, dto.getListaDescripcion());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                pagoViewHolder.seleccionableCampoServicio.setAdapter(adapter);
                pagoViewHolder.seleccionableCampoServicio.setVisibility(View.VISIBLE);
                pagoViewHolder.seleccionableCampoServicio.setEnabled(dto.getPermiteIngresoPago());
                pagoViewHolder.seleccionableCampoServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        CobranzaCampoDescripcionBean selected = (CobranzaCampoDescripcionBean) pagoViewHolder.seleccionableCampoServicio.getSelectedItem();
                        dto.setValorIngreso(selected.getValor());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        dto.setValorIngreso(null);
                    }
                });
                pagoViewHolder.campoServicio.setVisibility(View.GONE);
            } else {
               // pagoViewHolder.campoServicio.setHint(dto.getDescripcion());
                InputFilter[] filter = new InputFilter[1];
                pagoViewHolder.campoServicio.setVisibility(View.VISIBLE);
                filter[0] = new InputFilter.LengthFilter(dto.getLongitud());
                pagoViewHolder.campoServicio.setFilters(filter);
                pagoViewHolder.campoServicio.setText(dto.getValorIngreso());
                pagoViewHolder.campoServicio.setEnabled(dto.getPermiteIngresoPago());
                if(!dto.getPermiteIngresoPago()){
                    pagoViewHolder.campoServicio.setFocusable(false);
                }
                pagoViewHolder.campoServicio.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s != null) {
                            dto.setValorIngreso(s.toString());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        }else{
            pagoViewHolder.parent.setVisibility(View.GONE);
            pagoViewHolder.descripcionCampo.setVisibility(View.GONE);
            pagoViewHolder.seleccionableCampoServicio.setVisibility(View.GONE);
            pagoViewHolder.campoServicio.setVisibility(View.GONE);
        }
            //pagoViewHolder.campoServicio.setInputType(InputType.);
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        PagoAdapter.mCallBack = mCallBack;
    }

    @Override
    public PagoViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_list_campos, parent, false);
        return new PagoViewHolder(cardView, parent, cobranzaCamposBeanShow);
    }

    public static class PagoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected EditText campoServicio;
        protected Spinner seleccionableCampoServicio;
        protected TextView descripcionCampo;
        protected View parent;


        //private ViewGroup parent;
        protected List<CobranzaCampoBean> items;

        public PagoViewHolder(View itemView, ViewGroup parent, List<CobranzaCampoBean> list) {
            super(itemView);
            campoServicio = (EditText) itemView.findViewById(R.id.edit_campo_servicio);
            seleccionableCampoServicio = (Spinner) itemView.findViewById(R.id.select_campo_servicio);
            descripcionCampo = (TextView) itemView.findViewById(R.id.text_view_descripcion_campo);
            items=list;
            this.parent = itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mCallBack != null) {
                mCallBack.onClick(v, items.get(super.getAdapterPosition()));
            }
        }
    }

    public List<CobranzaCampoBean> getCobranzaCampoBeans() {
        return cobranzaCampoBeans;
    }

    public void setCobranzaCampoBeans(List<CobranzaCampoBean> cobranzaCampoBeans) {
        this.cobranzaCampoBeans = cobranzaCampoBeans;
    }
}
