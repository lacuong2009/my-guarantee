package users.service;

import users.domain.entity.User;
import users.domain.model.UserModel;
import users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public UserModel getUserById(UUID id) {
        return userRepository.findById(id)
                .map(this::mapToModel)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserModel createUser(User user) {
        User saved = userRepository.save(user);
        return mapToModel(saved);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    private UserModel mapToModel(User user) {
        return new UserModel(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
