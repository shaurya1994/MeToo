package com.example.shauryatrivedi.metoo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shauryatrivedi.metoo.R;

public class MainActivity extends AppCompatActivity {

    Button BtnLaws,BtnMetoo,BtnMetooIndia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD



=======
>>>>>>> 4fc830c8d5b678c5d0bf022123fd27bfa4c02108
        BtnLaws=(Button)findViewById(R.id.btnLaws);
        BtnMetoo=(Button)findViewById(R.id.btnMeToo);
        BtnMetooIndia=(Button)findViewById(R.id.btnMeTooIndia);

        BtnLaws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,LawActivity.class));
            }
        });

        BtnMetoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Twitter.class);
                intent.putExtra("Type","MeToo");
                MainActivity.this.startActivity(intent);

            }
        });

        BtnMetooIndia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Twitter.class);
                intent.putExtra("Type","MeTooIndia");
                MainActivity.this.startActivity(intent);

            }
        });
    }
}
