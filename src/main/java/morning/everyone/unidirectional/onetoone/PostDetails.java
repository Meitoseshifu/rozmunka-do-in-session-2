package morning.everyone.unidirectional.onetoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@Getter @Setter
@ToString
public class PostDetails {
    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Post post;

    private Date createOn;
    private String createdBy;




    /*doInJPA(entityManager -> {
        Post post = entityManager.find(Post.class, 1L);
        PostDetails details = new PostDetails("John Doe");
        details.setPost(post);
        entityManager.persist(details);
    });*/

    /*PostDetails details = entityManager.find(
            PostDetails.class,
            post.getId()
    );*/
}
