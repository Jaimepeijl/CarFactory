package nl.ordina.distribution.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phone {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String colour;
    private String segment;
    private String memory;
    private int cameras;
    private long cost;
    private int stock;
    private int minStock;
    private int maxStock;
    private int chipsPer;

}
