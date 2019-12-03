package br.com.lifemove.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.lifemove.R;
import br.com.lifemove.model.SportsEvent;
import br.com.lifemove.utils.LifeMoveApplicationUtils;
import br.com.lifemove.utils.StringUtils;
import br.com.lifemove.view.holder.EventHolder;

public class EventsAdapter extends RecyclerView.Adapter<EventHolder> {

    private List<SportsEvent> events;
    private boolean joinButtonVisible = false;
    private boolean dismissButtonVisible = false;


    public EventsAdapter(List<SportsEvent> events, boolean joinButtonVisible, boolean dismissButtonVisible) {
        this.events = events;
        this.joinButtonVisible = joinButtonVisible;
        this.dismissButtonVisible = dismissButtonVisible;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        holder.setEvent(events.get(position), joinButtonVisible, dismissButtonVisible);
//        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(LifeMoveApplicationUtils.getLifeMoveAppContext(), EventActivity.class);
//            intent.putExtra(StringUtils.SPORTS_EVENT_KEY, events.get(position).getId());
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            LifeMoveApplicationUtils.getLifeMoveAppContext().startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
