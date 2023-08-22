package lucaguerra.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Edificio saveEdificio(@RequestBody NewEdificioPayload body) {
		Edificio createdEdificio = edificioService.save(body);
		return createdEdificio;
	}

	@GetMapping("")
	public List<Edificio> getEdificio() {
		return edificioService.getEdificio();
	}

	@GetMapping("/{edificioId}")
	public Edificio findById(@PathVariable UUID edificioId) {
		return edificioService.findById(edificioId);
	}

	@PutMapping("/{edificioId}")
	public Edificio updateEdificio(@PathVariable UUID dispositivoId, @RequestBody NewEdificioPayload body) {
		return edificioService.findByIdAndUpdate(dispositivoId, body);
	}

	@DeleteMapping("/{edificioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEdificio(@PathVariable UUID dispositivoId) {
		edificioService.findByIdAndDelete(dispositivoId);
	}

}
