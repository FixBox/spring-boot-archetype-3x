Use this package for Entity  object DB (@Entity) like :


@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String career;


}