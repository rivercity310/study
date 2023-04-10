package com.example.thingsFlow.controller;

import com.example.thingsFlow.dto.UpdateDTO;
import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.service.UpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Api(tags = {"API"}, value = "게시글 업데이트 API 정보를 제공하는 Controller")
public class UpdateController {
    private UpdateService updateService;

    @GetMapping("/board/{id}")
    public Long validatePassword(@PathVariable("id") long id, @RequestBody Map<String,Object> map) {
        return updateService.validatePassword(id, map);
    }

    @PutMapping ("/post")
    @ApiOperation(value = "update 기능 API", notes = "BCrypt 함수로 비밀번호를 대조하고 일치시 데이터를 변경")
    public Board update(@RequestBody UpdateDTO updateDTO) {
        return updateService.updateBoard(updateDTO);
    }
}
