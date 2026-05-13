package at.fhtw.bic.carsharing.controller;

import at.fhtw.bic.carsharing.persistence.entity.User;
import at.fhtw.bic.carsharing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping( "/register" )
    public ResponseEntity<String> createUser(@RequestBody User user) {
        user = userService.createUser(user);
        return new ResponseEntity<>(user.getId().toString(), HttpStatus.CREATED);
    }


    @PostMapping( "/login" )
    public ResponseEntity<String> login(@RequestBody User credentials) {
        var token = userService.login(credentials);
        if ( token.isEmpty() )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(token.get(), HttpStatus.OK);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        if (userService.logout(auth) )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        var user = userService.getAuthenticatedUserByAuthHeader(auth);
        if ( user == null )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        log.info( user.toString() );
        if ( !user.isRoleFleetManager() )
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser( @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                            @RequestBody User updatedUser, @PathVariable Long id) {
        var user = userService.getAuthenticatedUserByAuthHeader(auth);
        if ( user == null )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if ( !user.isRoleFleetManager() && user.getId()!=id )
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        updatedUser.setId( id );    // just to be sure
        userService.updateUser( updatedUser );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
