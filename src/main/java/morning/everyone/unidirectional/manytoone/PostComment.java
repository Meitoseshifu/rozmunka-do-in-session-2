package morning.everyone.unidirectional.manytoone;

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
public class PostComment {
    @Id
    @GeneratedValue
    private Long id;
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    @ToString.Exclude
    private Post post;

}
