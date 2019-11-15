package com.example.nyt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nyt.FakeDatabase;
import com.example.nyt.R;
import com.example.nyt.model.Article;
import com.example.nyt.model.CatResponse;

public class ArticleDetailActivity extends AppCompatActivity {
    private TextView headlineTextView;
    private TextView authorTextView;
    private TextView contentTextView;
    private ImageView imageView;
    private TextView txvw1, txvw2, txvw3, txvw4, txvw5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        // Get the intent that was used to travel to this activity
        Intent intent = getIntent();

        String articleID = intent.getStringExtra("ArticleID");

        // This will now work because first we added the articles that resulted from the Gson
        // conversion to the FakeDatabase.
        CatResponse article = FakeDatabase.getArticleById(articleID);

        headlineTextView = findViewById(R.id.detailHeadline);
        authorTextView = findViewById(R.id.detailAuthor);
        contentTextView = findViewById(R.id.detailContent);
        imageView = findViewById(R.id.detailImage);
        txvw1 = findViewById(R.id.textview1);
        txvw2 = findViewById(R.id.textview2);
        txvw3 = findViewById(R.id.text3);
        txvw4 = findViewById(R.id.textview4);
        txvw5 = findViewById(R.id.textview5);

        headlineTextView.setText(article.getName());
        authorTextView.setText(article.getOrigin());


        // NOTE: The New York Times doesn't actually provide the full content of their articles.
        // This is why the JSON doesn't contain any content field. But, you can imagine if they did,
        // then it would be very simple to display that content here.
        //
        // If we turn our focus to the Books data, then there is a lot more that we can work with.
        contentTextView.setText(article.getDescription());

        // Setting the image.
        // Notice that the image we get is very blurry. This is because we've just selected the
        // first link in the JSON (by using index 0 after getMedia()). You could think of a way to
        // write a method that allows you to get the biggest image out of the array of images.
//        if (article.getMedia() != null) {
//            String imageUrl = article.getMedia()[0].getMedia_metadata()[0].getUrl();
//            Glide.with(this).load(imageUrl).into(imageView);
//        }
        txvw1.setText(article.getLife_span());
        txvw2.setText(article.getDog_friendly());
        txvw3.setText(article.getTemperament());
        txvw4.setText(article.getWikipedia_url());
        txvw5.setText(article.getWeigth().getMetric());

    }
}
