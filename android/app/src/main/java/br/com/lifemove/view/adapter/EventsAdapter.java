package br.com.lifemove.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.lifemove.R;
import br.com.lifemove.model.SportsEvent;
import br.com.lifemove.view.holder.EventHolder;

public class EventsAdapter extends RecyclerView.Adapter<EventHolder> {

    private List<SportsEvent> events;

    public EventsAdapter(List<SportsEvent> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_holder, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        holder.setEvent(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
