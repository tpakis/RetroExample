package retroexample.thanasakis.ai.retrofitjsonexample.utilities;

/**
 * Created by programbench on 11/10/2017.
 */

import java.util.List;

import retroexample.thanasakis.ai.retrofitjsonexample.models.GitHubResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GitHubService {

    @GET("repositories")
    Call<GitHubResponse> getItems(@Query("q") String gitHubQuery);

}
