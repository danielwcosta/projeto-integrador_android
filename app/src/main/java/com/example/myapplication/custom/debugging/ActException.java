package com.example.myapplication.custom.debugging;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

import base.ActBase;

import static debugging.ExceptionHandler.EXCEPTION_DEVICE;
import static debugging.ExceptionHandler.EXCEPTION_LOG;
import static debugging.ExceptionHandler.EXCEPTION_MESSAGE;

public class ActException extends ActBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_exception);
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            TextView mensagem = findViewById(R.id.exception_message);
            TextView log = findViewById(R.id.exception_log);
            TextView device = findViewById(R.id.exception_device);

            mensagem.setText(extras.getString(EXCEPTION_MESSAGE));
            log.setText(extras.getString(EXCEPTION_LOG));
            device.setText(extras.getString(EXCEPTION_DEVICE));
        }
    }
}
