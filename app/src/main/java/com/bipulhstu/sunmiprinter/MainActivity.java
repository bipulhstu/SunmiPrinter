package com.bipulhstu.sunmiprinter;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et_msg;
    Button btn_print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_msg=findViewById(R.id.et_msg);
        btn_print=findViewById(R.id.btn_print);

        btn_print.setOnClickListener(view -> printIt(et_msg.getText().toString()));
    }

    private void printIt(String thisData) {
        BluetoothSocket socket = null;
        byte [] data = thisData.getBytes();

        //Get BluetoothAdapter
        BluetoothAdapter btAdapter = BluetoothUtil.getBTAdapter();
        if(btAdapter == null) {
            Toast.makeText(getBaseContext(), "Open Bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }
        // Get sunmi InnerPrinter BluetoothDevice
        BluetoothDevice device = BluetoothUtil.getDevice(btAdapter);
        if(device == null) {
            Toast.makeText(getBaseContext(), "Make Sure Bluetooth have InnterPrinter", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            socket = BluetoothUtil.getSocket(device);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert socket != null;
            BluetoothUtil.sendData(data, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}