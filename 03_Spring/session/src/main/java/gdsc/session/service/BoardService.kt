package gdsc.session.service

import gdsc.session.entity.Board
import gdsc.session.repository.BoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BoardService(@Autowired val boardRepository: BoardRepository) {
    fun write(board: Board): Board = boardRepository.save(board)
    fun boardList(): List<Board> = boardRepository.findAll()
    fun boardView(id: Int) = boardRepository.findById(id).get()
    fun boardDelete(id: Int) = boardRepository.deleteById(id)
}