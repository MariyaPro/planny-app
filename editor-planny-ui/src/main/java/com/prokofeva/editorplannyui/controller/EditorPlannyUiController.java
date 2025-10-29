package com.prokofeva.editorplannyui.controller;

import com.prokofeva.editorplannyui.dto.EventDto;
import com.prokofeva.editorplannyui.service.EventService;
import com.prokofeva.editorplannyui.util.LogRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/planny")
@RequiredArgsConstructor
@Slf4j
public class EditorPlannyUiController {
    private final EventService eventService;

    @GetMapping("/")
    @LogRequest(logResult = false)
    public String getMenu() {
        return "main";
    }

    @GetMapping("/events/new")
    @LogRequest(logResult = false)
    public String showCreateForm(Model model) {
        model.addAttribute("eventDto", new EventDto());
        return "events-new";
    }

    @PostMapping("/events/new")
    @LogRequest
    public String createEvent(@ModelAttribute("eventDto") EventDto eventDto,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Пожалуйста, исправьте ошибки в форме");
            return "events-new";
        }
        try {
            eventService.save(eventDto);
            model.addAttribute("successMessage", "Событие успешно создано!");
            return "redirect:/events?success=true";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ошибка при создании события: " + e.getMessage());
            model.addAttribute("eventDto", eventDto);
            return "events-new";
        }
    }

}
