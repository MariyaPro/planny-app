package com.prokofeva.tgbotplanny.service.impl;

import com.prokofeva.tgbotplanny.dto.UserPADto;
import com.prokofeva.tgbotplanny.entity.UserPA;
import com.prokofeva.tgbotplanny.repository.UserPARepository;
import com.prokofeva.tgbotplanny.service.UserPAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.prokofeva.tgbotplanny.mapper.MapperUserPA.toDto;

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
    public UserPADto save(UserPA userPA) {
        var userDb = userPARepository.save(userPA);
        return toDto(userDb);
    }

}
