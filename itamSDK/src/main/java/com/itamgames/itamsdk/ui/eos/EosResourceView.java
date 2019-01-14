package com.itamgames.itamsdk.ui.eos;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseView;

public class EosResourceView extends BaseView {

    RelativeLayout cpu_ll = null;
    RelativeLayout ram_ll = null;

    TextView account_name = null;
    TextView eoscount_text = null;

    TextView eosstake_text = null;
    TextView eosunstake_text = null;
    TextView eosrefund_text = null;

    Button copy_btn = null;

    /*EOS Cpu UI*/
    TextView cpu_persent_text = null;
    TextView cpu_ms_count_text = null;
    TextView cpu_sec_count_text = null;

    TextView band_persent_text = null;
    TextView band_ms_count_text = null;
    TextView band_sec_count_text = null;

    ProgressBar band_use_progress = null;
    ProgressBar cpu_use_progress = null;

    Button cpu_stake_btn = null;
    Button cpu_unstake_btn = null;

    /*EOS Ram UI*/
    TextView ram_persent_text = null;
    TextView ram_ms_count_text = null;
    TextView ram_sec_count_text = null;

    ProgressBar ram_use_progress = null;

    Button ram_buy_btn = null;
    Button ram_sell_btn = null;

    int orientation = 0;

    public EosResourceView(Context context, int orientation ) {
        super(context, R.layout.eos_resource_layout );

        this.orientation = orientation;

        cpu_ll = (RelativeLayout)main_view.findViewById( R.id.EOS_CPU_LL );
        ram_ll = (RelativeLayout)main_view.findViewById( R.id.EOS_RAM_LL );

        /*TODO 세로 모드 임시 작업 */
        if (orientation == Configuration.ORIENTATION_LANDSCAPE ) {

            account_name = (TextView)main_view.findViewById( R.id.EOS_MAIN_NAME_TEXT );
            eoscount_text = (TextView)main_view.findViewById( R.id.TRADE_SHOP_ITAM_COUNT_TEXT );

            eosstake_text = (TextView)main_view.findViewById( R.id.EOS_RESOURCE_STAKE_TEXT );
            eosunstake_text = (TextView)main_view.findViewById( R.id.EOS_RESOURCE_UNSTAKE_TEXT );
            eosrefund_text = (TextView)main_view.findViewById( R.id.EOS_RESOURCE_REFUND_TEXT );

            copy_btn = (Button)main_view.findViewById( R.id.EOS_STAKE_COPY_BTN );

            copy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager cm = (ClipboardManager) con.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setPrimaryClip(ClipData.newPlainText("text", account_name.getText().toString() ) );
                    Toast.makeText(con, "copy account",  Toast.LENGTH_SHORT).show();
                }
            });
        }

        /*EOS Cpu UI*/
        cpu_persent_text = (TextView)main_view.findViewById( R.id.EOS_CPU_USE_PERSENT_TEXT );
        cpu_ms_count_text = (TextView)main_view.findViewById( R.id.EOS_CPU_USE_MS_COUNT_TEXT );
        cpu_sec_count_text = (TextView)main_view.findViewById( R.id.EOS_CPU_USE_SEC_COUNT_TEXT );

        band_persent_text = (TextView)main_view.findViewById( R.id.EOS_BANDWIDTH_USE_PERSENT_TEXT );
        band_ms_count_text = (TextView)main_view.findViewById( R.id.EOS_BANDWIDTH_USE_KB_COUNT_TEXT );
        band_sec_count_text = (TextView)main_view.findViewById( R.id.EOS_BANDWIDTH_USE_EOS_COUNT_TEXT );

        cpu_use_progress = (ProgressBar)main_view.findViewById( R.id.EOS_CPU_USE_BAR_IMG );
        band_use_progress = (ProgressBar)main_view.findViewById( R.id.EOS_BANDWIDTH_USE_BAR_IMG );

        cpu_stake_btn = (Button) main_view.findViewById( R.id.EOS_CPU_STAKE_BTN );
        cpu_unstake_btn = (Button)main_view.findViewById( R.id.EOS_CPU_UNSTAKE_BTN );

        /*EOS RAM UI*/
        ram_persent_text = (TextView)main_view.findViewById( R.id.EOS_RAM_USE_PERSENT_TEXT );
        ram_ms_count_text = (TextView)main_view.findViewById( R.id.EOS_RAM_USE_MS_COUNT_TEXT );
        ram_sec_count_text = (TextView)main_view.findViewById( R.id.EOS_RAM_USE_SEC_COUNT_TEXT );

        ram_use_progress = (ProgressBar)main_view.findViewById( R.id.EOS_RAM_USE_BAR_IMG );

        ram_buy_btn = (Button) main_view.findViewById( R.id.EOS_RAM_BUY_BTN );
        ram_sell_btn = (Button)main_view.findViewById( R.id.EOS_RAM_SELL_BTN );
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        super.setOnClickListener(l);

        cpu_stake_btn.setOnClickListener( l );
        cpu_unstake_btn.setOnClickListener( l );
        ram_buy_btn.setOnClickListener( l );
        ram_sell_btn.setOnClickListener( l );
    }

    public void SetAccountName(String _name ){
        account_name.setText( _name );
    }

    public void SetEosCountText( String _count ){
        eoscount_text.setText( _count );
    }

    public void SetEosStake( String _count ) {
        eosstake_text.setText( _count);
    }

    public void SetEosUnStake( String _count ) {
        eosunstake_text.setText( _count);
    }

    public void SetEosRefund( String _count ) {
        eosrefund_text.setText( _count);
    }

    public void ShowCpuLayout(){
        cpu_ll.setVisibility( View.VISIBLE );
        ram_ll.setVisibility( View.GONE );
    }

    public void ShowRamLayout(){
        cpu_ll.setVisibility( View.GONE );
        ram_ll.setVisibility( View.VISIBLE );
    }

    /*CPU UI Setting*/
    public void SetEosCpuPersent( String _count ) {
        cpu_persent_text.setText( _count);
    }

    public void SetEosCpuMs( String _count ) {
        cpu_ms_count_text.setText( _count);
    }

    public void SetEosCpuSec( String _count ) {
        cpu_sec_count_text.setText( _count);
    }

    public void SetBandPersent( String _count ) {
        band_persent_text.setText( _count);
    }

    public void SetBandMs( String _count ) {
        band_ms_count_text.setText( _count);
    }

    public void SetBandSec( String _count ) {
        band_sec_count_text.setText( _count);
    }

    public void SetCpuProgress( int _max ){
        cpu_use_progress.setMax( _max );
    }

    public ProgressBar getCpuUsebar() {
        return cpu_use_progress;
    }

    public void SetBandProgress( int _max ){
        band_use_progress.setMax( _max );
    }

    public ProgressBar getBandbar() {
        return band_use_progress;
    }

    /*Ram Ui Setting*/
    public void SetEosRamPersent( String _count ) {
        ram_persent_text.setText( _count);
    }

    public void SetEosRamMs( String _count ) {
        ram_ms_count_text.setText( _count);
    }

    public void SetEosRamSec( String _count ) {
        ram_sec_count_text.setText( _count);
    }

    public void SetRamProgress( int _max ){
        ram_use_progress.setMax( _max );
    }

    public ProgressBar getRamUsebar() {
        return ram_use_progress;
    }
}
