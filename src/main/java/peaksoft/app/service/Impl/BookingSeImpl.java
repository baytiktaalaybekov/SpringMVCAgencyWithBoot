package peaksoft.service.Impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.repository.AgencyRe;
import peaksoft.repository.BookingRe;
import peaksoft.repository.CustomerRE;
import peaksoft.repository.HouseRe;
import peaksoft.service.BookingSe;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingSeImpl implements BookingSe {

    private final BookingRe bookingRe;
    private final HouseRe houseRe;
    private final CustomerRE customerRe;
    private final AgencyRe agencyRe;

    private final EntityManager entityManager;


    @Override
    public void saveBooking(Long id,Booking booking) {
        House house = entityManager.find(House.class, booking.getHouse_id());
        if (house.getIs_Booked()){
            throw new RuntimeException("House with id %s already reserved.".formatted(house.getId()));}
        Customer customer = entityManager.find(Customer.class, booking.getCustomer_id());
        Booking booking1 = new Booking();
        booking1.setHouse_id(house.getId());
        booking1.setCustomer_id(customer.getId());
        booking1.setHouses(house);
        booking1.setCustomers(customer);
        house.setIs_Booked(true);
        bookingRe.saveBooking(booking1);
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRe.getAllBooking();
    }

    @Override
    public List<Booking> getAll(Long id) {
        return bookingRe.getAll(id);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRe.getBookingById(id);
    }

    @Override
    public void updateBooking(Long id, Booking booking) {
        Customer customer = customerRe.getCustomerById(booking.getCustomers().getId());
        House house = houseRe.getHouseById(booking.getHouses().getId());
        if (house.getIs_Booked()){
            throw new RuntimeException("House with id [%s] already reserved".formatted(house.getId()));
        }
        Booking booking1 = bookingRe.getBookingById(id);
        booking1.setHouses(house);
        booking1.setCustomers(customer);
        System.out.println("in service");
        bookingRe.updateBooking(booking);
    }


    @Override
    public void deleteBookingById(Long id) {
        bookingRe.deleteBookingById(id);
    }
}
