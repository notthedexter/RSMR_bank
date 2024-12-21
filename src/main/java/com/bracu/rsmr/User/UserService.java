package com.bracu.rsmr.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        if (user.getRoles().contains("Customer")) {
            Account account = new Account(1000D, false, (long) ((Math.random() * 100) + 1));
            account.setUser(user);
            account.setActive(false);
            user.setAccount(account);
            accountService.createAccount(account);
        }
        return user;
    }

    public boolean addRole(User user, String role) {
        List<String> roles = user.getRoles();
        if (roles.contains(role))
            return false;
        user.getRoles().add(role);
        userRepository.save(user);
        return true;
    }

    public boolean removeRole(User user, String role) {
        List<String> roles = user.getRoles();
        if (!roles.contains(role))
            return false;
        user.getRoles().remove(role);
        userRepository.save(user);
        return true;
    }

    public boolean checkUser(User user) {
        Optional<User> dUser = userRepository.findByUsername(user.getUsername());
        if (dUser.isPresent())
            return bCryptPasswordEncoder.matches(user.getPassword(), dUser.get().getPassword());
        return false;
    }
}
