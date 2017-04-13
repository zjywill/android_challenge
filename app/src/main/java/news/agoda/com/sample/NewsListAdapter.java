package news.agoda.com.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import news.agoda.com.sample.model.domain.entity.MediaEntity;
import news.agoda.com.sample.model.domain.entity.NewsEntity;
import news.agoda.com.sample.util.Loge;

public class NewsListAdapter extends BaseAdapter {
    private static class ViewHolder {
        TextView newsTitle;
        TextView newsAbstract;
        ImageView imageView;
    }

    private List<NewsEntity> newsItemList;
    private Context context;

    public NewsListAdapter(Context context, int resource, List<NewsEntity> objects) {
        super();
        this.context = context;
        this.newsItemList = objects;
    }

    @Override
    public int getCount() {
        return newsItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
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

        List<MediaEntity> mediaEntityList = newsEntity.getMediaEntity();
        if (mediaEntityList != null && mediaEntityList.size() > 0) {
            viewHolder.imageView.setVisibility(View.VISIBLE);
            String thumbnailURL = "";
            MediaEntity mediaEntity = mediaEntityList.get(0);
            thumbnailURL = mediaEntity.getUrl();
            Loge.d("thumbnailURL: " + thumbnailURL);
            Glide.with(context.getApplicationContext())
                .load(thumbnailURL)
                .placeholder(R.drawable.place_holder)
                .into(viewHolder.imageView);
        } else {
            viewHolder.imageView.setVisibility(View.GONE);
        }
        return convertView;
    }
}
