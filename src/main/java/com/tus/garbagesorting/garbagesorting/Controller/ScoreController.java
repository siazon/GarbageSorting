package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Mapper.ScoreMapper;
import com.tus.garbagesorting.garbagesorting.Model.Score;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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



//    @PostMapping("insertScore") {
//
//    }

}
