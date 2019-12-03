package br.com.lifemove.view.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.lifemove.R;
import br.com.lifemove.model.SportsEvent;
import br.com.lifemove.service.MapsService;
import br.com.lifemove.utils.DateUtils;

public class EventHolder extends RecyclerView.ViewHolder {

    private TextView eventTitleView;
    private TextView eventLocationView;
    private TextView eventStartTimeView;
    private TextView eventEndTimeView;

    private Button joinButton;
    private Button dismissButton;

    private MapsService mapsService;

    public EventHolder(@NonNull View itemView) {
        super(itemView);
        eventTitleView = itemView.findViewById(R.id.event_title_view);
        eventLocationView = itemView.findViewById(R.id.event_location_view);
        eventStartTimeView = itemView.findViewById(R.id.event_start_time_view);
        eventEndTimeView = itemView.findViewById(R.id.event_end_time_view);

        joinButton = itemView.findViewById(R.id.event_join_button);
        dismissButton = itemView.findViewById(R.id.event_dismiss_button);

        mapsService = new MapsService();
    }

    public void setEvent(SportsEvent sportsEvent, boolean joinButtonVisible, boolean dismissButtonVisible) {
        eventTitleView.setText(sportsEvent.getTitle());
        eventLocationView.setText(mapsService.getAddress(sportsEvent.getLocationLongitude(), sportsEvent.getLocationLatitude()));
        eventStartTimeView.setText(DateUtils.timestampToDisplayDate(sportsEvent.getStartTime()));
        eventEndTimeView.setText(DateUtils.timestampToDisplayDate(sportsEvent.getEndTime()));

        joinButton.setVisibility((joinButtonVisible) ? View.VISIBLE : View.INVISIBLE);
        dismissButton.setVisibility((dismissButtonVisible) ? View.VISIBLE : View.INVISIBLE);
    }
}
