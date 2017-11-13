package retroexample.thanasakis.ai.retrofitjsonexample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by programbench on 11/11/2017.
 */

public class GitHubResponse {

        @SerializedName("total_count")
        @Expose
        private Integer totalCount;
        @SerializedName("incomplete_results")
        @Expose
        private Boolean incompleteResults;
        @SerializedName("items")
        @Expose
        private List<GitHubResponseItem> items = null;

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public Boolean getIncompleteResults() {
            return incompleteResults;
        }

        public void setIncompleteResults(Boolean incompleteResults) {
            this.incompleteResults = incompleteResults;
        }

        public List<GitHubResponseItem> getItems() {
            return items;
        }

        public void setItems(List<GitHubResponseItem> items) {
            this.items = items;
        }

}
