package news.agoda.com.sample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pocha on 19/03/17.
 */
public interface INewsFetchRetrofit {
    String baseUrl = "http://some.domain.com";

    @GET("/path/to/http")
    Call<List<NewsEntity>> getNews();
}
