package peaksoft.app.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.app.entity.Booking;
import peaksoft.app.service.BookingSe;
import peaksoft.app.service.CustomerSe;
import peaksoft.app.service.HouseSe;


@Controller
@RequestMapping("/bookings/{id}")
@RequiredArgsConstructor
public class BookingApi {

    private final BookingSe bookingSe;
    private final HouseSe houseSe;
    private final CustomerSe customerSe;

    @GetMapping
    public String getAll(@PathVariable Long id, Model model) {
        model.addAttribute("booking", bookingSe.getAllBooking());
        model.addAttribute("houses", houseSe.getAllHouse(id));
        model.addAttribute("customers", customerSe.getAllCustomers(id));
        model.addAttribute(id);
        return "booking/bookings";

    }

    @GetMapping("/new")
    public String createBooking(@PathVariable Long id, Model model) {
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("customers", customerSe.getAllCustomers(id));
        model.addAttribute("houses", houseSe.getAllHouse(id));
        model.addAttribute("houseId", id);
        return "booking/newBooking";

    }



    @PostMapping("/save")
    public String saveBooking(@PathVariable Long id,@ModelAttribute("newBooking") Booking booking, Model model) {
        model.addAttribute("houses", houseSe.getAllHouse(id));
        model.addAttribute("customers", customerSe.getAllCustomers(id));
        bookingSe.saveBooking(id,booking);
        return "redirect:/bookings/{id}";

    }

    @GetMapping("/{bookingId}/delete")
    public String deleteBooking(@PathVariable Long bookingId,
                                @PathVariable Long id) {
        System.out.println("Baytik");
        bookingSe.deleteBookingById(bookingId);
        return "redirect:/bookings/" + id;
    }

    @GetMapping("/{bookingId}/edit")
    public String edit(@PathVariable Long bookingId,
                       @PathVariable Long id, Model model) {
        Booking bookingById = bookingSe.getBookingById(bookingId);
        model.addAttribute("houses", houseSe.getAllHouse(id));
        model.addAttribute("customers", customerSe.getAllCustomers(id));
        model.addAttribute("booking", new Booking());
        model.addAttribute(id);
        return "booking/updateBooking";
    }

    @PostMapping("/{bookingId}/update")
    public String update(@ModelAttribute("booking") Booking booking,
                         @PathVariable Long bookingId,
                         @PathVariable Long id) {
        bookingSe.updateBooking(bookingId, booking);
        return "redirect:/bookings/{id}";

    }


}
