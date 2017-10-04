package com.example.kishan.homework2;
/**
 * @author: Kishan Patel
 * @date: 10/3/2017
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class battleScreen extends AppCompatActivity {
    Character char1,char2;
    Button attack1,attack2,heal1,heal2,executeTurnButton;
    String char1Info,char2Info,action1Selected,action2Selected;
    TextView char1InfoBox,char2InfoBox,actionSelected;
    ProgressBar char1HealthBar,char2HealthBar;
    boolean char1Turn=true;
    boolean char1Selected = false;
    boolean char2Selected = false;

    public static final String T_MenuScreen = "T_MenuScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(T_MenuScreen, "App is Created");
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


        char2HealthBar.setMax(char2.getHealth());
        char2HealthBar.setProgress(char2.getHealth());

        setTitle(getString(R.string.battle_menu_1) + " " + char1.getName() + " " + getString(R.string.battle_menu_2) + " " + char2.getName());
        updateInfoFields();

        //When attack is chosen, the action selected textView will populate with data to let the user know
        //what has been chosen.
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
        //Turns are calculated when the execute turn button is hit.
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
    //Function to create the character that was chosen previously. Created based on the string that
    //was extracted from the intent of this activity
    public Character createCharacter(String characterName){
        switch(characterName){
            case "Ravi":
                return new Character("Ravi",4,3,20);
            case "Hanafi":
                return new Character("Hanafi",6,1,15);
        }
        return null;
    }
    //Logic to execute turn. Will alternate turns between character 1 and 2.
    public void executeTurn(Character c1, Character c2, boolean isChar1Turn){
        if(isChar1Turn){
            if(char1.isAttacking){
                char2.getsAttacked(char1);
                if(char2.getHealth()<=0){
                    displayWinner(char1);
                }
            }else{
                char1.heals();
            }
        }else{
            if(char2.isAttacking){
                char1.getsAttacked(char2);
                if(char1.getHealth()<=0){
                    displayWinner(char2);
                }
            }else{
                char1.heals();
            }
        }
    }
    //Function to update the info fields, to let the user know character stats.
    public void updateInfoFields(){
        char1Info = (getString(R.string.info_character) + " " + char1.getName() + "\n" + getString(R.string.info_health) + " " + char1.getHealth() + "\n"
                + getString(R.string.info_attack) + " " + char1.getAttackValue() + "\n" + getString(R.string.info_defense) + " " + char1.getDefenseValue());
        char1InfoBox.setText(char1Info);

        char2Info = ("Character: " + char2.getName() + "\n" + "Health: " + char2.getHealth() + "\n"
                + getString(R.string.info_attack) + " " + char2.getAttackValue() + "\n" + getString(R.string.info_defense) + " " + char2.getDefenseValue());
        char2InfoBox.setText(char2Info);

        char1HealthBar.setProgress(char1.getHealth());
        char2HealthBar.setProgress(char2.getHealth());
    }
    //Function to display the winner. Is called when one characters health reaches 0 or less.
    public void displayWinner(Character winner){
        String text = "Professor " + winner.getName() + " has won!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

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
