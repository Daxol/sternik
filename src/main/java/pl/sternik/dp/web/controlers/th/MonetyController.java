package pl.sternik.dp.web.controlers.th;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.dp.entities.Book;
import pl.sternik.dp.entities.Status;
import pl.sternik.dp.services.LibraryService;
import pl.sternik.dp.services.NotificationService;

@Controller
public class MonetyController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }
    
    @ModelAttribute("MyMessages")
    public List<NotificationService.NotificationMessage> populateMessages() {
        System.out.println("dupa");
        return notifyService.getNotificationMessages();
    }
    

    @GetMapping(value = "/monety/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Book> result;
        result = libraryService.findById(id);
        if (result.isPresent()) {
            Book book = result.get();
            model.addAttribute("moneta", book);
            return "th/moneta";
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return "redirect:/monety";
        }
    }

    @RequestMapping(value = "/monety/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Book> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Book> result;
        result = libraryService.findById(id);
        if (result.isPresent()) {
            Book book = result.get();
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/monety", params = { "save" }, method = RequestMethod.POST)
    public String saveMoneta(Book book, BindingResult bindingResult, ModelMap model) {
        // @Valid
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/moneta";
        }
        
        if (book.getStatus() == Status.NEW) {
            book.setStatus(Status.OLD);
        }
        
        Optional<Book> result = libraryService.edit(book);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety", params = { "create" }, method = RequestMethod.POST)
    public String createMoneta(Book book, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/moneta";
        }
        libraryService.create(book);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Book book, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = libraryService.deleteById(rowId.longValue());
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety/create", method = RequestMethod.GET)
    public String showMainPages(final Book book) {
        // Ustawiamy date nowej monety, na dole strony do dodania
        return "th/moneta";
    }
}