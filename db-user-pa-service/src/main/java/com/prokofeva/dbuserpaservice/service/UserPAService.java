package com.prokofeva.dbuserpaservice.service;

import com.prokofeva.dbuserpaservice.dto.OwnerDto;
import com.prokofeva.dbuserpaservice.dto.UserPADto;

import java.util.List;

public interface UserPAService {
    UserPADto findUserPAByIdTg(Long id);

    UserPADto save(UserPADto userPADto);

    List<OwnerDto> findUserPAById(List<String> strings);

    boolean existsUserTg(long idTg);
}
