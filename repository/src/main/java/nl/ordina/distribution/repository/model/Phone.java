package nl.ordina.distribution.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phone {
    @Id
    private String name;
    private String color;
    private String segment;
    private String memory;
    private int cameras;
    private long cost;
    private int stock;
    private int minStock;
    private int maxStock;
    private int chipsPer;

}
