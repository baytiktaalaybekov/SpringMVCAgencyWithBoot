package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.HouseType;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "house")
@Getter
@Setter
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "House_SEQ")
    @SequenceGenerator(name = "House_SEQ", sequenceName = "House_id_gen", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private String address;
    private String price;
    private String room;
    private String country;
    private String description;
    private Boolean is_Booked = false;
    @Column(length = 100000000)
    private String image_Link;

    @OneToOne(mappedBy = "houses", cascade = {ALL})
    private Booking bookings;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH, PERSIST})
    private Agency agencies;

    public House(HouseType houseType, String address, String price, String room, String country, String description, Boolean is_Booked, String image_Link) {
        this.houseType = houseType;
        this.address = address;
        this.price = price;
        this.room = room;
        this.country = country;
        this.description = description;
        this.is_Booked = is_Booked;
        this.image_Link = image_Link;
    }
}