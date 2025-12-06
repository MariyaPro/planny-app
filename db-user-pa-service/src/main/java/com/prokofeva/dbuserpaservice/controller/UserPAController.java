package com.prokofeva.dbuserpaservice.controller;

import com.prokofeva.dbuserpaservice.dto.UserPADto;
import com.prokofeva.dbuserpaservice.facade.UserPAFacade;
import com.prokofeva.dbuserpaservice.util.LogRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/db-user-pa")
public class UserPAController {
    private final UserPAFacade userPAFacade;

    @PostMapping("/")
    @LogRequest
    public ResponseEntity<Object> createUserPA(@RequestBody @Valid UserPADto userPADto) {
        userPAFacade.createUserPA(userPADto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @LogRequest
    public ResponseEntity<Object> existsUserTG(@RequestParam("tg") long idTg) {
        return ResponseEntity.ok(userPAFacade.existsUserTG(idTg));
    }

    @GetMapping("/{idtg}")
    @LogRequest
    public ResponseEntity<Object> getUserPA(@PathVariable("idtg") long userIdTg) {
        return ResponseEntity.ok(userPAFacade.getUserPA(userIdTg));
    }

    @GetMapping("/{idtg}/subscriptions")
    @LogRequest
    public ResponseEntity<Object> getUserPASubscriptions(@PathVariable("idtg") long userIdTg) {
       return ResponseEntity.ok(userPAFacade.getUserPASubscriptions(userIdTg)) ;
    }

    @GetMapping("/demo/subscriptions")
    @LogRequest
    public ResponseEntity<Object> getDemoSubscriptions() {
        return ResponseEntity.ok(userPAFacade.getDemoSubscriptions()) ;
    }

}
