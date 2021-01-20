package ODL.study.cartAPI.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "c_id"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart extends AbstractEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    List<CartItem> items;

}
