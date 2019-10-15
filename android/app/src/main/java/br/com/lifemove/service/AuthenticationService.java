package br.com.lifemove.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.lifemove.listener.SimpleAsynchronousTaskListener;
import br.com.lifemove.model.User;

public class AuthenticationService {

    private static List<User> users = new ArrayList<>(Collections.singletonList(new User("Administrador", "admin", "admin@admim.com", "admin")));

    private SimpleAsynchronousTaskListener listener;

    public AuthenticationService(SimpleAsynchronousTaskListener listener) {
        this.listener = listener;
    }

    public void authenticate(String username, String password) {
        for (User user: users)
            if (user.getUsername().compareTo(username) == 0
                    && user.getPassword().compareTo(password) == 0)
                listener.onSuccess();
        listener.onFailure("Usuário ou senha inválido");
    }

    public void register(User user) {
        if (user != null) {
            for (User savedUser : users)
                if (savedUser.getUsername().compareTo(user.getUsername()) == 0)
                    listener.onFailure("Username não disponível. Tente outro.");
            users.add(user);
            listener.onSuccess();
        } else listener.onFailure("Algo deu errado no cadastro. Sem dados para tal.");
    }

}
