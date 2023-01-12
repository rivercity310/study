package com.example.recruitBoard.repository;

import com.example.recruitBoard.vo.BoardVO;
import com.example.recruitBoard.vo.CompanyVO;
import com.example.recruitBoard.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardMapperRepository {
    int insertBoard(BoardVO vo);
    int deleteBoard(int seq);
    int updateBoard(BoardVO vo);
    int duplicateCheck(Map<String, Object> map);
    void applyBoard(int seq, String userId, String userName, int userAge);
    BoardVO getBoard(int seq);
    CompanyVO getCompany(int id);
    List<UserVO> getApplicants(int seq);
    List<BoardVO> getOtherBoard(int seq, int id);
    List<BoardVO> getBoardList();
}
