package com.example.staffapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class OrderPopUp extends Activity {
    private Button btnSaveNotes;
    private TextView inputNoteText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderpopup);
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().GONE);
        btnSaveNotes = (Button)findViewById(R.id.btnSaveNotes);
        inputNoteText = (TextView)findViewById(R.id.inputTextField);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width),(int)(height*.7));

        btnSaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.addedNotes = inputNoteText.getText().toString();
            }
        });
    }
}
