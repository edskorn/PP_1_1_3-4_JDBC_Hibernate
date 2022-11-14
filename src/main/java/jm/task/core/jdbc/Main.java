package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ed", "Skorn", (byte) 25);
        userService.saveUser("Tom", "Sowyer", (byte) 17);
        userService.saveUser("Bob", "Marley", (byte) 37);
        userService.saveUser("James", "Bond", (byte) 35);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
