package lucaguerra.payload;

import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lucaguerra.entities.EdificioConverter;

@Getter
@Setter
@AllArgsConstructor
public class NewEdificioPayload {

	@NotNull(message = "L'username è obbligatorio")
	private String name;
	@Convert(converter = EdificioConverter.class)
	@NotNull(message = "Il codice è obbligatorio")
	@Size(min = 8, max = 8, message = "Il codice deve essere composto da 8 caratteri")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]*$", message = "Il codice deve essere alfanumerico e contenere almeno un carattere e un numero")
	private String codice;
}
