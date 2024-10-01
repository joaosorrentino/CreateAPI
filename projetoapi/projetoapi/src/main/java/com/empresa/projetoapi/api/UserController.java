package com.empresa.projetoapi.api;

import com.empresa.projetoapi.model.User;
import com.empresa.projetoapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Gets user by ID",description= "User must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")})
    @GetMapping(value = "/user", produces = {"application/json","application/xml"})
    public ResponseEntity<User> getUser(@Parameter(
            name =  "id",
            description  = "Id do usuario",
            example = "1",
            required = true) @RequestParam Integer id){
        User user = userService.getUser(id);


        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        } else{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        }


    }

    @Operation(summary = "Gets all users")
    @GetMapping(value = "/users", produces = {"application/json","application/xml"})
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @Operation(summary = "Add user")
    @PostMapping(value = "/user", produces = {"application/json","application/xml"})
    public ResponseEntity<String> postUser(@RequestBody User user){
        try
        {
            userService.postUser(user);
            return ResponseEntity.ok("Usuario criado com sucesso");
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" "+ex);
        }


    }

    @Operation(summary = "Edit user")
    @PutMapping(value = "/user", produces = {"application/json","application/xml"})
    public ResponseEntity<String> editUser(@RequestBody User user){
        try{
            userService.editUser(user);
            return ResponseEntity.ok("Usuario editado com sucesso");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" "+ex);
        }

    }

    @Operation(summary = "Delete user by ID",description= "User must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario deletado"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")})
    @DeleteMapping(value = "/user", produces = {"application/json","application/xml"})
    public ResponseEntity<String> deleteUser(@Parameter(
            name =  "id",
            description  = "Id do usuario",
            example = "1",
            required = true) @RequestParam Integer id){
        if(getUser(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        } else{
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario deletado com sucesso");
        }

    }
}
