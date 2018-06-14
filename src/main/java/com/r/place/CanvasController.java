package com.r.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/canvas")
public class CanvasController {
    @Autowired
    private CanvasRepository canvasRepository;

    @GetMapping(path="/add")
    public @ResponseBody
    String greeting(@RequestParam(name="width", required=false, defaultValue="1024") String width,
                    @RequestParam(name="height", required=false, defaultValue="1024") String height, Model model) {
        Canvas n = new Canvas(Integer.parseInt(width), Integer.parseInt(height));
        canvasRepository.save(n);
        return "place";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Canvas> getAllCanvases() {
        // This returns a JSON or XML with the users
        return canvasRepository.findAll();
    }

}