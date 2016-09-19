package com.umes.jeb.hww.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.umes.jeb.hww.R;
import com.umes.jeb.hww.view.adapter.ListItemsAdapter;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ItemBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.util.ArrayList;
import java.util.List;

public class ListModulesFragment extends Fragment {

    private ListView mListView;
    private List<ItemBean> itemBeans;
    private ListItemsAdapter mItemsAdapter;
    private Integer medioDePagoSeleccionado;
    private String nameActivity;
    private String cajero;
    private boolean clave;
    private String monto;

    private ServicioBean servicioBean;
    private ArrayList<CobranzaCampoBean> cobranzaCampoBean;

    public ListModulesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mListView = (ListView) inflater.inflate(R.layout.fragment_list_view, container, false);
        this.mItemsAdapter = new ListItemsAdapter(getActivity(), this.itemBeans,medioDePagoSeleccionado, nameActivity, cajero, clave, monto,cobranzaCampoBean,servicioBean);
        this.mListView.setAdapter(this.mItemsAdapter);
        return this.mListView;
    }

    public List<ItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<ItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }

    public Integer getMedioDePagoSeleccionado() {
        return medioDePagoSeleccionado;
    }

    public void setMedioDePagoSeleccionado(Integer medioDePagoSeleccionado) {
        this.medioDePagoSeleccionado = medioDePagoSeleccionado;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }
    public String getCajero () {
        return cajero;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public String getNameActivity () {
        return nameActivity;
    }

    public void setClave(boolean clave) {
        this.clave = clave;
    }

    public boolean getClave() {
        return clave;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public ArrayList<CobranzaCampoBean> getCobranzaCampoBean() {
        return cobranzaCampoBean;
    }

    public void setCobranzaCampoBean(ArrayList<CobranzaCampoBean> cobranzaCampoBean) {
        this.cobranzaCampoBean = cobranzaCampoBean;
    }

    public ServicioBean getServicioBean() {
        return servicioBean;
    }

    public void setServicioBean(ServicioBean servicioBean) {
        this.servicioBean = servicioBean;
    }
}
