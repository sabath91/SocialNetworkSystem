package pl.czyz.springbootmongo.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.czyz.springbootmongo.domain.UserNode;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UsersNodeRepositoryTest {

    @Autowired
    private UsersNodeRepository usersNodeRepository;

    @Before
    public void setUp() {
        usersNodeRepository.deleteAll();
    }

    @Test
    public void findByLogin() {
        UserNode userNode = new UserNode("Login");
        usersNodeRepository.save(userNode);

        UserNode receivedUser = usersNodeRepository.findByLogin("Login");

        assertEquals(userNode, receivedUser);

    }
}