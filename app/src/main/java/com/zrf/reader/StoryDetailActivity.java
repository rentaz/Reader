package com.zrf.reader;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zrf.reader.http.MyHttpClient;
import com.zrf.reader.http.MyHttpUrl;
import com.zrf.reader.http.NetWorkUtils;
import com.zrf.reader.model.StoryDetail;
import com.zrf.reader.model.StorySimple;
import com.zrf.reader.utils.GsonUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-13 21:24
 * 日报详细内容界面
 */
public class StoryDetailActivity extends AppCompatActivity {
    @Bind(R.id.iv_story_image)
    ImageView iv_story_image;
    @Bind(R.id.toolBar_story)
    Toolbar mToolbar;
    @Bind(R.id.collapsingToolbarLayout_story)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.appBarLayout_story)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.wv_story_body)
    WebView wv_story_body;
    @Bind(R.id.coordinatorLayout_story)
    CoordinatorLayout mCoordinatorLayout;

    private StorySimple mStorySimple;
    private StoryDetail mStoryDetail;
    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mStorySimple=getIntent().getParcelableExtra("story_simple");
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mCollapsingToolbarLayout.setTitle(mStorySimple.getTitle());
        wv_story_body.getSettings().setJavaScriptEnabled(true);
        wv_story_body.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_story_body.getSettings().setDomStorageEnabled(true);
        wv_story_body.getSettings().setDatabaseEnabled(true);
        wv_story_body.getSettings().setAppCacheEnabled(true);
        if(NetWorkUtils.isNetWorkConnected(this)){
            MyHttpClient.httpGet(MyHttpUrl.DAILY_BASEURL + mStorySimple.getId(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    mStoryDetail=GsonUtils.getObject(response.body().string(),
                            StoryDetail.class);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            final ImageLoader imageloader = ImageLoader.getInstance();
                            DisplayImageOptions options = new DisplayImageOptions.Builder()
                                    .cacheInMemory(true)
                                    .cacheOnDisk(true)
                                    .build();
                            imageloader.displayImage(mStoryDetail.getImage(), iv_story_image, options);

                            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
                            String html = "<html><head>" + css + "</head><body>" + mStoryDetail.getBody() + "</body></html>";
                            html = html.replace("<div class=\"img-place-holder\">", "");
                            wv_story_body.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
