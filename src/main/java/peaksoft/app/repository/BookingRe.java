package peaksoft.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.app.entity.Booking;

import java.util.List;

@Repository
public interface BookingRe extends JpaRepository<Booking,Long> {

    @Query("from Booking  b where b.houses.id =:id")
    List<Booking> getAll(Long id);

    @Query("delete from Booking b where b.id =:id")
    String deleteBookingById(Long id);


}
