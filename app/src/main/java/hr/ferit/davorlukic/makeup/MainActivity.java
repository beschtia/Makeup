package hr.ferit.davorlukic.makeup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter recyclerAdapter;
    private Button btnSearch;
    private EditText etSearch;
    private Call<List<Product>> apiCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecycler();
        Fresco.initialize(this);
        btnSearch = findViewById(R.id.btnSearch);
        etSearch = findViewById(R.id.etSearch);
    }

    private void setUpRecycler() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    public void customBtnSearchClicked(View view) {
        String searchBrand = etSearch.getText().toString();
        setUpApiCall(searchBrand);
    }

    private void setUpApiCall(String searchBrand) {
        apiCall = NetworkUtils.getApiInterface().getProducts(searchBrand);
        apiCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.body().isEmpty()){
                    Toast.makeText(MainActivity.this, "No search results were found, check spelling or try another brand", Toast.LENGTH_LONG).show();
                }
                if (response.isSuccessful() && response.body() != null){
                    showProducts(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showProducts(List<Product> body) {
        List<String> names = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        List<String> ratings = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<String> images = new ArrayList<>();


        for (Product tempProduct : body){
            names.add("Name: " + tempProduct.getName());
            prices.add("Price: " + tempProduct.getPrice());
            ratings.add("Rating: " + tempProduct.getRating());
            descriptions.add(tempProduct.getDescription().replaceAll("[\n\t]"," "));
            images.add(tempProduct.getImage_link());
        }

        recyclerAdapter.addNames(names);
        recyclerAdapter.addPrices(prices);
        recyclerAdapter.addRatings(ratings);
        recyclerAdapter.addDescriptions(descriptions);
        recyclerAdapter.addImages(images);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (apiCall != null)
            apiCall.cancel();
    }

}
