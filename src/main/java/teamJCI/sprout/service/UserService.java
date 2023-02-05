package teamJCI.sprout.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamJCI.sprout.domain.User;
import teamJCI.sprout.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     * @param user
     */
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByUsername(user.getUsername());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId);
    }

}
