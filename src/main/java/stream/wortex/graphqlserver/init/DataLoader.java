package stream.wortex.graphqlserver.init;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stream.wortex.graphqlserver.CommentPublisher;
import stream.wortex.graphqlserver.model.Comment;
import stream.wortex.graphqlserver.model.User;
import stream.wortex.graphqlserver.repository.CommentRepository;
import stream.wortex.graphqlserver.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

@Slf4j
@Service
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    Faker faker = new Faker();

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private CommentPublisher commentPublisher;

    private void addNewComments() {
        try {
            Comment comment = new Comment(faker.number().randomDigit(), faker.number().numberBetween(100, 200), faker.number().randomDigit(), faker.lebowski().quote());
            //log.info("Adding comment!");
            commentPublisher.publish(comment);
            //commentRepository.save(comment);
            //log.info("Added new comment!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    @PostConstruct
    public void loadData() {

        log.info("init data loaded");

        User user1 = new User("Yasas", "Sandeepa", DataLoader.getRandomDate(), "Mount Pleasant Estate Galle", 1);
        User user2 = new User("Sahan", "Rambukkna", DataLoader.getRandomDate(), "Delkanda Nugegoda", 2);
        User user3 = new User("Ranuk", "Silva", DataLoader.getRandomDate(), "Yalawatta gampaha", 3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        executor.scheduleAtFixedRate(
                this::addNewComments,
                0,
                5,
                TimeUnit.SECONDS);
    }


    public static Date getRandomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1990);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 2);
        Date date1 = calendar.getTime();
        calendar.set(Calendar.YEAR, 1996);
        Date date2 = calendar.getTime();
        long startMillis = date1.getTime();
        long endMillis = date2.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

}
