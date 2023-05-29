package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "agency")
@Getter @Setter
@NoArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Agency_SEQ")
    @SequenceGenerator(name = "Agency_SEQ",sequenceName = "Agency_id_gen",allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(length = 10000000)
    private String image_Link;

    @ManyToMany(mappedBy = "agencies",cascade = ALL)
    private List<Customer> customers;

    @OneToMany(mappedBy = "agencies",cascade = ALL)
    private List<House> house;

    public Agency(String name, String country, String phoneNumber, String email,String image_Link) {
        this.name = name;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image_Link= image_Link;
    }

    public Agency(Long id, String name, String country, String phoneNumber, String email, String image_Link, List<Customer> customers, List<House> house) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image_Link = image_Link;
        this.customers = customers;
        this.house = house;
    }

}