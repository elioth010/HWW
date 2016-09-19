package com.umes.jeb.hww.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.umes.jeb.hww.R;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Created by DISEÑO on 30/09/2015.
 */
public class FechaVencimientoDialogFragment extends DialogFragment {

    private View mLayout;
    private ViewGroup mViewGroup;
    private LayoutInflater mInflater;

    public interface FechaVencimientoOnclickListener{
        public void onPositiveButtonClicked(int month, int year);
    }

    FechaVencimientoOnclickListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewGroup = container;
        if (getShowsDialog()) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        this.mInflater = inflater;
        return getLayout(inflater, container);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FechaVencimientoOnclickListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement FechaVencimientoOnclickListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private View getLayout(LayoutInflater inflater, ViewGroup container) {
        mLayout = inflater.inflate(R.layout.fragment_dialog_month_year_picker, container, false);
        return mLayout;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = getLayout(inflater, mViewGroup);
        final NumberPicker monthPicker = (NumberPicker) v.findViewById(R.id.month_picker);
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);

        final NumberPicker yearPicker = (NumberPicker) v.findViewById(R.id.year_picker);
        Calendar cal = GregorianCalendar.getInstance();
        yearPicker.setMinValue(cal.get(Calendar.YEAR));
        cal.add(Calendar.YEAR, 5);
        yearPicker.setMaxValue(cal.get(Calendar.YEAR));
        return new AlertDialog.Builder(getActivity())
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositiveButtonClicked(monthPicker.getValue(), yearPicker.getValue());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .setView(v)
                .create();
    }
}