package com.umes.jeb.hww.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.dto.TipoDato;
import com.umes.jeb.hww.eis.dto.TipoPago;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.ConsultaActivity;
import com.umes.jeb.hww.view.activity.PagoActivity;
import com.umes.jeb.hww.view.activity.ServicioActivity;
import com.umes.jeb.hww.view.adapter.CobranzaAdapter;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.HomeBean;

import java.util.List;

/**
 * Created by elioth010 on 4/19/16.
 */
public class RecientesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CobranzaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeBean homeBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<CobranzaBean> beans = homeBean.getListaCobranzas();
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recyclerview, container, false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new CobranzaAdapter(beans, (AbstractActivity)getActivity());
        mAdapter.setCallBack(new CobranzaAdapter.CallBack() {
            @Override
            public void onClick(View v, CobranzaBean dto) {
                if(dto!= null && dto.getServicios()!= null){
                    Intent intent =null;
                    if(dto.getServicios().size()>1){
                        intent = new Intent(getActivity(), ServicioActivity.class);
                    }else{
                        if(dto.getServicios().get(0).getUsaConsulta()) {
                            CobranzaCampoBean campoTotal = getCobranzaTotal(dto);
                            intent = new Intent(getActivity(), ConsultaActivity.class);
                            intent.putExtra("activity", "cobranzaActivity");
                            intent.putExtra("campoTotal", campoTotal);
                        }else{
                            intent = new Intent(getActivity(), PagoActivity.class);
                            intent.putExtra("activity", "cobranzaActivity");
                        }
                    }
                    intent.putExtra("cobranzaBean", dto);
                    startActivity(intent);
                }
            }
        });
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public CobranzaAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(CobranzaAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public RecyclerView.LayoutManager getmLayoutManager() {
        return mLayoutManager;
    }

    public void setmLayoutManager(RecyclerView.LayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    protected CobranzaCampoBean getCobranzaTotal(CobranzaBean cobranzaSeleccionada) {
        CobranzaCampoBean bean = new CobranzaCampoBean();
        if (cobranzaSeleccionada.getTipoPago() != TipoPago.STO) {
            bean.setVisiblePago(true);
            bean.setPermiteIngresoPago(true);
            bean.setIngresoObligatorioPago(true);
            bean.setVisibleConsulta(false);
            bean.setDescripcion(getString(R.string.app_textView_pago_valor_a_pagar));
            bean.setTipoDato(TipoDato.D);
            bean.setLongitud(15);
            bean.setCantidadDecimales(2);
            bean.setVisibleResultado(true);
            bean.setEsTotal(false);
            bean.setEsIdPago(false);
            bean.setEsFavorito(false);
            bean.setEsImpuesto(false);
            bean.setEsCampoPagoTotal(true);
            return bean;
        }
        return null;
    }
}
