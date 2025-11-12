package com.prokofeva.editorplannyui.controller;

import com.prokofeva.editorplannyui.dto.EventForm;
import com.prokofeva.editorplannyui.service.DbPlannyService;
import com.prokofeva.editorplannyui.util.LogRequest;
import com.prokofeva.editorplannyui.util.Util;
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
    private final DbPlannyService dbPlannyService;

    @GetMapping("/")
    @LogRequest(logResult = false)
    public String getMenu() {
        return "main";
    }

    @GetMapping("/events/new")
    @LogRequest(logResult = false)
    public String showCreateForm(Model model) {
        model.addAttribute("eventForm", EventForm.builder().build());
        model.addAttribute("owners", dbPlannyService.getOwnersList(true));
        return "events-new";
    }

    @PostMapping("/events/new")
//    @LogRequest
    public String createEvent(@ModelAttribute("eventForm") EventForm eventForm,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Пожалуйста, исправьте ошибки в форме");
            return "events-new";
        }
        try {
            log.info("Attempt to save a new event: {}", Util.toJson(eventForm));
            dbPlannyService.save(eventForm);
            model.addAttribute("successMessage", "Событие успешно создано!");
            return "redirect:/events?success=true";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ошибка при создании события: " + e.getMessage());
            model.addAttribute("eventForm", eventForm);
            return "events-new";
        }
    }

}
