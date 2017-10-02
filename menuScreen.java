package com.example.kishan.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class menuScreen extends AppCompatActivity {
    Button ravi_Button,hanafi_Button,gameStart;
    Character char1,char2;
    boolean isChar1=true;
    String choice1,choice2;

    public Character getChar1() {
        return char1;
    }

    public Character getChar2() {
        return char2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        setTitle("Character Selection");
        System.out.println("Test");
        ravi_Button= (Button) findViewById(R.id.ravi_Button);
        hanafi_Button= (Button) findViewById(R.id.hanafi_Button);
        gameStart = (Button) findViewById(R.id.gameStartButton);

        ravi_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isChar1){
                    choice1="Ravi";
                    isChar1=false;
                }else{
                    choice2="Ravi";
                }

            }
        });
        hanafi_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isChar1){
                    choice1="Hanafi";
                    isChar1=false;
                }else{
                    choice2 = "Hanafi";
                }

            }
        });
        gameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice1 != null && choice2 !=null){
                    Intent intent = new Intent(menuScreen.this, battleScreen.class);
                        intent.putExtra("char1", choice1);
                        intent.putExtra("char2",choice2);

                    startActivity(intent);
                    //startActivity(intent);

                }
            }
        });




    }
    public static byte[] serialize(Object obj) throws IOException {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }
}
