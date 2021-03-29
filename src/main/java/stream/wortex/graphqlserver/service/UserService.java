package stream.wortex.graphqlserver.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stream.wortex.graphqlserver.model.User;
import stream.wortex.graphqlserver.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUserAddress(Integer userId, String address) throws Exception {
        try {
            userRepository.updateUserAddress(address, userId);
            User user = userRepository.findById(userId).get();
            return user;
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    public User createUser(String firstName, String lastName, Date dob, String address, Integer postId) {

        User user = new User();

        user.setFirstName(firstName);
        user.setAddress(address);
        user.setDob(dob);
        user.setPostId(postId);
        user.setLastName(lastName);

        userRepository.save(user);

        return user;


    }

}
