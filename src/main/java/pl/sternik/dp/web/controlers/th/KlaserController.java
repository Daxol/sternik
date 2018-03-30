package pl.sternik.dp.web.controlers.th;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sternik.dp.entities.Book;
import pl.sternik.dp.services.LibraryService;
import pl.sternik.dp.services.NotificationService;

import java.util.List;


@Controller
public class KlaserController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private NotificationService notificationService;


    @ModelAttribute("allBooks")
    public List<Book> populateCoins() {
        return this.libraryService.findAll();
    }

    @ModelAttribute("toSell")
    public List<Book> populateCoinsToSell() {
        return this.libraryService.findAllToSell();
    }

//    @ModelAttribute("coinsLast3")
//    public List<Moneta> populateLast3Coins() {
//        return this.klaserService.findLatest3();
//    }

//    @RequestMapping({ "/", "/index" })
//    public String index(Model model) {
//        return "th/index";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages", notificationService.getNotificationMessages());
        return "th/klaser";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "th/tosell";
    }

}
