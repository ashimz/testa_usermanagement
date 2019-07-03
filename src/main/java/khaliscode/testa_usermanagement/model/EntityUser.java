package khaliscode.testa_usermanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_main", schema = "public")
public class EntityUser {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userid;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_ic")
    private String useric;
}
