package com.utupio.zongheng.activity;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by Administrator on 2015/10/10.
 */
public class CycleActivity extends Activity {
    private Button button;
    private NfcAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
