package org.market.pricecomparator.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baskets")
@ToString(exclude = {"user", "shoppingLists"})
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "basket")
    private User user;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<ShoppingList> shoppingLists = new ArrayList<>();
}
