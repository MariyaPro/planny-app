package com.prokofeva.dbuserpaservice.facade;

import com.prokofeva.dbuserpaservice.dto.OwnerDto;
import com.prokofeva.dbuserpaservice.dto.UserPADto;
import com.prokofeva.dbuserpaservice.service.UserPAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPAFacade {
    private final UserPAService userPAService;

    public void createUserPA(UserPADto userPADto) {
       var userDb = userPAService.save(userPADto);
       userDb.subscriptionsId().add(userDb._id());
       userPAService.save(userDb);
    }

    public UserPADto getUserPA(long userId) {
        return userPAService.findUserPAByIdTg(userId);
    }

    public List<OwnerDto> getUserPASubscriptions(long userId) {
        var userPADto = userPAService.findUserPAByIdTg(userId);
        return userPAService.findUserPAById(userPADto.subscriptionsId());
    }

    public boolean existsUserTG(long idTg) {
        return userPAService.existsUserTg(idTg);
    }

    public List<OwnerDto> getDemoSubscriptions() {
        return List.of(
                new OwnerDto("test-01", "Саша Т."),
                new OwnerDto("test-02", "Паша Т."),
                new OwnerDto("test-03", "Даша Т.")
        );
    }
}
