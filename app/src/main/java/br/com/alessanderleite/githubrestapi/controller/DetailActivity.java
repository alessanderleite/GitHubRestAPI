package br.com.alessanderleite.githubrestapi.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import br.com.alessanderleite.githubrestapi.R;

public class DetailActivity extends AppCompatActivity {
    private TextView mLink, mUsername;
    private Toolbar mActionBarToolbar;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.user_image_header);
        mUsername = (TextView) findViewById(R.id.username);

        mLink = (TextView) findViewById(R.id.link);

        String username = getIntent().getExtras().getString("login");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");

        mLink.setText(link);
        Linkify.addLinks(mLink, Linkify.WEB_URLS);

        mUsername.setText(username);
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.load)
                .into(imageView);

        getSupportActionBar().setTitle("Details Activity");
    }

    private Intent createShareForcastIntent() {
        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("link");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer O" + ", " + link)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForcastIntent());
        return true;
    }
}
