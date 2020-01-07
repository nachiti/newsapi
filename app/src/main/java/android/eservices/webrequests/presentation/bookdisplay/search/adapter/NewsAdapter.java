package android.eservices.webrequests.presentation.bookdisplay.search.adapter;

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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView authorsTextView;
        private ImageView iconImageView;
        private View v;
        private NewsItemViewModel newsItemViewModel;
        private NewsActionInterface newsActionInterface;
        private Switch favoriteSwitch;

        public NewsViewHolder(View v, final NewsActionInterface newsActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.book_title_textview);
            authorsTextView = v.findViewById(R.id.book_authors_textview);
            iconImageView = v.findViewById(R.id.book_icon_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            this.newsActionInterface = newsActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    newsActionInterface.onFavoriteToggle(newsItemViewModel.getNewsId(), b);
                }
            });
        }

        void bind(NewsItemViewModel newsItemViewModel) {
            this.newsItemViewModel = newsItemViewModel;
            titleTextView.setText(newsItemViewModel.getNewsTitle());
            authorsTextView.setText(newsItemViewModel.getNewsAuthors());
            favoriteSwitch.setChecked(newsItemViewModel.isFavorite());
            Glide.with(v)
                    .load(newsItemViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(iconImageView);

        }

    }

    private List<NewsItemViewModel> newsItemViewModelList;
    private NewsActionInterface newsActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsAdapter(NewsActionInterface newsActionInterface) {
        newsItemViewModelList = new ArrayList<>();
        this.newsActionInterface = newsActionInterface;
    }

    public void bindViewModels(List<NewsItemViewModel> newsItemViewModelList) {
        this.newsItemViewModelList.clear();
        this.newsItemViewModelList.addAll(newsItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(v, newsActionInterface);
        return newsViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(newsItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return newsItemViewModelList.size();
    }


}