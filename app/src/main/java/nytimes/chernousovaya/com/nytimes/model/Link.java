package nytimes.chernousovaya.com.nytimes.model;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Link {
   @GET("names.json")
   Call<Object> getNames(@Query("api-key") String apiKey);
}
