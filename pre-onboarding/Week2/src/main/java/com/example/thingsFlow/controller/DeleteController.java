package com.example.thingsFlow.controller;

import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.service.DeleteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = {"API"}, value = "게시글 삭제 API 정보를 제공하는 Controller")
public class DeleteController {
    private final DeleteService deleteService;

    @DeleteMapping("/board/{id}")
    @ApiOperation(value = "delete 기능 API", notes = "BCrypt 함수로 비밀번호를 대조하고 일치시 데이터 삭제")
    public Board delete(@PathVariable("id") long id, @RequestBody Map<String, Object> map) {
        return deleteService.delete(id, map);
    }
}

