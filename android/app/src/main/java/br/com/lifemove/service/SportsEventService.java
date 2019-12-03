package br.com.lifemove.service;

import java.util.ArrayList;
import java.util.List;

import br.com.lifemove.model.SportsEvent;
import br.com.lifemove.model.User;

public class SportsEventService {

    private UserService userService;

    public SportsEventService() {
        userService = new UserService();
    }

    public List<SportsEvent> getEvents(String username) {
        List<SportsEvent> events = new ArrayList<>();
        for(User user : userService.getAll()) {
            if(!user.getUsername().equals(username))
                events.addAll(user.getEvents());
        }
        return events;
    }

}
