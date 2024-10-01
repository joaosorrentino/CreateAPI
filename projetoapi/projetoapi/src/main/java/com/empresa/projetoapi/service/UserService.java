package com.empresa.projetoapi.service;

import com.empresa.projetoapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService(){
        userList = new ArrayList<>();
        User user1 = new User(1, "Maria",23,"maria@gmail.com");
        User user2 = new User(2, "Renan",24,"renan@gmail.com");
        User user3 = new User(3, "Buzina",22,"buzina@gmail.com");
        User user4 = new User(4, "Luigi",51,"luigi@gmail.com");
        User user5 = new User(5, "Matthew",23,"matthew@gmail.com");

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }

    public User getUser(Integer id) {
        for (User user : userList) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUser(){
        return userList;
    }

    public void postUser(User user){
        userList.add(user);
    }

    public void editUser(User user){
        for(User users : userList){
            if(user.getId() == users.getId()){
                userList.remove(users);
                User newUser = new User(user.getId(), user.getName(),user.getAge(),user.getEmail());
                userList.add(newUser);
            }
        }
    }

    public void deleteUser(Integer id){
        for(User user : userList){
            if(id == user.getId()){
                userList.remove(user);
            }
        }
    }
}
