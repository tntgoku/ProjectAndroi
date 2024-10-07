package com.example.myapplication.ActivityiTem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Controller.eventSystem;
import com.example.myapplication.R;

public class MainActivity2 extends AppCompatActivity implements eventSystem {
    ImageView imgback,img1,img2,img3,img4 ;
    TableRow tbr,tbr1,tbr2,tbr3,tbr4,tbr5;
    TextView t,t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle tbrvedInstanceState) {
        super.onCreate(tbrvedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        setID();
        eVentID();


    }

    @Override
    public void setID() {
        tbr  = findViewById(R.id.nametable);
        tbr1 = findViewById(R.id.nametable1);
        tbr2 = findViewById(R.id.nametable2);
        tbr3 = findViewById(R.id.nametable3);
        tbr4 = findViewById(R.id.nametable4);


        imgback=findViewById(R.id.imgback);
        img1=findViewById(R.id.imgsucces);
        t=findViewById(R.id.nameuser);
        t1=findViewById(R.id.dateuser);
        t2=findViewById(R.id.genderuser);
        t3=findViewById(R.id.phoneuser);
        t4=findViewById(R.id.emailuser);


    }


    @Override
    public void eVentID() {
        setTableRowClickListener(tbr);
        setTableRowClickListener(tbr1);
        setTableRowClickListener(tbr2);
        setTableRowClickListener(tbr3);
        setTableRowClickListener(tbr4);


        finishintentback();
        img1event();
    }


    private  void finishintentback(){
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }


    private  void img1event(){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
            }
        });
    }
    private void setTableRowClickListener(TableRow tableRow) {
        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableRow.setBackgroundResource(R.color.smoke);
                openHandle(300, tableRow);
            }
        });
    }
    private  void openHandle(int timedelay, TableRow tbrr){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // tbru 300ms, đổi tbrng màu trắng
                tbrr.setBackgroundResource(R.color.white); // Hoặc setBackgroundColor(Color.WHITE)
                int idtbr=tbrr.getId();
                if(tbr.getId()==idtbr){

                } else if (tbr1.getId() == idtbr) {
                    opendialogTime();
                }else{
                    // Hiển thị một AlertDialog tbru khi thay đổi màu nền
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Màu nền đã thay đổi!");

                    // Nút "OK" để đóng dialog
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // Đóng dialog
                        }
                    });

                    // Hiển thị dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        }, timedelay);
    }

    private  void opendialogTime(){
        DatePickerDialog sea=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                t1.setText(String.valueOf(i2)+"/"+String.valueOf(i1)+"/"+String.valueOf(i));
            }
        },2024,10,6);
        sea.show();
    }
}