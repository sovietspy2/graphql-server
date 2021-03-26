package stream.wortex.graphqlserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataLoader {

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void loadData(){

        User user1 = new User("Yasas" ,"Sandeepa",DataLoader.getRandomDate(),"Mount Pleasant Estate Galle",1);
        User user2 = new User("Sahan" ,"Rambukkna",DataLoader.getRandomDate(),"Delkanda Nugegoda",2);
        User user3 = new User("Ranuk" ,"Silva",DataLoader.getRandomDate(),"Yalawatta gampaha",3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }


    public static Date getRandomDate(){
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
