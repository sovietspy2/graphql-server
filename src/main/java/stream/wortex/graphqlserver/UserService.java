package stream.wortex.graphqlserver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
