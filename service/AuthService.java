package project2.service;

import project2.entity.User;
import project2.entity.enums.Role;

import static project2.db.Datasource.currentUser;
import static project2.db.Datasource.users;
import static project2.tools.Util.*;
import static project2.tools.Util.strScanner;

public class AuthService {

    AdminService adminService = new AdminService();
    UserService userService = new UserService();

    public void service() {

        while (true) {

            System.out.println("""
                    0 exit
                    1 sign up
                    2 sign in
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    signUp();
                }
                case 2 -> {
                    signIn();
                }
            }
        }
    }

    private void signIn() {


        System.out.println("enter email");
        String email = strScanner.nextLine();
        System.out.println("enter password");
        String password = strScanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                if (user.getRole().equals(Role.ADMIN)) {
                    adminService.service();
                    return;
                }
                if (user.getRole().equals(Role.USER)) {
                    currentUser = user;
                    userService.service();
                    return;
                }
            }
        }
        System.out.println("user not found");
    }

    private void signUp() {

        System.out.println("enter name");
        String name = strScanner.nextLine();
        System.out.println("enter surname");
        String surname = strScanner.nextLine();
        System.out.println("enter email");
        String email = strScanner.nextLine();
        System.out.println("enter password");
        String password = strScanner.nextLine();

        User user = new User(name, surname, email, password, Role.USER);
        users.add(user);


    }
}
