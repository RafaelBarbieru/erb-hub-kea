package com.anpora.erbhub.controllers;

import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.services.BattlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Controller class for /battles and /battle/
 */
@RestController
public class BattleController {

    @Autowired
    private Environment env;

    @Autowired
    private BattlesService battlesService;

    @GetMapping("/battles")
    @ResponseBody
    public ResponseEntity<List<BattleDTO>> getAllBattles() throws Exception {
        return new ResponseEntity<>(battlesService.getAllBattles(), HttpStatus.OK);
    }

    @GetMapping("/battle/{id}")
    public ResponseEntity<BattleDTO> getBattle(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(battlesService.getBattleById(id), HttpStatus.OK);
    }

//    @PostMapping("/battle/add")
//    public void addBattle(@RequestBody BattleEntity battleEntity) {
//        if (battleEntity != null) {
//            battlesService.save(battleEntity);
//        }
//    }

}
