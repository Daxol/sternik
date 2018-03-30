package pl.sternik.dp.web.controlers.th;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sternik.dp.entities.Book;
import pl.sternik.dp.entities.Status;
import pl.sternik.dp.services.LibraryService;
import pl.sternik.dp.services.NotificationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

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
        return notifyService.getNotificationMessages();
    }


    @GetMapping(value = "/book/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Book> result;
        result = libraryService.findById(id);
        if (result.isPresent()) {
            Book book = result.get();
            model.addAttribute("book", book);
            return "th/book";
        } else {
            notifyService.addErrorMessage("Cannot find book #" + id);
            model.clear();

            return "redirect:/book";
        }
    }

    @RequestMapping(value = "/book/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Book> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Book> result;
        result = libraryService.findById(id);
        if (result.isPresent()) {
            Book book = result.get();
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find book #" + id);
            model.clear();
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/book", params = {"save"}, method = RequestMethod.POST)
    public String savebook(@Valid Book book, BindingResult bindingResult, ModelMap model, final HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages", notifyService.getNotificationMessages());
            return "th/book";
        }
        long rowId = Long.valueOf(req.getParameter("save"));
        book.setId(rowId);
        if (book.getStatus() == Status.NEW) {
            book.setStatus(Status.OLD);
        }

        Optional<Book> result = libraryService.edit(book);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();

        return "redirect:/";
    }

    @RequestMapping(value = "/book", params = {"create"}, method = RequestMethod.POST)
    public String createbook(@Valid Book book, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages", notifyService.getNotificationMessages());

            return "th/book";
        }
        libraryService.create(book);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");

        return "redirect:/";
    }

    @RequestMapping(value = "/book", params = {"remove"}, method = RequestMethod.POST)
    public String removeRow(final Book book, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = libraryService.deleteById(rowId.longValue());

        return "redirect:/";
    }

    @RequestMapping(value = "/book/create", method = RequestMethod.GET)
    public String showMainPages(final Book book) {
        return "th/book";
    }
}