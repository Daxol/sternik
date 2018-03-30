package pl.sternik.dp.web.controlers.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sternik.dp.entities.Moneta;
import pl.sternik.dp.repositories.MYMoneRespo;

import java.util.Date;


@Controller
public class WprawkiControllerJsp {

    @Autowired
    private MYMoneRespo repo;

    @RequestMapping(path = "/wprawki-jsp", method = RequestMethod.GET)
    public String wprawki(ModelMap model) {
        Moneta moneta = new Moneta();
        moneta.setKrajPochodzenia("PL");
        repo.save(moneta);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "jsp/wprawki";
    }

    @GetMapping("/wprawki-jsp/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "jsp/wprawki";
    }

    @GetMapping("/wprawki-jsp2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "Wprawki z param cos=" + cosParam;
    }

    @GetMapping("/wprawki-jsp3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "Uzywasz przegladarki:=" + cosParam;
    }
}
