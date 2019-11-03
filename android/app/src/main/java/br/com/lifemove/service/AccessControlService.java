package br.com.lifemove.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.lifemove.R;
import br.com.lifemove.listener.AccessControlListener;
import br.com.lifemove.model.User;
import br.com.lifemove.utils.SharedPreferencesUtils;
import br.com.lifemove.utils.StringUtils;

public class AccessControlService {

    private static List<User> users = new ArrayList<>(Collections.singletonList(new User("Administrador", "admin", "admin@admin.com", "admin@admin")));

    private AccessControlListener listener;

    public AccessControlService(AccessControlListener listener) {
        this.listener = listener;
    }

    public void authenticate(String login, String password) {
        for (User user : users) {
            if ((user.getUsername().compareTo(login) == 0 || user.getEmail().compareTo(login) == 0)
                    && user.getPassword().compareTo(password) == 0) {
                SharedPreferencesUtils.writeInSharedPreferences(SharedPreferencesUtils.USER_KEY, login);
                SharedPreferencesUtils.writeInSharedPreferences(SharedPreferencesUtils.PASSWORD_KEY, password);
                listener.onSuccess();
                return;
            }
        }
        listener.onFailure(StringUtils.valueOf(R.string.login_failed));
    }

    public void register(User user) {
        if (user != null) {
            for (User savedUser : users)
                if (savedUser.getUsername().compareTo(user.getUsername()) == 0)
                    listener.onFailure(StringUtils.valueOf(R.string.unavailable_username));
            users.add(user);
            SharedPreferencesUtils.writeInSharedPreferences(SharedPreferencesUtils.USER_KEY, user.getUsername());
            SharedPreferencesUtils.writeInSharedPreferences(SharedPreferencesUtils.PASSWORD_KEY, user.getPassword());
            listener.onSuccess();
        } else listener.onFailure(StringUtils.valueOf(R.string.authentication_has_crashed));
    }

    public void checkForUsername(String username) {
        boolean isAvailable = true;
        for (User user: users)
            if (user.getUsername().compareTo(username) == 0) {
                listener.handleUsernameCheck(username, false);
                isAvailable = false;
            }
        if (isAvailable)
            listener.handleUsernameCheck(username, true);
    }

}
