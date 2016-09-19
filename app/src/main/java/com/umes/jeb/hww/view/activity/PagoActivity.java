package com.umes.jeb.hww.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.bs.service.InformacionCampoCobranzaTask;
import com.umes.jeb.hww.eis.dto.AccionCampo;
import com.umes.jeb.hww.eis.dto.CobranzaCampoDTO;
import com.umes.jeb.hww.eis.dto.InfoServicioCobranzaResponseDTO;
import com.umes.jeb.hww.eis.dto.TipoDato;
import com.umes.jeb.hww.eis.dto.TipoPago;
import com.umes.jeb.hww.util.validator.ValidatorRegex;
import com.umes.jeb.hww.view.adapter.PagoAdapter;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by DISEÑO on 03-09-15.
 */
public class PagoActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
    private PagoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawer;
    private int originalOrientation;

    private Toolbar mToolbar;

    private String[] osArray = null;

    private String mActivityTitle;

    private ServicioBean servicioBean;

    private CobranzaBean cobranzaBean;

    private String activityName;

    private Boolean showErrors = false;

    private ArrayList<CobranzaCampoBean> beans;

    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PagoActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_campo_servicio);

        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Pago");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PagoActivity.this.finish();
                }
            });
        }


        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().getExtras() != null) {
            activityName = (String) getIntent().getExtras().getString("activity");
            if (activityName != null && activityName.equalsIgnoreCase("cobranzaActivity")) {
                cobranzaBean = (CobranzaBean) getIntent().getExtras().getSerializable("cobranzaBean");
                servicioBean = cobranzaBean.getServicios().get(0);
            } else if (activityName != null && activityName.equalsIgnoreCase("servicioActivity")) {
                cobranzaBean = (CobranzaBean) getIntent().getExtras().getSerializable("cobranzaBean");
                servicioBean = (ServicioBean) getIntent().getExtras().getSerializable("servicioBean");
            } else if (activityName != null && activityName.equalsIgnoreCase("consultaActivity")) {
                cobranzaBean = (CobranzaBean) getIntent().getExtras().getSerializable("cobranzaBean");
                servicioBean = (ServicioBean) getIntent().getExtras().getSerializable("servicioBean");
                beans = (ArrayList<CobranzaCampoBean>)  getIntent().getExtras().getSerializable("listBeans");;
                afterOnCreate(beans);
            }

            if (activityName != null && activityName.equalsIgnoreCase("cobranzaActivity") || activityName != null && activityName.equalsIgnoreCase("servicioActivity")) {
                new InformacionCampoCobranzaTask(this, getSession().getToken(), getSession().getUser(), getSession().getTokenType()).execute(servicioBean.getCodigoCobranza().toString(),
                        servicioBean.getId().toString(), servicioBean.getCodigo().toString(), servicioBean.getVersionCampos().toString());
            }
        }

        Button boton = (Button) this.findViewById(R.id.button_action_service);
        boton.setText("hacer pago");
    }


    @Override
    public void handleException(Exception e) {
        show(e.getMessage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.colectores_menu, menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem cobranzaIcono = menu.findItem(R.id.iconos_cobranzas);
        if(cobranzaIcono!=null){
            loadBitmapMenu(cobranzaBean.getLogo(), cobranzaIcono);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void ejecutaAccionOnClickActionService(View v) {

        List<CobranzaCampoBean> campos = beans;
        for (CobranzaCampoBean bean : campos) {
            if(bean.getPermiteIngresoPago() && bean.getIngresoObligatorioPago()){
                String result = ValidatorRegex.validateField(bean);
                if(result!=null){
                    show(result);
                    return;
                }
            }
        }
        valueChangeCobranzaCampoDecimal();
        verificaTipoPago(cobranzaBean.getTipoPago(), beans);

        if (this.servicioBean.getUsaConsulta() && this.cobranzaBean.getTipoPago() != TipoPago.STO) {
            if (getMontoSinImpuestos().compareTo(BigDecimal.ZERO) == 0 && getImpuestos().compareTo(BigDecimal.ZERO) == 0) {
                setMontoSinImpuestos(valorPago);
            } else if (getMontoSinImpuestos().compareTo(BigDecimal.ZERO) == 0 && getImpuestos().compareTo(BigDecimal.ZERO) != 0) {
                setMontoSinImpuestos(valorPago.subtract(getImpuestos()));
            }
        } else {
            if (getMontoSinImpuestos().compareTo(BigDecimal.ZERO) == 0 && getImpuestos().compareTo(BigDecimal.ZERO) == 0) {
                setMontoSinImpuestos(getMontoTotal());
            } else if (getMontoSinImpuestos().compareTo(BigDecimal.ZERO) == 0 && getImpuestos().compareTo(BigDecimal.ZERO) != 0) {
                setMontoSinImpuestos(getMontoTotal().subtract(getImpuestos()));
            }
        }
        Intent pago = new Intent(this, MedioPagoActivity.class);
        pago.putExtra("listaCobranzaCampoBean",beans);
        pago.putExtra("servicioBean",servicioBean);
        pago.putExtra("total", getMontoTotal());
        pago.putExtra("totalSinImpuestos", getMontoSinImpuestos());
        pago.putExtra("impuestos", getImpuestos());
        this.startActivity(pago);
        //this.finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = null;
            if (activityName != null && activityName.equalsIgnoreCase("cobranzaActivity")) {
                intent = new Intent(PagoActivity.this, CobranzaActivity.class);
            } else if (activityName != null && activityName.equalsIgnoreCase("servicioActivity")) {
                intent = new Intent(PagoActivity.this, ServicioActivity.class);
            }
            // CobranzaActivity.this.startActivity(intent);
            PagoActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPostExecuteTask(Object response) {
        super.onPostExecuteTask(response);
        InfoServicioCobranzaResponseDTO respo = (InfoServicioCobranzaResponseDTO) response;
        beans = getCobranzaCampoBean(respo);
        afterOnCreate(beans);
    }

    private void afterOnCreate(List<CobranzaCampoBean> beans) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_campo_servicio);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new PagoAdapter(beans, this);
        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<CobranzaCampoBean> getCobranzaCampoBean(InfoServicioCobranzaResponseDTO response) {
        ArrayList<CobranzaCampoBean> lista = new ArrayList<CobranzaCampoBean>();
        if (response != null) {
            if (response.getListaCampos() != null && response.getListaCampos().size() > 0) {
                for (CobranzaCampoDTO campo : response.getListaCampos()) {
                    lista.add(new CobranzaCampoBean(campo));
                }
            }
        }
        return lista;
    }

    /**
     * Verificar pago que esten bien las sumas
     */

    BigDecimal sumador = BigDecimal.ZERO;
    BigDecimal valorPago = BigDecimal.ZERO;

    public void verificaTipoPago(TipoPago pago, List<CobranzaCampoBean> listaCampos) {
        int numero = 0;
        sumador = BigDecimal.ZERO;
        setMontoTotal(BigDecimal.ZERO);
        valorPago = BigDecimal.ZERO;
        super.setImpuestos(BigDecimal.ZERO);
        setMontoSinImpuestos(BigDecimal.ZERO);
        Boolean pagoMinimo = pago == TipoPago.PMI ? true : false;
        for (CobranzaCampoBean campo : listaCampos) {
            numero++;
            switch (numero) {
                case 1:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 2:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 3:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 4:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 5:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 6:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 7:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 8:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 9:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
                case 10:
                    sumaRestaMontos(campo, campo.getValorIngreso(), pagoMinimo);
                    break;
            }
            //if (showErrorMessage) { // Si ocurre un error en el metodo
            // sumaRestaMontos
            //return;
            //}
        }
        if (getMontoTotal() == null || valorPago == null || (getMontoTotal().compareTo(BigDecimal.ZERO) <= 0 && valorPago.compareTo(BigDecimal.ZERO) <= 0)
                || (getMontoTotal().compareTo(BigDecimal.ZERO) >= 0 && valorPago.compareTo(BigDecimal.ZERO) < 0)) {
            //this.showCodeAndMessageError(MensajeErrorUtil.MONTO_A_PAGAR_DEBE_SER_MAYOR_A_CERO, this.getServiceMessage(MensajeErrorUtil.MONTO_A_PAGAR_DEBE_SER_MAYOR_A_CERO), false);
            return;
        }

        if (TipoPago.STO == pago) {
            if (sumador.compareTo(getMontoTotal()) == 0 || (sumador.compareTo(BigDecimal.ZERO) == 0 && getMontoTotal().compareTo(BigDecimal.ZERO) != 0)) {
                return;
            } else if (sumador.compareTo(getMontoTotal()) != 0) {
                //this.showCodeAndMessageError(MensajeErrorUtil.LA_SUMA_DE_CAMPOS_NO_COINCIDE_CON_TOTAL, this.getServiceMessage(MensajeErrorUtil.LA_SUMA_DE_CAMPOS_NO_COINCIDE_CON_TOTAL), false);
                return;
            }
            //this.showCodeAndMessageError(MensajeErrorUtil.MONTO_A_PAGAR_DEBE_SER_IGUAL_AL_TOTAL, this.getServiceMessage(MensajeErrorUtil.MONTO_A_PAGAR_DEBE_SER_IGUAL_AL_TOTAL), false);
            //this.
            // = true;
            return;

        } else if (TipoPago.IMA == pago) {
            if (servicioBean.getUsaConsulta()) {
                if ((sumador.compareTo(valorPago) == 0 && valorPago.compareTo(getMontoTotal()) >= 0) || (sumador.compareTo(BigDecimal.ZERO) == 0 && valorPago.compareTo(getMontoTotal()) >= 0)) {
                    return;
                } else if (valorPago.compareTo(getMontoTotal()) < 0) {
                    //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_O_MAYOR_AL_TOTAL, this.getServiceMessage(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_O_MAYOR_AL_TOTAL), false);
                    return;
                } else if (sumador.compareTo(valorPago) != 0) {
                    //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_EN_SUMA, this.getServiceMessage(MensajeErrorUtil.ERROR_EN_SUMA), false);
                    return;
                }
                //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_O_MAYOR_AL_TOTAL, this.getServiceMessage(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_O_MAYOR_AL_TOTAL), false);
                return;
            } else {
                if (sumador.compareTo(getMontoTotal()) == 0 || (sumador.compareTo(BigDecimal.ZERO) == 0 && getMontoTotal().compareTo(BigDecimal.ZERO) != 0)) {
                    return;
                }
                //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_EN_SUMA, this.getServiceMessage(MensajeErrorUtil.ERROR_EN_SUMA), false);
                //this.showErrorMessage = true;
                return;
            }
        } else if (TipoPago.IME == pago) {
            if (servicioBean.getUsaConsulta()) {
                if ((sumador.compareTo(valorPago) == 0 && valorPago.compareTo(getMontoTotal()) <= 0) || (sumador.compareTo(BigDecimal.ZERO) == 0 && valorPago.compareTo(getMontoTotal()) <= 0)) {
                    return;
                } else if (valorPago.compareTo(getMontoTotal()) > 0) {
                    //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_O_MENOR_AL_TOTAL, this.getServiceMessage(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_O_MENOR_AL_TOTAL), false);
                    return;

                } else if (sumador.compareTo(valorPago) != 0) {
                    //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_EN_SUMA, this.getServiceMessage(MensajeErrorUtil.ERROR_EN_SUMA), false);
                    return;
                }
                //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_O_MENOR_AL_TOTAL, this.getServiceMessage(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_O_MENOR_AL_TOTAL), false);
                return;
            } else {
                if (sumador.compareTo(getMontoTotal()) == 0 || (sumador.compareTo(BigDecimal.ZERO) == 0 && getMontoTotal().compareTo(BigDecimal.ZERO) != 0)) {
                    return;
                }
                //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_EN_SUMA, this.getServiceMessage(MensajeErrorUtil.ERROR_EN_SUMA), false);
                return;
            }
        } else if (TipoPago.PMI == pago) {
            if (servicioBean.getUsaConsulta()) {
                if (sumador.compareTo(valorPago) <= 0 || getMontoTotal().compareTo(valorPago) == 0) {
                    return;
                } else if (sumador.compareTo(valorPago) > 0) {
                    //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_AL_PAGO_MINIMO, this.getServiceMessage(MensajeErrorUtil.ERROR_MONTO_DEBE_SER_IGUAL_AL_PAGO_MINIMO), false);
                    return;
                }
                //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_EN_SUMA, this.getServiceMessage(MensajeErrorUtil.ERROR_EN_SUMA), false);
                return;
            } else {
                if (sumador.compareTo(getMontoTotal()) == 0 || (sumador.compareTo(BigDecimal.ZERO) == 0 && getMontoTotal().compareTo(BigDecimal.ZERO) != 0)) {
                    return;
                }
                //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_EN_SUMA, this.getServiceMessage(MensajeErrorUtil.ERROR_EN_SUMA), false);
                return;
            }

        } else if (TipoPago.CVA == pago) {
            if (servicioBean.getUsaConsulta()) {
                if ((getMontoTotal().compareTo(BigDecimal.ZERO) != 0 && valorPago.compareTo(BigDecimal.ZERO) != 0) || (sumador.compareTo(BigDecimal.ZERO) >= 0 && valorPago.compareTo(BigDecimal.ZERO) != 0)) {
                    return;
                }
                //this.showCodeAndMessageError(MensajeErrorUtil.MONTO_A_PAGAR_DEBE_SER_MAYOR_A_CERO, this.getServiceMessage(MensajeErrorUtil.MONTO_A_PAGAR_DEBE_SER_MAYOR_A_CERO), false);
                return;
            } else {
                if (sumador.compareTo(getMontoTotal()) == 0 || (sumador.compareTo(BigDecimal.ZERO) == 0 && getMontoTotal().compareTo(BigDecimal.ZERO) != 0)) {
                    return;
                }
                // this.showCodeAndMessageError(MensajeErrorUtil.ERROR_EN_SUMA, this.getServiceMessage(MensajeErrorUtil.ERROR_EN_SUMA), false);
                this.showErrors = true;
                return;
            }
        }
    }

    public void sumaRestaMontos(CobranzaCampoBean campo, String value, Boolean pagoMinimo) {
        if (value == null || value.trim().equals("") || campo.getTipoDato() != TipoDato.D) {
            return;
        }

        try {
            //value = removeMoneyMask(value);
            BigDecimal valor = new BigDecimal(value);

            if (campo.getEsTotal()) {
                setMontoTotal(valor);
                return;
            }
            if (campo.getEsCampoPagoTotal()) {
                valorPago = valor;
                return;
            }
            try {
                if (pagoMinimo) {
                    if (campo.getAccionPagoMinimo() == AccionCampo.S) {
                        sumador = sumador.add(valor);
                        if (campo.getEsImpuesto()) {
                            setImpuestos(getImpuestos().add(valor));
                        } else {
                            setMontoSinImpuestos(getMontoSinImpuestos().add(valor));
                        }
                    } else if (campo.getAccionPagoMinimo() == AccionCampo.R) {
                        sumador = sumador.subtract(valor);
                        if (campo.getEsImpuesto()) {
                            setImpuestos(getImpuestos().subtract(valor));
                        } else {
                            setMontoSinImpuestos(getMontoSinImpuestos().subtract(valor));
                        }
                    }
                    return;
                }
                if (campo.getAccionTotal() == AccionCampo.S) {
                    sumador = sumador.add(valor);
                    if (campo.getEsImpuesto()) {
                        setImpuestos(getImpuestos().add(valor));
                    } else {
                        setMontoSinImpuestos(getMontoSinImpuestos().add(valor));
                    }
                } else if (campo.getAccionTotal() == AccionCampo.R) {
                    sumador = sumador.subtract(valor);
                    if (campo.getEsImpuesto()) {
                        setImpuestos(getImpuestos().subtract(valor));
                    } else {
                        setMontoSinImpuestos(getMontoSinImpuestos().subtract(valor));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_FORMATO_EN_NUMERICO_INCORRECTO, this.getServiceMessage(MensajeErrorUtil.ERROR_FORMATO_EN_NUMERICO_INCORRECTO), false);
            return;
        }

        if (campo.getEsCampoPagoTotal() || campo.getPosicionEnviaPago() == null || campo.getPosicionEnviaPago() == 0) {
            //continue;
        }
        if (!campo.getVisiblePago() && !campo.getEsTotal()) {
            //continue;
        }
        if (campo.getTipoDato() == TipoDato.D || campo.getTipoDato() == TipoDato.N) {
            campo.setValorIngreso(campo.getValorIngreso());
        }
        if (campo.getEsIdPago() != null && campo.getEsIdPago()) {
            //idConsulta = campo.getValorIngreso();
        }
        if (campo.getEsTotal()) {
            //value =campo.getValorIngreso();
            campo.setValorIngreso(getMontoTotal().toString());
        }
        if (campo.getTipoDato() == TipoDato.F && campo.getValorIngreso() != null && !campo.getValorIngreso().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat(campo.getFormatoFechaConSeparador());
            try {
                campo.setValorIngreso(formato.format(formato.parse(campo.getValorIngreso())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void valueChangeCobranzaCampoDecimal() {
        CobranzaCampoBean impuesto = new CobranzaCampoBean();
        BigDecimal totalPago = BigDecimal.ZERO;
        boolean aplicoImpuesto = false;
        try {
            for (int i = 0; i < beans.size(); i++) {
                CobranzaCampoBean bean = beans.get(i);
                if (bean.getEsImpuesto()) {
                    for (CobranzaCampoBean campo : beans) {
                        if (campo.getId().equals(bean.getCampoAfectoImpuesto())) {
                            BigDecimal valorImpuesto = new BigDecimal(bean.getPorcentajeImpuesto());
                            valorImpuesto = valorImpuesto.divide(new BigDecimal("100"));
                            BigDecimal valorAplicarImpuesto = new BigDecimal(campo.getValorIngreso());
                            impuesto = bean;
                            BigDecimal valorImpuestoTotal = valorImpuesto.multiply(valorAplicarImpuesto);
                            valorImpuestoTotal = valorImpuestoTotal.setScale(2, RoundingMode.DOWN);
                            totalPago = valorImpuestoTotal.add(valorAplicarImpuesto);
                            impuesto.setValorIngreso(valorImpuestoTotal.toString());
                            aplicoImpuesto = true;
                            break;
                        }
                    }
                    beans.set(i, impuesto);
                } else if (aplicoImpuesto && bean.getEsTotal()) {
                    bean.setValorIngreso(String.valueOf(totalPago));
                    beans.set(i, bean);
                }
            }
        } catch (NumberFormatException er) {
            //this.showCodeAndMessageError(MensajeErrorUtil.ERROR_FORMATO_EN_NUMERICO_INCORRECTO, this.getServiceMessage(MensajeErrorUtil.ERROR_FORMATO_EN_NUMERICO_INCORRECTO) + "--" + e.getNewValue().toString(), true);
        }
    }

}
