package com.umes.jeb.hww.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder> {

    protected ArrayList<CobranzaCampoBean> cobranzaCampoBeans;
    protected List<CobranzaCampoBean> cobranzaCamposBeanShow = new ArrayList<>();
    protected ArrayList<CobranzaCampoBean> cobranzaCampoAll;
    private AbstractActivity mContext;

    static CallBack mCallBack;

    public interface CallBack {
        void onClick(View v, CobranzaCampoBean dto);
    }

    public ConsultaAdapter() {
        super();
    }

    public CobranzaCampoBean getItem(int position) {
        return cobranzaCamposBeanShow.get(position);
    }

    public ConsultaAdapter(ArrayList<CobranzaCampoBean> cobranzaCampoBeans, AbstractActivity activity,ArrayList<CobranzaCampoBean> cobranzaCampoAll) {
        this.cobranzaCampoBeans = cobranzaCampoBeans;
        this.mContext = activity;
        this.cobranzaCampoAll=cobranzaCampoAll;
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
        return this.cobranzaCamposBeanShow.size();
    }

    @Override
    public void onBindViewHolder(final ConsultaViewHolder consultaViewHolder, int position) {
        final CobranzaCampoBean dto = cobranzaCamposBeanShow.get(position);
        if (dto.getVisibleConsulta()) {
            if(dto.getEsSeleccionable()){
                ArrayAdapter<CobranzaCampoDescripcionBean> adapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, dto.getListaDescripcion());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                consultaViewHolder.seleccionableCampoServicio.setAdapter(adapter);
                consultaViewHolder.seleccionableCampoServicio.setVisibility(View.VISIBLE);
                consultaViewHolder.seleccionableCampoServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        CobranzaCampoDescripcionBean selected = (CobranzaCampoDescripcionBean) consultaViewHolder.seleccionableCampoServicio.getSelectedItem();
                        dto.setValorIngreso(selected.getValor());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        dto.setValorIngreso(null);
                    }
                });
                consultaViewHolder.campoServicio.setVisibility(View.INVISIBLE);
            }else{
                consultaViewHolder.campoServicio.setHint(dto.getDescripcion());
                InputFilter[] filter = new InputFilter[1];
                filter[0] = new InputFilter.LengthFilter(dto.getLongitud());
                consultaViewHolder.campoServicio.setFilters(filter);
                consultaViewHolder.campoServicio.addTextChangedListener(new TextWatcher() {
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
                dto.setValorIngreso(consultaViewHolder.campoServicio.getText().toString());
            }
        }
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack mCallBack) {
        ConsultaAdapter.mCallBack = mCallBack;
    }

    @Override
    public ConsultaViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.fragment_list_campo_consulta, parent, false);
        return new ConsultaViewHolder(cardView, parent, cobranzaCampoBeans);
    }

    public static class ConsultaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected EditText campoServicio;
        protected Spinner seleccionableCampoServicio;

        //private ViewGroup parent;
        protected List<CobranzaCampoBean> items;

        public ConsultaViewHolder(View itemView, ViewGroup parent, List<CobranzaCampoBean> list) {
            super(itemView);
            campoServicio = (EditText) itemView.findViewById(R.id.edit_campo_servicio_consulta);
            seleccionableCampoServicio = (Spinner) itemView.findViewById(R.id.select_campo_servicio_consulta);
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

    public ArrayList<CobranzaCampoBean> getCobranzaCampoBeans() {
        return cobranzaCampoBeans;
    }

    public void setCobranzaCampoBeans(ArrayList<CobranzaCampoBean> cobranzaCampoBeans) {
        this.cobranzaCampoBeans = cobranzaCampoBeans;
    }

    public ArrayList<CobranzaCampoBean> getCobranzaCampoAll() {
        return cobranzaCampoAll;
    }

    public void setCobranzaCampoAll(ArrayList<CobranzaCampoBean> cobranzaCampoAll) {
        this.cobranzaCampoAll = cobranzaCampoAll;
    }
}
