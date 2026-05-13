package at.fhtw.bic.carsharing.persistence.repository;

import at.fhtw.bic.carsharing.persistence.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private static long nextId = 1L;
    public static long nextId() { return nextId++; }


    private final Map<Long, User> users;


    public UserRepository() {
        users = new HashMap<>();
    }


    // CRUD Operations:
    public User save(User user) {
        if (user.getId()==null)
            user.setId( nextId() );
        users.put( user.getId(), user );
        return user;
    }

    public List<User> findAll() {
        return users.values().stream().toList();
    }

    public Optional<User> findById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> findByUsername(String username) {
        for ( User user : users.values() ) {
            if ( user.getUsername().equalsIgnoreCase( username ) )
                return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<User> findByToken(String s) {
        for ( User user : users.values() ) {
            if (Strings.isNotBlank( user.getToken()) && user.getToken().equals(s) )
                return Optional.of(user);
        }
        return Optional.empty();
    }


    public void deleteById(long id) {
        users.remove(id);
    }

}
