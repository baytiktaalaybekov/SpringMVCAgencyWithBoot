package peaksoft.service;

import peaksoft.entity.Booking;

import java.util.List;

public interface BookingSe {

    void saveBooking(Long id, Booking booking);

    List<Booking> getAllBooking();

    List<Booking> getAll(Long id);

    Booking getBookingById(Long id);

    void updateBooking(Long id, Booking booking);

    void deleteBookingById(Long id);
}
