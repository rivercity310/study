package com.example.assignment1.board

interface BoardService {
    fun insertBoard(vo: BoardVO)
    fun deleteBoard(vo: BoardVO)
    fun updateBoard(vo: BoardVO)
    fun getBoard(vo: BoardVO): BoardVO?
    fun getBoardContentList(vo: BoardVO): List<BoardVO>?
    fun getBoardList(vo: BoardVO?): List<BoardVO>?
}