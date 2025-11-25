package com.prokofeva.dbuserpaservice.service.impl;

import com.prokofeva.dbuserpaservice.dto.OwnerDto;
import com.prokofeva.dbuserpaservice.dto.UserPADto;
import com.prokofeva.dbuserpaservice.mapper.MapperUserPA;
import com.prokofeva.dbuserpaservice.repository.UserPARepository;
import com.prokofeva.dbuserpaservice.service.UserPAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.prokofeva.dbuserpaservice.mapper.MapperUserPA.toDto;

@Service
@RequiredArgsConstructor
public class UserPAServiceImpl implements UserPAService {
    private final UserPARepository userPARepository;

    @Override
    public UserPADto findUserPAByIdTg(Long userId) {
        var userDb = userPARepository.findUserPAByIdTg(userId);
        return userDb == null ? null : toDto(userDb);
    }

    @Override
    public UserPADto save(UserPADto userPADto) {
        return toDto(userPARepository.save(MapperUserPA.toEntity(userPADto)));
    }

    @Override
    public List<OwnerDto> findUserPAById(List<String> userIds) {
        return userPARepository.findUserPABy_idIn(userIds).stream()
                .map(u -> new  OwnerDto(u.get_id(), u.getNameForReport()))
                .toList();
    }

    @Override
    public boolean existsUserTg(long idTg) {
        return userPARepository.existsUserPAByIdTg(idTg);
    }

}
