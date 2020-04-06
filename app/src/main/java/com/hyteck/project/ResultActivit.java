package com.hyteck.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyteck.project.api.TecnologyApi;
import com.hyteck.project.entity.Tecnology;
import com.hyteck.project.entity.TecnologyAdapter;
import com.hyteck.project.service.TecnologyService;

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
        TecnologyService tecnologyService = new TecnologyService();
        final TecnologyApi tecnologyApi = tecnologyService.calculateTecnologies(searchOptions);
        final Call<List<Tecnology>> callApi = tecnologyApi.list();


        callApi(callApi, getApplicationContext());
    }

    private void callApi(Call<List<Tecnology>> callApi, Context context) {
        callApi.enqueue(new Callback<List<Tecnology>>() {
            @Override
            public void onResponse(Call<List<Tecnology>> call, Response<List<Tecnology>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    tecnologies = response.body();

//                    Context context = getApplicationContext();
                    RecyclerView recyclerView = findViewById(R.id.list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(new TecnologyAdapter(tecnologies));

//                    Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Tecnology item);
    }
}
