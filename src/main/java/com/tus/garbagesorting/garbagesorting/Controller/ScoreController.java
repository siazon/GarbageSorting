package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Mapper.ScoreMapper;
import com.tus.garbagesorting.garbagesorting.Model.Score;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ScoreController {

    @Autowired
    private ScoreMapper scoreMapper;

    @GetMapping("/allScores")
    public ResponseEntity<List<Score>> findAllScores() {
        List<Score> list = scoreMapper.findAllScores();
        return new ResponseEntity<List<Score>>(list, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public Score findById(@PathVariable int id) throws NotFoundException {
       return scoreMapper.findScoreById(id);
    }


    @PostMapping("/insertScore")
    public Object insertScore(@RequestBody Score score) {
            scoreMapper.insertScore(score);
            return score;
    }

    @PutMapping("/updateScore")
    public ResponseEntity<String> updateScore(@RequestBody Score score) {
        scoreMapper.updateScore(score);
        return new ResponseEntity<>("Score updated", HttpStatus.NOT_FOUND);
    }

}