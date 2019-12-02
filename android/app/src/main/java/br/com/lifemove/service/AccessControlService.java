package br.com.lifemove.service;

import br.com.lifemove.R;
import br.com.lifemove.listener.AccessControlListener;
import br.com.lifemove.model.User;
import br.com.lifemove.utils.SessionUtils;
import br.com.lifemove.utils.StringUtils;

public class AccessControlService {

    private AccessControlListener listener;
    private UserService userService;

    public AccessControlService(AccessControlListener listener) {
        this.listener = listener;
        this.userService = new UserService();
    }

    public void authenticate(String login, String password) {
        for (User user : userService.getAll()) {
            if ((user.getUsername().compareTo(login) == 0 || user.getEmail().compareTo(login) == 0)
                    && user.getPassword().compareTo(password) == 0) {
                SessionUtils.saveSession(user.getUsername(), user.getPassword());
                listener.onSuccess();
                return;
            }
        }
        listener.onFailure(StringUtils.valueOf(R.string.login_failed));
    }

    public void register(User user) {
        if (user != null) {
            for (User savedUser : userService.getAll())
                if (savedUser.getUsername().compareTo(user.getUsername()) == 0)
                    listener.onFailure(StringUtils.valueOf(R.string.unavailable_username));
            userService.add(user);
            SessionUtils.saveSession(user.getUsername(), user.getPassword());
            listener.onSuccess();
        } else listener.onFailure(StringUtils.valueOf(R.string.authentication_has_crashed));
    }

    public void checkForUsername(String username) {
        boolean isAvailable = true;
        for (User user: userService.getAll())
            if (user.getUsername().compareTo(username) == 0) {
                listener.handleUsernameCheck(username, false);
                isAvailable = false;
            }
        if (isAvailable)
            listener.handleUsernameCheck(username, true);
    }

}
