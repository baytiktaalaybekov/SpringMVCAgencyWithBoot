package peaksoft.app.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.app.entity.Agency;
import peaksoft.app.entity.Booking;
import peaksoft.app.entity.Customer;
import peaksoft.app.entity.House;
import peaksoft.app.repository.AgencyRe;
import peaksoft.app.repository.BookingRe;
import peaksoft.app.repository.CustomerRE;
import peaksoft.app.repository.HouseRe;
import peaksoft.app.service.BookingSe;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingSeImpl implements BookingSe {

    private final BookingRe bookingRe;

    private final HouseRe houseRe;

    private final CustomerRE customerRe;

    @Override
    public void saveBooking(Long id, Booking booking) {
        House house = houseRe.findById(booking.getHouse_id()).orElseThrow(()->new RuntimeException("Booking with id: " +id+ "Not Found"));
        if (house.getIs_Booked()){
            throw new RuntimeException("House with id %s already reserved".formatted(house.getId()));
        }
        Customer customer = customerRe.findById(booking.getCustomer_id()).orElseThrow(()-> new RuntimeException("Booking with id:"+id+"Not Found"));
        Booking booking1 = new Booking();
        booking1.setHouse_id(house.getId());
        booking1.setCustomer_id(customer.getId());
        booking1.setHouses(house);
        booking1.setCustomers(customer);
        house.setIs_Booked(true);
        bookingRe.save(booking1);
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRe.findAll();
    }

    @Override
    public List<Booking> getAll(Long id) {
        return bookingRe.getAll(id);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRe.findById(id).orElseThrow(()-> new RuntimeException("Booking with id:"+id+"Not Found"));
    }

    @Override
    public void updateBooking(Long id, Booking booking) {
        Booking oldBooking = bookingRe.findById(id).orElseThrow(()-> new RuntimeException("Booking with id:"+id+"Not Found"));
        Customer customer = customerRe.findById(booking.getCustomer_id()).orElseThrow(()-> new RuntimeException("Booking with id:"+id+"Not Found"));;
        House house = houseRe.findById(booking.getHouse_id()).orElseThrow(()-> new RuntimeException("Booking with id:"+id+"Not Found"));
        if (!oldBooking.getHouses().getId().equals(house.getId()) && house.getIs_Booked()){
            throw new RuntimeException("House with id [%s] already reserved".formatted(house.getId()));
        }
        oldBooking.setHouses(house);
        oldBooking.setCustomers(customer);
        bookingRe.save(oldBooking);
    }

    @Override
    public String deleteBookingById(Long id) {
        boolean b = bookingRe.existsById(id);
        if (!b){
            throw new RuntimeException(("Booking with id:"+id+"Not Found"));
        }
//        bookingRe.findById(id).get().getHouses().setIs_Booked(false);
        bookingRe.deleteById(id);
        return "Booking with id:" +id+" is deleted";
    }
}
