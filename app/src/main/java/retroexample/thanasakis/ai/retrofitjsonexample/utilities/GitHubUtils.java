package retroexample.thanasakis.ai.retrofitjsonexample.utilities;

/**
 * Created by programbench on 11/10/2017.
 */

public class GitHubUtils {

    final static String GITHUB_BASE_URL =
            "https://api.github.com/search/";

    public static GitHubService getGitHubService() {
        return RetrofitClient.getClient(GITHUB_BASE_URL).create(GitHubService.class);
    }
}
