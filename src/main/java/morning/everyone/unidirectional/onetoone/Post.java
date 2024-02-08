package morning.everyone.unidirectional.onetoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
