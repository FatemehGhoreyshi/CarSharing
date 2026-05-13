package at.fhtw.bic.carsharing.service;

import at.fhtw.bic.carsharing.persistence.entity.User;
import at.fhtw.bic.carsharing.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        String plainTextPassword = user.getPassword();
        if (Strings.isNotBlank( plainTextPassword ) )
        {
            user.setPasswordHash( passwordEncoder.encode(plainTextPassword) );
            user.setPassword( null );
        }
        user = userRepository.save(user);
        log.info("Created user {}", user);
        return user;
    }

    public Optional<String> login(User credentials) {
        // find user by credentials.username
        var loginUser = userRepository.findByUsername(credentials.getUsername() );
        if (loginUser.isEmpty())
            return Optional.empty();

        User user = loginUser.get();
        log.info("Login started by {}", user);

        // check password
        if (!passwordEncoder.matches( credentials.getPassword(), user.getPasswordHash() ) ) {
            log.warn("Login failed due to mismatching password!");
            return Optional.empty();
        }
        try {
            // generate token
            String token = generateToken( user.getUsername());
            user.setToken( token );
            userRepository.save(user);
            log.info("Login successful, token is {}", token);
            return Optional.of(user.getToken());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error( e.getMessage() );
            throw new UserServiceException( e );
        }
    }

    public boolean logout(String token) {
        var user = getAuthenticatedUserByAuthHeader(token);
        if ( user == null )
            return false;
        user.setToken( null );
        userRepository.save( user );
        log.info("Logout of {}", user);
        return true;
    }

    public User getAuthenticatedUserByAuthHeader(String auth) {
        if ( Strings.isBlank(auth) || !auth.startsWith("bearer ") )
            return null;
        var loginUser = userRepository.findByToken( auth.substring(7) );
        if ( loginUser.isEmpty() )
            return null;
        return loginUser.get();
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }



    private String generateToken( String username ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(username.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    public void updateUser(User updatedUser) {
        String plainTextPassword = updatedUser.getPassword();
        if (Strings.isNotBlank( plainTextPassword ) )
        {
            updatedUser.setPasswordHash( passwordEncoder.encode(plainTextPassword) );
            updatedUser.setPassword( null );
        }
        userRepository.save( updatedUser );
    }
}
