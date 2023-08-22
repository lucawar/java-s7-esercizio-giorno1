package lucaguerra.payload;

import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lucaguerra.entities.EdificioConverter;

@Getter
@Setter
@AllArgsConstructor
public class NewEdificioPayload {

	private String name;
	@Convert(converter = EdificioConverter.class)
	private String codice;
}
