package android.eservices.webrequests.presentation.bookdisplay.favorite.adapter;

        import android.eservices.webrequests.R;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CompoundButton;
        import android.widget.ImageView;
        import android.widget.Switch;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

        import java.util.ArrayList;
        import java.util.List;

public class NewsDetailAdapter extends RecyclerView.Adapter<android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailAdapter.NewsDetailViewHolder> {


    public static class NewsDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView languageTextView;
        private TextView descriptionTextView;
        private TextView publishedTextView;
        private ImageView iconImageView;
        private View v;
        private NewsDetailViewModel newsDetailViewModel;
        private NewsDetailActionInterface newsDetailActionInterface;
        private Switch favoriteSwitch;

        public NewsDetailViewHolder(View v, final NewsDetailActionInterface newsDetailActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.book_title_textview);
            languageTextView = v.findViewById(R.id.book_language_textview);
            descriptionTextView = v.findViewById(R.id.book_description_textview);
            publishedTextView = v.findViewById(R.id.book_published_textview);
            authorsTextView = v.findViewById(R.id.book_authors_textview);
            iconImageView = v.findViewById(R.id.book_icon_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            setupListeners();
            this.newsDetailActionInterface = newsDetailActionInterface;
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!b) {
                        newsDetailActionInterface.onRemoveFavorite(newsDetailViewModel.getNewsId());
                    }
                }
            });
        }

        void bind(NewsDetailViewModel newsDetailViewModel) {
            this.newsDetailViewModel = newsDetailViewModel;
            titleTextView.setText(newsDetailViewModel.getNewsTitle());
            authorsTextView.setText(newsDetailViewModel.getNewsAuthors());
            descriptionTextView.setText(newsDetailViewModel.getNewsDescription());
            favoriteSwitch.setChecked(true);
            if (newsDetailViewModel.getNewsDescription() == null) {
                descriptionTextView.setVisibility(View.GONE);
            } else {
                descriptionTextView.setVisibility(View.VISIBLE);
            }
            publishedTextView.setText(newsDetailViewModel.getNewsPublishedDate());
            Glide.with(v)
                    .load(newsDetailViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iconImageView);

        }

    }

    private List<NewsDetailViewModel> newsDetailViewModelList;
    private NewsDetailActionInterface newsDetailActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsDetailAdapter(NewsDetailActionInterface newsDetailActionInterface) {
        newsDetailViewModelList = new ArrayList<>();
        this.newsDetailActionInterface = newsDetailActionInterface;
    }

    public void bindViewModels(List<NewsDetailViewModel> newsItemViewModelList) {
        this.newsDetailViewModelList.clear();
        this.newsDetailViewModelList.addAll(newsItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailAdapter.NewsDetailViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detailed_book, parent, false);
        android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailAdapter.NewsDetailViewHolder newsDetailViewHolder = new android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailAdapter.NewsDetailViewHolder(v, newsDetailActionInterface);
        return newsDetailViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.NewsDetailAdapter.NewsDetailViewHolder holder, int position) {
        holder.bind(newsDetailViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return newsDetailViewModelList.size();
    }


}