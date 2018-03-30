package pl.sternik.dp.web.controlers.th;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sternik.dp.repositories.BookRepository;

import java.util.Date;


@Controller
public class WprawkiControllerTh {

//    @Autowired
//    @Qualifier("myDatabase")
//    BookRepository baza;

    @RequestMapping(path = "/wprawki-th", method = RequestMethod.GET)
    public String wprawki(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "th/wprawki";
    }

    @GetMapping("/wprawki-th/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "th/wprawki";
    }

    @GetMapping("/wprawki-th2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "Wprawki z param cos=" + cosParam;
    }

        @GetMapping("/wprawki-th3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "Uzywasz przegladarki:=" + cosParam;
    }
//
//    @GetMapping(value = "/wprawki-th/monety/{id}/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<Book> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
//
//            return new ResponseEntity<Book>(null, HttpStatus.CREATED);
//        }
//    }

}
