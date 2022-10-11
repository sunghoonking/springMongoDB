package com.example.springmongodb;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/board/{id}")
    public Board findById(@PathVariable String id) {
        return boardRepository.findById(id).get();
    }

    @GetMapping("/board")
    public List<Board> findAll() { //
        return boardRepository.findAll();
    }

    @GetMapping("/boards")
    public List<Board> findAlla(@RequestParam String title) { //
        List<Board> byTitle = boardRepository.findByTitle(title);
        return byTitle;

    }

    @PostMapping("/board")
    public Board save(@RequestBody BoardSaveDto dto) {
        Board boardEntity = boardRepository.save(dto.toEntity());

        return boardEntity; //
    }

    @PutMapping("board/{id}")
    public void update(@RequestBody BoardSaveDto dto, @PathVariable String id) {

        Board board = dto.toEntity();
        board.set_id(id); // save함수는 같은 아이디면 수정한다.

        boardRepository.save(board);
    }

    @DeleteMapping("board/{id}")
    public int deleteById(@PathVariable String id) {

        boardRepository.deleteById(id); // 내부적으로 실행되다가 오류 Exception 발동

        return 1; // 1 : 성공, -1 : 실패
    }





}


