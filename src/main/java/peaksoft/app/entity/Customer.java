package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "customer")
@Getter @Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer_SEQ")
    @SequenceGenerator(name = "Customer_SEQ",sequenceName = "Customer_id_gen",allocationSize = 1)
    private Long id;

    private String name;

    private String surname;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    private LocalDate date_of_birth;

    @ManyToMany(cascade = {DETACH, MERGE,REFRESH, PERSIST})
    private List<Agency> agencies;

    @OneToMany(mappedBy = "customers",cascade = {ALL})
    private List<Booking> booking;


    public Customer(String name, String surname, String email, Gender gender, String phoneNumber, LocalDate date_of_birth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.date_of_birth = date_of_birth;
    }

}