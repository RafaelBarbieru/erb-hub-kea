package com.anpora.erbhub.controllers.api;

import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.services.BattleService;
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
 * Controller class for battles
 */
@RestController
public class BattleController {

    // Dependencies
    private Environment env;
    private BattleService battleService;

    // Constructor injection
    @Autowired
    public BattleController(
            Environment env,
            BattleService battleService) {
        this.env = env;
        this.battleService = battleService;
    }

    // Endpoints
    @GetMapping("/api/battles")
    @ResponseBody
    public ResponseEntity<List<BattleDTO>> getAllBattles() throws Exception {
        return new ResponseEntity<>(battleService.getAllBattles(), HttpStatus.OK);
    }

    @GetMapping("/api/battle/{id}")
    public ResponseEntity<BattleDTO> getBattle(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(battleService.getBattleById(id), HttpStatus.OK);
    }

//    @PostMapping("/battle/add")
//    public void addBattle(@RequestBody BattleEntity battleEntity) {
//        if (battleEntity != null) {
//            battlesService.save(battleEntity);
//        }
//    }

}
