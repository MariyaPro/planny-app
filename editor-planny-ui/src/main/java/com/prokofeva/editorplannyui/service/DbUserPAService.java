package com.prokofeva.editorplannyui.service;

import com.prokofeva.editorplannyui.dto.OwnerDto;

import java.util.List;

public interface DbUserPAService {

    List<OwnerDto> getOwnersList(long userIdTg);
}
