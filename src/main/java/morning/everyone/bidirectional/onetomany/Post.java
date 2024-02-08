package morning.everyone.bidirectional.onetomany;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    public void addComment(PostComment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(PostComment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }
}
