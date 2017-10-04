package com.example.kishan.homework2;
/**
 * @author: Kishan Patel
 * @date: 10/3/2017
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public static final String T_MenuScreen = "T_MenuScreen";

    public Character getChar1() {
        return char1;
    }

    public Character getChar2() {
        return char2;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(T_MenuScreen, "App is Created");                                                      //LogCat debug
        setContentView(R.layout.activity_menu_screen);
        setTitle(getString(R.string.menu_title));
        System.out.println("Test");
        ravi_Button= (Button) findViewById(R.id.ravi_Button);
        hanafi_Button= (Button) findViewById(R.id.hanafi_Button);
        gameStart = (Button) findViewById(R.id.gameStartButton);

        //Follow this button format to add more characters
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
        //Game start button logic (only activates when both characters are chosen, will start next activity
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
    //LogCat Debug Messages
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(T_MenuScreen, "App is started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(T_MenuScreen, "App is stopped");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(T_MenuScreen, "App is destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(T_MenuScreen, "App is paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(T_MenuScreen, "App is resumed");
    }
}
