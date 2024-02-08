package morning.everyone;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "notes")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "person")
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "notes_persons_fk"))
    private Person person;

    public Note(String body) {
        this.body = body;
    }
}
