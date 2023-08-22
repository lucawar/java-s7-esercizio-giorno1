package lucaguerra.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lucaguerra.entities.Edificio;
import lucaguerra.payload.NewEdificioPayload;
import lucaguerra.service.EdificioService;

@RestController
@RequestMapping("/edifici")
public class EdificioController {

	@Autowired
	EdificioService edificioService;

	@Autowired
	PasswordEncoder bcrypt;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Edificio saveEdificio(@RequestBody @Validated NewEdificioPayload body) {
		body.setCodice(bcrypt.encode(body.getCodice()));
		Edificio createdEdificio = edificioService.save(body);
		return createdEdificio;
	}

//	@GetMapping("")
//	public List<Edificio> getEdificio() {
//		return edificioService.getEdificio();
//	}

	@GetMapping("")
	public Page<Edificio> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return edificioService.find(page, size, sortBy);
	}

	@GetMapping("/{edificioId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Edificio findById(@PathVariable UUID edificioId) {
		return edificioService.findById(edificioId);
	}

	@PutMapping("/{edificioId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Edificio updateEdificio(@PathVariable UUID edificioId, @RequestBody NewEdificioPayload body) {
		return edificioService.findByIdAndUpdate(edificioId, body);
	}

	@DeleteMapping("/{edificioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteEdificio(@PathVariable UUID edificioId) {
		edificioService.findByIdAndDelete(edificioId);
	}

}
