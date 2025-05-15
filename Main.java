package project2;

import project.service.AuthService;

public class Main {
    public static void main(String[] args) {

        System.out.println("-----hotel management system-----");
        AuthService authService =new AuthService();
        authService.service();
    }
}
