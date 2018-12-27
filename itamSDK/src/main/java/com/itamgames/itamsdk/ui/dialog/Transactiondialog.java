package com.itamgames.itamsdk.ui.dialog;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itamgames.itamsdk.R;

public class Transactiondialog extends Dialog {

    Context con = null;

    String transid = "";

    TextView transid_text = null;
    TextView trans_copy_text = null;

    Button close_btn = null;

    public Transactiondialog(@NonNull Context context) {
        super(context);

        this.con = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;

        getWindow().setAttributes(lpWindow);

        setContentView( R.layout.transactiondialog );

        transid_text = (TextView)findViewById( R.id.TRANSACTION_DLG_TEXT );
        trans_copy_text = (TextView)findViewById( R.id.TRANSACTION_DLG_COPY_TEXT );

        transid_text.setText( transid );

        close_btn = (Button)findViewById( R.id.TRANSACTIONID_DLG_CLOSE_BTN ) ;

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        trans_copy_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager cm = (ClipboardManager) con.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text", transid_text.getText().toString() ) );
                Toast.makeText(con, R.string.main_idcopy_text,  Toast.LENGTH_SHORT).show();
            }
        });

    }


    public Transactiondialog(@NonNull Context context, String _tid ) {
        super(context);

        this.transid = _tid;


    }

}
