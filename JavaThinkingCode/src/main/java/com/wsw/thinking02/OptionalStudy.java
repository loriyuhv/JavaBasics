package com.wsw.thinking02;

import java.util.Optional;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/11 14:36
 * @since 1.0
 */
public class OptionalStudy {
    public static void main(String[] args) {
        // Optional<String> username = Optional.of("Hello");
        // System.out.println(username.get());

        // boolean flag = true;
        // Optional<User> optional;
        // if (!flag) {
        //     User user = new User("Jack");
        //     optional = Optional.of(user);
        // } else {
        //     optional = Optional.empty();
        // }
        //
        // User user = optional.orElse(new User("Jerry"));
        // System.out.println(user);


        OptionalStudy optionalStudy = new OptionalStudy();
        User user = optionalStudy.getUser();
        Optional<User> optional = Optional.ofNullable(user);
        User guest = optional.orElse(new User("guest_user"));
        System.out.println(guest);
    }

    private User getUser() {
        return null;
        // return new User("Jerry");
    }
}

record User(String username) {
}
