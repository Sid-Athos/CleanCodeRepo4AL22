package cleancodetests.ut.impl.user;

import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.generators.UUIDGenerator;
import com.cleancode.cleancodedbimpl.impl.userservices.UserRepositoryServiceImpl;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
public class UserRepositoryServiceImplIT {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryServiceImplIT.class.getName());
    @InjectMocks
    private UserRepositoryServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldCreateNewUserAndGenerateClientReference(){
        LOGGER.log(Level.INFO,"Starting test shouldCreateNewUserAndGenerateClientReference");

        // PREPARE
         UsersEntity usersEntityToSave = new UsersEntity();
         UsersEntity usersEntityToReturn = new UsersEntity();
         usersEntityToReturn.setId(1L);
         usersEntityToReturn.setUserReference(UUIDGenerator.generateUUIDWithoutUnionTrails());

         // ACT
         Mockito.when(userRepository.save(usersEntityToSave)).thenReturn(usersEntityToReturn);
         usersEntityToSave = userService.saveUser(usersEntityToSave);

         // CHECK
        Mockito.verify(userRepository, Mockito.atMostOnce()).save(usersEntityToSave);
        Assertions.assertNotNull(usersEntityToSave.getUserReference());
        LOGGER.log(Level.INFO,"Starting test shouldCreateNewUserAndGenerateClientReference");
    }
}
