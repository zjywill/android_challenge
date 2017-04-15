package news.agoda.com.sample.fragment.headline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import news.agoda.com.sample.R;
import news.agoda.com.sample.model.domain.entity.NewsEntity;

public class HeadLineListAdapter extends BaseAdapter {
    private List<NewsEntity> newsItemList = new ArrayList<>();
    private Context context;
    public HeadLineListAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setNewsItemList(List<NewsEntity> newsItemList) {
        this.newsItemList.clear();
        this.newsItemList.addAll(newsItemList);
    }

    @Override
    public int getCount() {
        return newsItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return newsItemList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsEntity newsEntity = (NewsEntity) getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_news, parent, false);
            viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.newsAbstract = (TextView) convertView.findViewById(R.id.news_abstract);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.news_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.newsTitle.setText(newsEntity.getTitle());
        viewHolder.newsAbstract.setText(newsEntity.getSummary());

        String standardImage = newsEntity.getStandardImage();
        if (standardImage != null) {
            viewHolder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context)
                .load(standardImage)
                .placeholder(R.drawable.place_holder)
                .into(viewHolder.imageView);
        } else {
            viewHolder.imageView.setVisibility(View.GONE);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView newsTitle;
        TextView newsAbstract;
        ImageView imageView;
    }
}
