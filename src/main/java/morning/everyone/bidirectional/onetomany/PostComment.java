package morning.everyone.bidirectional.onetomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter @Setter
public class PostComment {
    @Id
    @GeneratedValue
    private Long id;
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
