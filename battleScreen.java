package com.example.kishan.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class battleScreen extends AppCompatActivity {
    Character char1,char2;
    Button attack1,attack2,heal1,heal2,executeTurnButton;
    String char1Info,char2Info,action1Selected,action2Selected;
    TextView char1InfoBox,char2InfoBox,actionSelected;
    ProgressBar char1HealthBar,char2HealthBar;
    boolean char1Turn=true;
    boolean char1Selected = false;
    boolean char2Selected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);


        String char1Name = getIntent().getStringExtra("char1");
        String char2Name = getIntent().getStringExtra("char2");

        char1 = createCharacter(char1Name);
        char2 = createCharacter(char2Name);




        attack1 = (Button) findViewById(R.id.char1Attack);
        attack2 = (Button) findViewById(R.id.char2Attack);

        heal1 = (Button) findViewById(R.id.char1Heal);
        heal2 = (Button) findViewById(R.id.char2Heal);

        executeTurnButton = (Button) findViewById(R.id.executeTurnButton);

        char1InfoBox = (TextView) findViewById(R.id.charInfo1);
        char2InfoBox = (TextView) findViewById(R.id.charInfo2);
        actionSelected = (TextView)  findViewById(R.id.actionSelected);

        char1HealthBar = (ProgressBar) findViewById(R.id.char1Health);
        char2HealthBar = (ProgressBar) findViewById(R.id.char2Health);


        char1HealthBar.setMax(char1.getHealth());
        char1HealthBar.setProgress(char1.getHealth());
        char1HealthBar.set

        char2HealthBar.setMax(char2.getHealth());
        char2HealthBar.setProgress(char2.getHealth());

        setTitle("Battle royale between " + char1.getName() + " and " + char2.getName());
        updateInfoFields();


        attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action1Selected=char1.getName() + " is going to attack" + "\n";
                actionSelected.setText(action1Selected + action2Selected);
                char1.setAttacking(true);
                char1Selected = true;
            }
        });
        attack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action2Selected=char2.getName() + " is going to attack" + "\n";
                actionSelected.setText(action1Selected + action2Selected);
                char2.setAttacking(true);
                char2Selected = true;
            }
        });

        heal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action1Selected=char1.getName() + " is going to heal" + "\n";
                actionSelected.setText(action1Selected + action2Selected);
                char1.setAttacking(false);
                char1Selected = true;
            }
        });

        heal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action2Selected=char2.getName() + " is going to heal" + "\n";
                actionSelected.setText(action1Selected + action2Selected);
                char2.setAttacking(false);
                char2Selected = true;
            }
        });

        executeTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(char1Selected && char2Selected){
                    char1Turn=!char1Turn;
                    executeTurn(char1,char2, char1Turn);
                    updateInfoFields();
                }
            }
        });


    }
    public Character createCharacter(String characterName){
        switch(characterName){
            case "Ravi":
                return new Character("Ravi",4,3,20);
            case "Hanafi":
                return new Character("Hanafi",6,1,15);
        }
        return null;
    }

    public void executeTurn(Character c1, Character c2, boolean isChar1Turn){
        if(isChar1Turn){
            if(char1.isAttacking){
                char2.getsAttacked(char1);
            }else{
                char1.heals();
            }
        }else{
            if(char2.isAttacking){
                char1.getsAttacked(char2);
            }else{
                char1.heals();
            }
        }
    }

    public void updateInfoFields(){
        char1Info = ("Character: " + char1.getName() + "\n" + "Health: " + char1.getHealth() + "\n"
                + "Attack: " + char1.getAttackValue() + "\n" + "Defense: " + char1.getDefenseValue());
        char1InfoBox.setText(char1Info);

        char2Info = ("Character: " + char2.getName() + "\n" + "Health: " + char2.getHealth() + "\n"
                + "Attack: " + char2.getAttackValue() + "\n" + "Defense: " + char2.getDefenseValue());
        char2InfoBox.setText(char2Info);

        char1HealthBar.setProgress(char1.getHealth());
        char2HealthBar.setProgress(char2.getHealth());
    }
}
