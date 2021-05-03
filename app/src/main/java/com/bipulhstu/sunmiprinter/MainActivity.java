package com.bipulhstu.sunmiprinter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView et_msg;
    Button btn_print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_msg = findViewById(R.id.et_msg);
        btn_print = findViewById(R.id.btn_print);

        et_msg.setText("Hi, this is Bipul Islam\n\n\n");

        String BILL;

        BILL = "        INVOICE/MEMO\n";


        BILL = BILL +
                "   Rasad Filling Station\n" +
                "       +8801312515241\n" +
                "Purana Paltan Lane, Dhaka-10\n";

        BILL = BILL +
                "------------HOVATA-----------\n";
        BILL = BILL +
                "_____________________________\n";

        BILL = BILL +
                "Invoice Code: #64846464\n" +
                "Date        : 2020-09-05\n" +
                "Customer    : Kobir Hossain\n";
        BILL = BILL +
                "_____________________________\n";


        BILL = BILL +
                "Fuel/Nazzol : Diseal/Diseal 1\n" +
                "Sale LTR    : 25 LTR\n" +
                "Sale Amount : 1650 Taka\n" +
                "Commission  : 50 Taka\n" +
                "Paid        : 1600 Taka\n" +
                "Due         : 0 Taka\n\n\n";

        BILL = BILL +
                "      Shift : Night Shift\n" +
                "Seller Name : Aslam Kobir\n\n\n";



        /*BILL = BILL
                + "--------------------------------\n";


        BILL = BILL + String.format("%1$-10s %2$10s %3$13s %4$10s", "Item", "Qty", "Rate", "Totel");
        BILL = BILL + "\n";
        BILL = BILL
                + "-----------------------------------------------";
        BILL = BILL + "\n " + String.format("%1$-10s %2$10s %3$11s %4$10s", "item-001", "5", "10", "50.00");
        BILL = BILL + "\n " + String.format("%1$-10s %2$10s %3$11s %4$10s", "item-002", "10", "5", "50.00");
        BILL = BILL + "\n " + String.format("%1$-10s %2$10s %3$11s %4$10s", "item-003", "20", "10", "200.00");
        BILL = BILL + "\n " + String.format("%1$-10s %2$10s %3$11s %4$10s", "item-004", "50", "10", "500.00");

        BILL = BILL
                + "\n-----------------------------------------------";
        BILL = BILL + "\n\n ";

        BILL = BILL + "                   Total Qty:" + "      " + "85" + "\n";
        BILL = BILL + "                   Total Value:" + "     " + "700.00" + "\n";

        BILL = BILL
                + "-----------------------------------------------\n";
        BILL = BILL + "\n\n ";*/


        String finalBILL = BILL;
        String bill = et_msg.getText().toString();
        btn_print.setOnClickListener(view -> printIt(finalBILL));
    }

    private void printIt(String thisData) {

        BluetoothSocket socket = null;
        byte[] data = thisData.getBytes();

        //Get BluetoothAdapter
        BluetoothAdapter btAdapter = BluetoothUtil.getBTAdapter();
        if (btAdapter == null) {
            Toast.makeText(getBaseContext(), "Open Bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }
        // Get sunmi InnerPrinter BluetoothDevice
        BluetoothDevice device = BluetoothUtil.getDevice(btAdapter);
        if (device == null) {
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