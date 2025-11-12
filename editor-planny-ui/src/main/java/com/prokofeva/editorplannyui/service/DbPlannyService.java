package com.prokofeva.editorplannyui.service;

import com.prokofeva.editorplannyui.dto.EventForm;
import com.prokofeva.editorplannyui.dto.OwnerDto;

import java.util.List;

public interface DbPlannyService {

    void save(EventForm eventForm);

    List<OwnerDto> getOwnersList(boolean isDemo);
}
