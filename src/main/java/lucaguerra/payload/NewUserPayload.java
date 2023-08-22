package lucaguerra.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewUserPayload {

	@NotNull(message = "L'username è obbligatorio")
	private String username;
	@NotNull(message = "Il nome è obbligatorio")
	@Size(min = 3, max = 30, message = "Il nome deve avere min 3 caratteri e max 30 caratteri")
	private String name;
	@NotNull(message = "Il cognome è obbligatorio")
	private String surname;
	@NotNull(message = "L'email è obbligatoria")
	@Email(message = "La password inserita non è valida")
	private String email;
	@NotNull(message = "La password è obbligatoria")
	private String password;

}
