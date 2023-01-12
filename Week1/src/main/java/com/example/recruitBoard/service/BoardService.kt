package com.example.recruitBoard.service

import com.example.recruitBoard.vo.BoardVO
import com.example.recruitBoard.vo.UserVO


interface BoardService {
    fun getBoardList(): List<BoardVO>
    fun getBoard(seq: Int): BoardVO
    fun insertBoard(vo: BoardVO): Int
    fun deleteBoard(seq: Int): Int
    fun updateBoard(vo: BoardVO): Int
    fun searchBoard(searchKeyword: String? = null): List<BoardVO>
    fun applyBoard(boardVO: BoardVO, userVO: UserVO)
}