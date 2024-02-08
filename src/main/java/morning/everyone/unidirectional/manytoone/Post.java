package morning.everyone.unidirectional.manytoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@Getter @Setter
@ToString
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
}
