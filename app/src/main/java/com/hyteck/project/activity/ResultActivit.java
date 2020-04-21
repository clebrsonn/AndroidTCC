package com.hyteck.project.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyteck.project.R;
import com.hyteck.project.adapter.TecnologyAdapter;
import com.hyteck.project.api.TecnologyApi;
import com.hyteck.project.entity.SearchOptions;
import com.hyteck.project.entity.Tecnology;
import com.hyteck.project.service.TecnologyService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivit extends AppCompatActivity {

    ProgressBar progress;
    private List<Tecnology> tecnologies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        progress = findViewById(R.id.progressbar);
        progress.setVisibility(View.VISIBLE);
        final SearchOptions searchOptions = intent.getParcelableExtra("searchOptions");
        assert searchOptions != null;
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        boolean urlPrd = sharedPreferences.getBoolean("url_prd", false);



        TecnologyService tecnologyService = new TecnologyService();
        final TecnologyApi tecnologyApi = tecnologyService.calculateTecnologies(searchOptions, urlPrd);
        final Call<List<Tecnology>> callApi = tecnologyApi.search(searchOptions);


        callApi(callApi);
    }

    private void callApi(Call<List<Tecnology>> callApi) {
        callApi.enqueue(new Callback<List<Tecnology>>() {
            @Override
            public void onResponse(@NotNull Call<List<Tecnology>> call, @NotNull Response<List<Tecnology>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    tecnologies = response.body();

                    RecyclerView recyclerView = findViewById(R.id.list);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(new TecnologyAdapter(tecnologies));

                    progress.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Tecnology>> call, Throwable t) {
                t.printStackTrace();
                progress.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
