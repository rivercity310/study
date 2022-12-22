package com.example.assignment1.board.impl

import com.example.assignment1.board.BoardService
import com.example.assignment1.board.BoardVO
import org.springframework.stereotype.Service

@Service
open class BoardServiceImpl(private val boardDAO: BoardDAO): BoardService {
    override fun insertBoard(vo: BoardVO) = boardDAO.insertBoard(vo)
    override fun deleteBoard(vo: BoardVO) = boardDAO.deleteBoard(vo)
    override fun updateBoard(vo: BoardVO) = boardDAO.updateBoard(vo)
    override fun getBoard(vo: BoardVO): BoardVO? = boardDAO.getBoard(vo)
    override fun getBoardContentList(vo: BoardVO): List<BoardVO>? = boardDAO.getBoardContentList(vo)
    override fun getBoardList(vo: BoardVO?): List<BoardVO>? = boardDAO.getBoardList(vo)
}