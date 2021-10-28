package com.anpora.erbhub.controllers;

import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.services.BattlesService;
import com.anpora.erbhub.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Controller for all the views in the application
 */
@Controller
public class WebController {

    // Dependencies
    private BattlesService battlesService;
    private CommonUtils commonUtils;

    // Constructor injection
    @Autowired
    public WebController(
            BattlesService battlesService,
            CommonUtils commonUtils) {
        this.battlesService = battlesService;
        this.commonUtils = commonUtils;
    }

    // Endpoints
    @RequestMapping("/")
    public String index(Model model) throws Exception {
        BattleDTO battle = battlesService.getBattleById(1L);
        model.addAttribute("battle", battle);
        return "index";
    }

}
