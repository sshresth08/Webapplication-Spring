package de.thk.rwoerzbe.toolbox.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller that just serves the main page of Toobox
 */
@Controller
public class MainController {

    /**
     * Serves the main page of Toolbox. Is triggered
     * by a <code>GET /</code> request
     * 
     * @return <code>"main"</code>
     */
    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

}
