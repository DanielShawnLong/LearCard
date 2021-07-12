package htwsaar.ariadne.Backend.controller;

import htwsaar.ariadne.Backend.entity.Lernkartei;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LernkarteiController {

    @RequestMapping (
            method = RequestMethod.GET,
            path = "/getLernkartei",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Lernkartei getLernkartei() {
        Lernkartei lk = new Lernkartei("Das ist ein Test!");

        return lk;
    }

}
