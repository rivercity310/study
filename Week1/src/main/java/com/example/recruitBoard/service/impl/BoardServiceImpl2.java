package com.example.recruitBoard.service.impl;

import com.example.recruitBoard.repository.BoardMapperRepository;
import com.example.recruitBoard.service.BoardService;
import com.example.recruitBoard.vo.BoardVO;
import com.example.recruitBoard.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("boardService2")
public class BoardServiceImpl2 implements BoardService {
    private final BoardMapperRepository boardMapperRepository;

    /* [ Refactor ]
    * - boardList: DB 접속을 최소화시키기 위한 리스트, DB에서 받아온 전체 리스트를 저장한다.
    * - isUpdate:
            - false: DB 업데이트가 이뤄지지 않았으므로 그대로 boardList 이용
            - true: 새로 리스트를 받아와서 boardList에 저장, 기존 데이터 삭제 (insert, update, delete, apply -> true)
    */
    private static List<BoardVO> boardList;
    private static boolean isUpdated;


    public BoardServiceImpl2(BoardMapperRepository boardMapperRepository) {
        this.boardMapperRepository = boardMapperRepository;
        this.isUpdated = false;
        renewBoardList();      /* 최초 생성시 전체 목록을 가져옴 */
    }

    private void renewBoardList() {
        isUpdated = false;
        boardList = boardMapperRepository.getBoardList()
                .stream()
                .map(it -> setApplicantsToBoard(setCompanyToBoard(setOthersToBoard(it))))
                .sorted(Comparator.comparing(BoardVO::getSeq).reversed())
                .collect(Collectors.toList());
    }

    /* [ Fix ]
    * 쿼리문에서 ORDER BY seq DESC ---> 성능 이슈
    * 받아온 뒤에 여기서 역순정렬
    */
    @NotNull
    @Override
    public List<BoardVO> getBoardList() {
        if (isUpdated) renewBoardList();
        return boardList;
    }

    @NotNull
    @Override
    public List<BoardVO> searchBoard(@Nullable String searchKeyword) {
        if (isUpdated) renewBoardList();

        return boardList
                .stream()
                .filter(it ->
                        (it.getTech() + it.getPosition() + it.getContent() + it.getCompany())
                                .contains(searchKeyword))
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public BoardVO getBoard(int seq) {
        /* DB 상태가 업데이트 되었다면 DB 접속 -> 전체 리스트를 받아오는게 아니므로 isUpdate 그대로 유지 */
        if (isUpdated) {
            BoardVO board = boardMapperRepository.getBoard(seq);
            setCompanyToBoard(board);
            setOthersToBoard(board);
            setApplicantsToBoard(board);

            return board;
        }

        return boardList
                .stream()
                .filter(board -> board.getSeq() == seq)
                .findAny()
                .get();
    }

    @Override
    public void applyBoard(@NotNull BoardVO boardVO, @NotNull UserVO userVO) {
        if (duplicateCheck(boardVO.getSeq(), userVO.getUserId()) != 0) {
            System.out.println("이미 지원하셨습니다");
            return;
        }

        isUpdated = true;
        boardMapperRepository.applyBoard(
                boardVO.getSeq(),
                userVO.getUserId(),
                userVO.getUserName(),
                userVO.getUserAge()
        );
    }

    @Override
    public int insertBoard(BoardVO vo) {
        isUpdated = true;
        return boardMapperRepository.insertBoard(vo);
    }

    @Override
    public int deleteBoard(int seq) {
        isUpdated = true;
        return boardMapperRepository.deleteBoard(seq);
    }

    @Override
    public int updateBoard(@NotNull BoardVO vo) {
        isUpdated = true;
        return boardMapperRepository.updateBoard(vo);
    }



    /* 이 아래부터 유틸 메소드 */
    private int duplicateCheck(int boardSeq, String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("boardSeq", boardSeq);
        map.put("userId", userId);
        return boardMapperRepository.duplicateCheck(map);
    }

    private BoardVO setCompanyToBoard(BoardVO board) {
        board.setCompany(boardMapperRepository.getCompany(board.getId()));
        return board;
    }

    private BoardVO setApplicantsToBoard(BoardVO board) {
        board.setApplicants(boardMapperRepository.getApplicants(board.getSeq()));
        return board;
    }

    private BoardVO setOthersToBoard(BoardVO board) {
        board.setOthers(boardMapperRepository.getOtherBoard(board.getSeq(), board.getId()));
        return board;
    }
}
