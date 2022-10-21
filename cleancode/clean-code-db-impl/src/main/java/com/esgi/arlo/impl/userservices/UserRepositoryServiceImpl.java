package com.esgi.arlo.impl.userservices;

import com.esgi.arlo.entities.users.UsersEntity;
import com.esgi.arlo.generators.UUIDGenerator;
import com.esgi.arlo.interfaces.userservices.UserService;
import com.esgi.arlo.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    /**
     * @param userBusinessReference a user unique function identifier
     * @return an optional of a user
     */
    @Override
    public Optional<UsersEntity> findOneUserByUserTechnicalId(String userBusinessReference) {
        return Optional.ofNullable(userRepository.findByUserReference(userBusinessReference));
    }

    /**
     * @param userToSave the user (existing or not according to his functional ref) to create or update
     * @return a user
     */
    @Override
    public UsersEntity saveUser(UsersEntity userToSave) {
        if(userToSave.getUserReference() == null){
            userToSave.setUserReference(UUIDGenerator.generateUUIDWithoutUnionTrails());
        }
        return userRepository.save(userToSave);
    }
}
