package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeashure uom;
    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }
    public Ingredient(String description, BigDecimal amount, UnitOfMeashure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
    public Ingredient(String description, BigDecimal amount, UnitOfMeashure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }
}
