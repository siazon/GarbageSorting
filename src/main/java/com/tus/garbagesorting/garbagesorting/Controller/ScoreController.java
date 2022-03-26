package com.tus.garbagesorting.garbagesorting.Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.tus.garbagesorting.garbagesorting.Common.JwtTokenUtil;
import com.tus.garbagesorting.garbagesorting.Mapper.ScoreMapper;
import com.tus.garbagesorting.garbagesorting.Model.Score;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * This class contains all REST endpoints to create, read and update the user scores
 * */

@CrossOrigin
@RestController
public class ScoreController {

    @Autowired
    private ScoreMapper scoreMapper;

    // Rank users by getting scores in descending order
    @GetMapping("/allScores")
    public ResponseEntity<List<Score>> findAllScores() {
        List<Score> list = scoreMapper.findAllScores();
        return new ResponseEntity<List<Score>>(list, HttpStatus.OK);
    }

    // find score of one user by id
    @GetMapping("/findById/{id}")
    public Score findById(@PathVariable int id) throws NotFoundException {
        return scoreMapper.findScoreById(id);
    }

    // insert score
    @PostMapping("/insertScore")
    public Object insertScore(@RequestBody Score score, @RequestHeader Map<String, String> headers) {
        String token = headers.get("token");
        DecodedJWT jwt = JwtTokenUtil.getTokenInfo(token);
        String user_id = jwt.getClaim("id").asString();
        score.userId = Integer.parseInt(user_id);
        scoreMapper.insertScore(score);
        return score;
    }

    // update score
    @PutMapping("/updateScore")
    public ResponseEntity<String> updateScore(@RequestBody Score score) {
        scoreMapper.updateScore(score);
        return new ResponseEntity<>("Score updated", HttpStatus.NOT_FOUND);
    }

}
