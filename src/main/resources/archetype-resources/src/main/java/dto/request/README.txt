Use this package for DTO object (request or response) like  :

@Data
public class UserDto {

    private String name;
    private String surname;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String role;
    private String seat;
}