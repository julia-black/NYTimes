package nytimes.chernousovaya.com.apinytimes;

import nytimes.chernousovaya.com.apinytimes.model.ListBooks;
import nytimes.chernousovaya.com.apinytimes.model.RootObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTimes {
    @GET("lists/names.json")
    Call<RootObject> getNames(@Query("api-key") String apiKey);

   @GET("lists.json")
    Call<ListBooks> getListBooksByName(@Query("api-key") String apiKey, @Query("list") String list);
}

