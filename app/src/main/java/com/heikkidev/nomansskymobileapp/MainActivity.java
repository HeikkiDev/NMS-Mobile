package com.heikkidev.nomansskymobileapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    TextView txtMainText;
    Button btnExplore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        txtMainText = (TextView)findViewById(R.id.txtMainText);
        btnExplore = (Button)findViewById(R.id.btnExplore);
        btnExplore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Spannable text1 = new SpannableString("You are on a ");
        Spannable text2 = new SpannableString(" planet with ");
        Spannable text3 = new SpannableString(" trees and ");
        Spannable text4 = new SpannableString(" dinosaurs.");

        ColorModel color1 = getRandomColor();
        ColorModel color2 = getRandomColor();
        ColorModel color3 = getRandomColor();


        Spannable textPlanet = new SpannableString(color1.colorName);
        Spannable textTree = new SpannableString(color2.colorName);
        Spannable textDinosaurs = new SpannableString(color3.colorName);

        textPlanet.setSpan(new ForegroundColorSpan(context.getResources().getColor(color1.colorToUse)), 0, textPlanet.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textTree.setSpan(new ForegroundColorSpan(context.getResources().getColor(color2.colorToUse)), 0, textTree.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textDinosaurs.setSpan(new ForegroundColorSpan(context.getResources().getColor(color3.colorToUse)), 0, textDinosaurs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        txtMainText.setText(text1);
        txtMainText.append(textPlanet);
        txtMainText.append(text2);
        txtMainText.append(textTree);
        txtMainText.append(text3);
        txtMainText.append(textDinosaurs);
        txtMainText.append(text4);

        //Animation that moves the text from center to the right
        Animation animationToRight = AnimationUtils.loadAnimation(MainActivity.this, R.anim.main_text_move_from_left);

        if(txtMainText != null){
            txtMainText.setAnimation(animationToRight);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_online:

                new AlertDialog.Builder(context)
                        .setTitle("Online mode not available")
                        .setMessage("Sorry, but the online mode is not yet available. Coming soon (in 2021).")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //
                            }
                        })
                        .setIcon(R.drawable.app_icon)
                        .show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ColorModel getRandomColor(){
        int colorToUse = 0;
        String colorName = "";
        ColorModel colorModel = new ColorModel();

        String[] colorNames = getResources().getStringArray(R.array.colorNames);

        Random random = new Random();
        int index = random.nextInt(colorNames.length -1);

        //Getting the color resource id
        TypedArray ta = getResources().obtainTypedArray(R.array.colorList);
        colorToUse = ta.getResourceId(index, 0);
        colorName = colorNames[index];

        colorModel.colorToUse = colorToUse;
        colorModel.colorName = colorName;
        return colorModel;
    }
}
