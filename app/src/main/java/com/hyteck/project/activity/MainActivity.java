package com.hyteck.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.hyteck.project.R;
import com.hyteck.project.entity.SearchOptions;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private EditText mEnergy;
    private EditText mDistance;
    private RadioButton mTypeDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mSave = findViewById(R.id.btnSearch);

        mSave.setOnClickListener(v -> {
            chargeSearchOptions();

            SearchOptions searchOptions = new SearchOptions(Arrays.asList(mDistance, mEnergy, mTypeDistance));

            Intent intent = new Intent(MainActivity.this, ResultActivit.class);
            intent.putExtra("searchOptions", searchOptions);
            startActivity(intent);


        });

    }

    private void chargeSearchOptions() {
        mDistance = findViewById(R.id.distancy);
        mEnergy = findViewById(R.id.energy);
        final RadioGroup mTypeDistanceGroup = findViewById(R.id.typeDistance);
        mTypeDistance = findViewById(mTypeDistanceGroup.getCheckedRadioButtonId());
    }
}
