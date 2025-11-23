package com.prokofeva.tgbotplanny.repository;

import com.prokofeva.tgbotplanny.entity.UserPA;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPARepository extends MongoRepository<UserPA, String> {

    UserPA findUserPAByIdTg(Long id);
}
