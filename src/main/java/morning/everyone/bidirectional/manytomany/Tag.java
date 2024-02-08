package morning.everyone.bidirectional.manytomany;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@NoArgsConstructor
@Getter @Setter
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();
    //private List<Post> posts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
