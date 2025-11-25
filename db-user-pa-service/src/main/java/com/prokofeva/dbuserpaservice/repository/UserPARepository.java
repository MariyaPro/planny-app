package com.prokofeva.dbuserpaservice.repository;

import com.prokofeva.dbuserpaservice.entity.UserPA;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserPARepository extends MongoRepository<UserPA, String> {

    UserPA findUserPAByIdTg(Long id);

    List<UserPA> findUserPABy_idIn(List<String> userIds);

    boolean existsUserPAByIdTg(long idTg);
}
