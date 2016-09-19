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

import java.util.List;

/**
 * Created by Ccalito on 06/04/2016.
 */
public class ResultadoAdapter extends RecyclerView.Adapter<ResultadoAdapter.ResultadoViewHolder> {

    protected List<CobranzaCampoBean> cobranzaCampoBeans;
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, CobranzaCampoBean dto);
    }

    public ResultadoAdapter() {
        super();
    }

    public CobranzaCampoBean getItem(int position) {
        return cobranzaCampoBeans.get(position);
    }

    public ResultadoAdapter(List<CobranzaCampoBean> cobranzaCampoBeans, AbstractActivity activity) {
        this.cobranzaCampoBeans = cobranzaCampoBeans;
        this.mContext = activity;
    }

    @Override
    public int getItemCount() {
        if (cobranzaCampoBeans == null)
            return 0;
        return this.cobranzaCampoBeans.size();
    }

    @Override
    public void onBindViewHolder(final ResultadoViewHolder resultadoViewHolder, int position) {
        final CobranzaCampoBean dto = cobranzaCampoBeans.get(position);
        if(dto.getVisibleResultado()) {
            resultadoViewHolder.descripcionCampo.setText(dto.getDescripcion());
            if (dto.getEsSeleccionable() != null && dto.getEsSeleccionable()) {
                ArrayAdapter<CobranzaCampoDescripcionBean> adapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, dto.getListaDescripcion());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                resultadoViewHolder.seleccionableCampoServicio.setAdapter(adapter);
                resultadoViewHolder.seleccionableCampoServicio.setVisibility(View.VISIBLE);
                resultadoViewHolder.seleccionableCampoServicio.setEnabled(dto.getPermiteIngresoPago());
                resultadoViewHolder.seleccionableCampoServicio.setFocusable(false);
                resultadoViewHolder.seleccionableCampoServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        CobranzaCampoDescripcionBean selected = (CobranzaCampoDescripcionBean) resultadoViewHolder.seleccionableCampoServicio.getSelectedItem();
                        dto.setValorIngreso(selected.getValor());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        dto.setValorIngreso(null);
                    }
                });
                resultadoViewHolder.campoServicio.setVisibility(View.INVISIBLE);
            } else {
               // resultadoViewHolder.campoServicio.setHint(dto.getDescripcion());
                InputFilter[] filter = new InputFilter[1];
                filter[0] = new InputFilter.LengthFilter(dto.getLongitud());
                resultadoViewHolder.campoServicio.setFilters(filter);
                resultadoViewHolder.campoServicio.setText(dto.getValorIngreso());
                resultadoViewHolder.campoServicio.setFocusable(false);
                resultadoViewHolder.campoServicio.setEnabled(dto.getPermiteIngresoPago());
            }
        }else{
            resultadoViewHolder.campoServicio.setVisibility(View.INVISIBLE);
        }
            //resultadoViewHolder.campoServicio.setInputType(InputType.);
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        ResultadoAdapter.mCallBack = mCallBack;
    }

    @Override
    public ResultadoViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_list_campos, parent, false);
        return new ResultadoViewHolder(cardView, parent, cobranzaCampoBeans);
    }

    public static class ResultadoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected EditText campoServicio;
        protected Spinner seleccionableCampoServicio;
        protected TextView descripcionCampo;


        //private ViewGroup parent;
        protected List<CobranzaCampoBean> items;

        public ResultadoViewHolder(View itemView, ViewGroup parent, List<CobranzaCampoBean> list) {
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

    public List<CobranzaCampoBean> getCobranzaCampoBeans() {
        return cobranzaCampoBeans;
    }

    public void setCobranzaCampoBeans(List<CobranzaCampoBean> cobranzaCampoBeans) {
        this.cobranzaCampoBeans = cobranzaCampoBeans;
    }
}
