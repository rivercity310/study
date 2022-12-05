package gdsc.session.service

import gdsc.session.entity.Board
import gdsc.session.repository.BoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BoardService(@Autowired val boardRepository: BoardRepository) {
    fun write(board: Board): Board = boardRepository.save(board)
    fun boardList(pageable: Pageable): Page<Board> = boardRepository.findAll(pageable)
    fun boardView(id: Int) = boardRepository.findById(id).get()

    fun boardSearchList(searchKeyword: String, pageable: Pageable): Page<Board> =
        boardRepository.findByTitleContaining(searchKeyword, pageable)
    fun boardDelete(id: Int) = boardRepository.deleteById(id)
}