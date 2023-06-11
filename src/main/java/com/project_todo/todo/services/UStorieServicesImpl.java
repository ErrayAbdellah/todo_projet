package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.UserStoriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UStorieServicesImpl implements IUStoriesServices{
    private final IUStoriesServices repo ;

    @Override
    public List<UserStoriesDto> getSprints() {
        return null;
    }

    @Override
    public ResponseEntity add(HttpServletRequest request, UserStoriesDto userStoriesDto) {
        return null;
    }

    @Override
    public ResponseEntity update(HttpServletRequest request, UserStoriesDto userStoriesDto, long id) {
        return null;
    }

    @Override
    public ResponseEntity delete(HttpServletRequest request, long id) {
        return null;
    }
}
