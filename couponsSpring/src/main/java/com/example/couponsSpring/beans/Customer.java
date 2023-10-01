package com.example.couponsSpring.beans;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Coupon> coupons;

    public Customer(String firstName, String lastName, String email, String password, List<Coupon> coupons) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "\nid=" + id +
                "\nfirstName='" + firstName + '\'' +
                "\nlastName='" + lastName + '\'' +
                "\nemail='" + email + '\'' +
                "\npassword='" + password + '\'' +
                "\ncoupons=" + coupons +
                '}';
    }
}
