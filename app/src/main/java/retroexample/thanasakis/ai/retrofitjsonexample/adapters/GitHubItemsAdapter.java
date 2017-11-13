package retroexample.thanasakis.ai.retrofitjsonexample.adapters;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retroexample.thanasakis.ai.retrofitjsonexample.R;
import retroexample.thanasakis.ai.retrofitjsonexample.models.GitHubResponseItem;

/**
 * Created by programbench on 11/10/2017.
 */

public class GitHubItemsAdapter extends RecyclerView.Adapter<GitHubItemsAdapter.ViewHolder> {

        private List<GitHubResponseItem> mItems;
        private Context mContext;
        private PostItemListener mItemListener;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            @BindView(R.id.git_card)
            CardView itemCard;
            @BindView(R.id.item_name)
            public TextView nameText;
            @BindView(R.id.item_description)
            public TextView descText;

            PostItemListener mItemListener;

            public ViewHolder(View itemView, PostItemListener postItemListener) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                this.mItemListener = postItemListener;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                GitHubResponseItem item = getItem(getAdapterPosition());
                this.mItemListener.onPostClick(item.getHtmlUrl());

                notifyDataSetChanged();
            }
        }

        public GitHubItemsAdapter(Context context, List<GitHubResponseItem> posts, PostItemListener itemListener) {
            mItems = posts;
            mContext = context;
            mItemListener = itemListener;
        }

        @Override
        public GitHubItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View postView = inflater.inflate(R.layout.card, parent, false);

            ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(GitHubItemsAdapter.ViewHolder holder, int position) {

            GitHubResponseItem item = mItems.get(position);
            TextView firstLine = holder.nameText;
            firstLine.setText(item.getName());
            TextView secondLine = holder.descText;
            secondLine.setText(item.getDescription());
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public void updateAnswers(List<GitHubResponseItem> items) {
            mItems = items;
            notifyDataSetChanged();
        }

        private GitHubResponseItem getItem(int adapterPosition) {
            return mItems.get(adapterPosition);
        }

        public interface PostItemListener {
            void onPostClick(String url);
        }
    }
