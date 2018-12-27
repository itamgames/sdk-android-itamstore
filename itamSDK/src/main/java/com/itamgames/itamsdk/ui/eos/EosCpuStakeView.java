package com.itamgames.itamsdk.ui.eos;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseView;

public class EosCpuStakeView extends BaseView {


    EditText account_edit;
    EditText cpu_edit;
    EditText band_edit;

    RadioButton delegate_rb;
    RadioButton transfer_rb;

    Button stake_btn;

    public EosCpuStakeView(Context context ) {
        super(context, R.layout.eos_stake_input_layout );

        account_edit = (EditText)main_view.findViewById( R.id.EOS_STAKE_INPUT_RECV_ACCOUNT_EDIT );
        cpu_edit = (EditText)main_view.findViewById( R.id.EOS_STAKE_INPUT_CPU_EDIT );
        band_edit = (EditText)main_view.findViewById( R.id.EOS_STAKE_INPUT_BANDWIDTH_EDIT );

        delegate_rb = (RadioButton)main_view.findViewById( R.id.EOS_STAKE_DELEGATE_BTN );
        transfer_rb = (RadioButton)main_view.findViewById( R.id.EOS_STAKE_TRANSFER_BTN );

        stake_btn = (Button)main_view.findViewById( R.id.EOS_STAKE_OPT_ACCEPT_BTN );

    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        super.setOnClickListener(l);

        stake_btn.setOnClickListener( l );
    }

    public String GetStakeRecvierAccount(){

        return account_edit.getText().toString();
    }

    public String GetCpuCount(){

        return cpu_edit.getText().toString();
    }

    public String GetBandCount(){

        return band_edit.getText().toString();
    }

    public Boolean IsTransfer(){
        return transfer_rb.isChecked();
    }

    public void SetAccountEdit( String _tmp ){
        account_edit.setText( _tmp );
    }
}
