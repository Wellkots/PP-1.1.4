package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    public static void main(String[] args) {
        UserService  userServiceImpl = new UserServiceImpl();


        userServiceImpl.createUsersTable();

        userServiceImpl.saveUser("Sasha", "Zybov", (byte) 19);
        userServiceImpl.saveUser("Gosha", "Surov", (byte) 24);
        userServiceImpl.saveUser("Misha", "Bytov", (byte) 32);
        userServiceImpl.saveUser("Gena", "Murov", (byte) 47);
        userServiceImpl.removeUserById(3);
        userServiceImpl.getAllUsers();
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}

