package com.example.couponsSpring.beans;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    @ManyToOne
    private Company company;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;


    public Coupon(Company company, Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.company = company;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "\nid=" + id +
                "\ncompanyID=" + company.getId() +
                "\ncategory=" + category +
                "\ntitle='" + title + '\'' +
                "\nstartDate=" + startDate +
                "\nendDate=" + endDate +
                "\namount=" + amount +
                "\nprice=" + price +
                "\nimage='" + image + '\'' +
                '}';
    }
}
