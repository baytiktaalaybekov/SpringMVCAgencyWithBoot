package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Booking;
import peaksoft.service.BookingSe;
import peaksoft.service.CustomerSe;
import peaksoft.service.HouseSe;

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
//        try {
//            bookingSe.saveBooking(booking);
//            return "redirect:/bookings/{id}";
//        } catch (RuntimeException e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "booking/newBooking";
//        }
        model.addAttribute("houses", houseSe.getAllHouse(id));
        model.addAttribute("customers", customerSe.getAllCustomers(id));
        bookingSe.saveBooking(id,booking);
        return "redirect:/bookings/{id}";

    }

    @DeleteMapping("/{bookingId}/delete")
    public String deleteBooking(@PathVariable Long bookingId,
                                @PathVariable Long id) {
        bookingSe.deleteBookingById(id);
        return "redirect:/bookings/"+id;
    }

    @GetMapping("/{bookingId}/edit")
    public String edit(@PathVariable Long bookingId,
                       @PathVariable Long id, Model model) {
        Booking bookingById = bookingSe.getBookingById(bookingId);
        model.addAttribute("houses", houseSe.getAllHouse(id));
        model.addAttribute("customers", customerSe.getAllCustomers(id));
        model.addAttribute("booking", bookingId);
        model.addAttribute(id);
        return "booking/updateBooking";
    }

    @PutMapping("/{bookingId}/update")
    public String update(@ModelAttribute("booking") Booking booking,
                         @PathVariable Long bookingId,
                         @PathVariable Long id) {
        bookingSe.updateBooking(bookingId, booking);
        return "redirect:/bookings/{id}";

    }


}
