package it.tickets.manager.Security;






import it.tickets.manager.Model.UserModel;

public class CustomUserDetails extends UserModel {
	private final String firstName;
    private final String lastName;
    private final String email;

    private CustomUserDetails(Builder builder) {
        super(builder.username, builder.password, builder.authorities);
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
    }

}
