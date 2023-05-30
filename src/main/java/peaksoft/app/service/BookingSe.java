package peaksoft.app.service;


import peaksoft.app.entity.Booking;

import java.util.List;

public interface BookingSe {

    void saveBooking(Long id, Booking booking);

    List<Booking> getAllBooking();

    List<Booking> getAll(Long id);

    Booking getBookingById(Long id);

    void updateBooking(Long id, Booking booking);

    String deleteBookingById(Long id);
}
