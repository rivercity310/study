package com.springbook.biz.mvc.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("board")
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @ModelAttribute("conditionMap")
    public Map<String, String> searchConditionMap() {
        Map<String, String> conditionMap = new HashMap<>();
        conditionMap.put("제목", "TITLE");
        conditionMap.put("내용", "CONTENT");
        return conditionMap;
    }

    @RequestMapping("/deleteBoard.do")
    public String deleteBoard(BoardVO vo) {
        boardService.deleteBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/getBoard.do")
    public String getBoard(BoardVO vo, Model model) {
        model.addAttribute("board", boardService.getBoard(vo));
        return "getBoard.jsp";
    }

    @RequestMapping("/getBoardList.do")
    public String getBoardList(BoardVO vo, Model model) {
        if (vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
        if (vo.getSearchKeyword() == null) vo.setSearchKeyword("");
        model.addAttribute("boardList", boardService.getBoardList(vo));
        return "getBoardList.jsp";
    }

    @RequestMapping(value="/insertBoard.do")
    public String insertBoard(BoardVO vo) throws IOException {
        // 파일 업로드 처리
        MultipartFile uploadFile = vo.getUploadFile();
        if (!uploadFile.isEmpty()) {
            String fileName = uploadFile.getOriginalFilename();
            uploadFile.transferTo(new File(fileName));
        }

        boardService.insertBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute("board") BoardVO vo) {
        boardService.updateBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/dataTransform.do")
    @ResponseBody
    public List<BoardVO> dataTransform(BoardVO vo) {
        vo.setSearchCondition("TITLE");
        vo.setSearchKeyword("");
        List<BoardVO> boardList = boardService.getBoardList(vo);
        return boardList;
    }
}
