package com.anpora.erbhub.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Controller for all the views in the application
 */
public class WebController {

    // Endpoints
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
