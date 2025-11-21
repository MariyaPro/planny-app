package com.prokofeva.tgbotplanny.service;

import com.prokofeva.tgbotplanny.dto.UserPADto;
import com.prokofeva.tgbotplanny.entity.UserPA;

public interface UserPAService {
    UserPADto findUserPAByIdTg(Long id);

    UserPADto save(UserPA userPA);
}
