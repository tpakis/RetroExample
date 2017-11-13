package retroexample.thanasakis.ai.retrofitjsonexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retroexample.thanasakis.ai.retrofitjsonexample.adapters.GitHubItemsAdapter;
import retroexample.thanasakis.ai.retrofitjsonexample.models.GitHubResponse;
import retroexample.thanasakis.ai.retrofitjsonexample.models.GitHubResponseItem;
import retroexample.thanasakis.ai.retrofitjsonexample.utilities.GitHubService;
import retroexample.thanasakis.ai.retrofitjsonexample.utilities.GitHubUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.git_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_button)
    Button mSearchButton;
    @BindView(R.id.search_query)
    EditText mSearchQueryText;
    @BindString(R.string.error_query)
    String errorquery;

    public GitHubItemsAdapter mAdapter;
    private GitHubService mService;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mService = GitHubUtils.getGitHubService();
        mAdapter = new GitHubItemsAdapter(this, new ArrayList<GitHubResponseItem>(0), new GitHubItemsAdapter.PostItemListener() {

            @Override
            public void onPostClick(String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

    }
    public void loadAnswers() {
        mService.getItems(mSearchQueryText.getText().toString()).enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {

                if(response.isSuccessful()) {

                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "succesfull json load");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<GitHubResponse> call, Throwable t) {
               // showErrorMessage();
                Log.d("MainActivity", "error loading json");

            }
        });
    }

    @OnClick(R.id.search_button)
    public void searchButtonClick(View v) {
        if (mSearchQueryText.getText().length() > 0) {
            loadAnswers();
        } else {
            Toast.makeText(MainActivity.this, errorquery, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        // Save list state
        listState = layoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, listState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        // Retrieve list state and list/item positions
        if(state != null)
            listState = state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
    }
}
