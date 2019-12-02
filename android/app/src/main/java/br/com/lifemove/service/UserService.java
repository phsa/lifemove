package br.com.lifemove.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.lifemove.model.SportsEvent;
import br.com.lifemove.model.User;
import br.com.lifemove.model.enums.SportCategory;

public class UserService {

    private static List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User(
                            "Administrador",
                            "admin",
                            "admin@admin.com",
                            "admin@admin",
                            Collections.singletonList(
                                    new User("Paulo H Sousa", "phsa", "paulo@paulo.com", "phsa1234")
                            ),
                            new ArrayList<User>(),
                            Arrays.asList(
                                    new SportsEvent(
                                            "3ª Pedalada do SESC",
                                            "Evento promovido pelo SESC Crateús",
                                            1575554400,
                                            1575565200,
                                            -5.175357,
                                            -40.6765753,
                                            SportCategory.CYCLING),
                                    new SportsEvent(
                                            "Corrida dos Servidores",
                                            "Evento promovido pela Prefeitura de Crateús",
                                            1575792000,
                                            1575802800,
                                            -5.1743189,
                                            -40.6701352,
                                            SportCategory.JOGGING))
                    ),
                    new User("Paulo H Sousa",
                            "phsa",
                            "paulo@paulo.com",
                            "phsa1234",
                            new ArrayList<User>(),
                            Collections.singletonList(
                                    new User("Administrador", "admin", "admin@admin.com", "admin@admin")
                            ),
                            Collections.singletonList(
                                    new SportsEvent(
                                            "Handeball para iniciantes",
                                            "Evento aberto a todos os públicos",
                                            1575727200,
                                            1575738000,
                                            -5.1744376,
                                            -40.6758752,
                                            SportCategory.HANDBALL)
                            )
                    ),
                    new User("Anderson Almada",
                            "almada",
                            "anderson@almada.com",
                            "andersonalmada",
                            Arrays.asList(
                                    new User("Paulo H Sousa", "phsa", "paulo@paulo.com", "phsa1234"),
                                    new User("Administrador", "admin", "admin@admin.com", "admin@admin")
                            ),
                            Collections.singletonList(new User("Administrador", "admin", "admin@admin.com", "admin@admin")),
                            Arrays.asList(
                                    new SportsEvent(
                                            "Racha da UFC",
                                            "Evento promovido pelos servidores da UFC (Leve seus 10 reais)",
                                            1575410400,
                                            1575414000,
                                            -5.175357,
                                            -40.6765753,
                                            SportCategory.FOOTBALL),
                                    new SportsEvent(
                                            "Caminhada da UFC",
                                            "Evento promovido pela Prefeitura da Universidade Federal do Ceará",
                                            1575410400,
                                            1575414000,
                                            -5.1864371,
                                            -40.6452647,
                                            SportCategory.WALKING))
                    )
            )
    );


    public void add(User user) {
        users.add(user);
    }

    public User getByUsername(String username) {
        for (User user: getAll()) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public List<User> getAll() {
        return users;
    }

}
