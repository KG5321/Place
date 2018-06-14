package com.r.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@Controller
@RequestMapping(path="/canvas")
public class CanvasController {
    @Autowired
    private CanvasRepository canvasRepository;

    @GetMapping(path="/add")
    public @ResponseBody
    String greeting(@RequestParam(name="width", required=false, defaultValue="5") String width,
                    @RequestParam(name="height", required=false, defaultValue="4") String height, Model model) {
        Canvas n = new Canvas(Integer.parseInt(width), Integer.parseInt(height));
        canvasRepository.save(n);
        return "added";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Canvas> getAllCanvases() {
        // This returns a JSON or XML with the users
        return canvasRepository.findAll();
    }

    @GetMapping(path="/debug")
    public @ResponseBody Canvas getLastCanvas() {
        // This returns a JSON or XML with the users
        Iterable<Canvas> canvases =  canvasRepository.findAll();
        final Iterator<Canvas> itr = canvases.iterator();
        Canvas lastElement = itr.next();

        while(itr.hasNext()) {
            lastElement=itr.next();
        }

        return lastElement;
    }

    @GetMapping(path="/other")
    public @ResponseBody Canvas test() {

        return getLastCanvas();
    }

    @GetMapping(path="/pixel")
    public @ResponseBody String pixel(@RequestParam(name="x") String x,
                                      @RequestParam(name="y") String y,
                                      @RequestParam(name="color") String color, Model model) {
        Canvas c = this.getLastCanvas();
        c.setPixel(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(color));
        canvasRepository.save(c);
        return "pixel saved";
    }

    @GetMapping(path="/show")
    public @ResponseBody String show() {
        Canvas c = this.getLastCanvas();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < c.getWidth(); x++) {
            for (int y = 0; y < c.getHeight(); y++) {
               sb.append(String.format("(%d), ", c.getPixel(x, y)));
            }
            sb.append("<br>");
        }

        return sb.toString();
    }
}