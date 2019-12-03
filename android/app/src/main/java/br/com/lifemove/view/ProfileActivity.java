package br.com.lifemove.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.lifemove.R;
import br.com.lifemove.model.SportsEvent;
import br.com.lifemove.model.User;
import br.com.lifemove.service.UserService;
import br.com.lifemove.utils.SessionUtils;
import br.com.lifemove.view.adapter.EventsAdapter;

public class ProfileActivity extends AppCompatActivity {

    private TextView fullNameView;
    private TextView followersCountView;
    private TextView followingCountView;
    private TextView eventsCountView;

    private User user;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupActionBar();

        userService = new UserService();
        user = userService.getByUsername(SessionUtils.loggedUsername());

        if(user != null) {
            setViews();
            setUserInfo();

            RecyclerView userEvents = findViewById(R.id.user_events);
            EventsAdapter adapter = new EventsAdapter(user.getEvents(), false, false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            userEvents.setAdapter(adapter);
            userEvents.setLayoutManager(layoutManager);

        } else {
            Toast.makeText(this, R.string.user_not_found, Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void setViews() {
        fullNameView = findViewById(R.id.user_name_view);
        followersCountView = findViewById(R.id.followers_count_view);
        followingCountView = findViewById(R.id.following_count_view);
        eventsCountView = findViewById(R.id.events_count_view);
    }

    private void setUserInfo() {
        fullNameView.setText(user.getName());
        followersCountView.setText(String.format("%1$s", user.getFollowers().size()));
        followingCountView.setText(String.format("%1$s", user.getFollowing().size()));
        eventsCountView.setText(String.format("%1$s", user.getEvents().size()));
    }


    private void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(R.string.profile_prompt);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        setUserInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
