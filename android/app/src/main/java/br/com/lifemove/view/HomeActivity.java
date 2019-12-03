package br.com.lifemove.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.lifemove.R;
import br.com.lifemove.model.SportsEvent;
import br.com.lifemove.service.SportsEventService;
import br.com.lifemove.utils.SessionUtils;
import br.com.lifemove.view.adapter.EventsAdapter;

public class HomeActivity extends AppCompatActivity {

    private SportsEventService eventService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        eventService = new SportsEventService();

        RecyclerView eventsView = findViewById(R.id.home_events);
        EventsAdapter adapter = new EventsAdapter(eventService.getEvents(SessionUtils.loggedUsername()), true, true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        eventsView.setAdapter(adapter);
        eventsView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profile_action) {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}
