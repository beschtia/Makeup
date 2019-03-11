package hr.ferit.davorlukic.makeup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("products.json")
    Call<List<Product>> getProducts(@Query("brand") String searchBrand);
}
