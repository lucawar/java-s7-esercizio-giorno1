package lucaguerra.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lucaguerra.entities.Edificio;
import lucaguerra.exceptions.NotFoundException;
import lucaguerra.payload.NewEdificioPayload;
import lucaguerra.repositories.EdificioRepository;

@Service
public class EdificioService {

	@Autowired
	EdificioRepository edificioRepo;

	@Autowired
	EdificioService edificioservice;

	// SALVA NUOVO EDIFICIO
	public Edificio save(NewEdificioPayload body) {
		Edificio newEdificio = new Edificio(body.getName(), body.getCodice());
		return edificioRepo.save(newEdificio);
	}

	// TORNA LA LISTA DEGLI EDIFICI
	public List<Edificio> getEdificio() {
		return edificioRepo.findAll();
	}

	// CERCA EDIFICIO TRAMITE ID
	public Edificio findById(UUID id) throws NotFoundException {
		return edificioRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA EDIFICIO TRAMITE ID
	public Edificio findByIdAndUpdate(UUID id, NewEdificioPayload body) throws NotFoundException {
		Edificio found = this.findById(id);
		found.setName(body.getName());
		return edificioRepo.save(found);
	}

	// CERCA E CANCELLA EDIFICIO TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Edificio found = this.findById(id);
		edificioRepo.delete(found);
	}
}
