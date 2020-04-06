package com.hyteck.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import javax.measure.MetricPrefix;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.quantity.Area;
import javax.measure.quantity.Length;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;
import tech.uom.lib.common.function.QuantityConverter;
import tech.uom.lib.common.function.QuantityFunctions;

import static javax.measure.MetricPrefix.CENTI;
import static javax.measure.MetricPrefix.HECTO;
import static javax.measure.MetricPrefix.TERA;
import static tech.units.indriya.unit.Units.METRE;
import static tech.units.indriya.unit.Units.SQUARE_METRE;

public class MainActivity extends AppCompatActivity {


    private EditText mEnergy;
    private EditText mDistance;
    private RadioButton mTypeDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mSave = findViewById(R.id.btnSearch);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargeSearchOptions();

                SearchOptions searchOptions = new SearchOptions(Arrays.asList(mDistance, mEnergy, mTypeDistance));



                Intent intent = new Intent(MainActivity.this, ResultActivit.class);
                intent.putExtra("searchOptions", searchOptions);
                startActivity(intent);


            }
        });

    }

    private void chargeSearchOptions() {
        mDistance = findViewById(R.id.distancy);
        mEnergy = findViewById(R.id.energy);
        final RadioGroup mTypeDistanceGroup = findViewById(R.id.typeDistance);
        mTypeDistance = findViewById(mTypeDistanceGroup.getCheckedRadioButtonId());
    }
}
