package lucaguerra.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import lucaguerra.entities.Edificio;

public interface EdificioRepository extends JpaRepository<Edificio, UUID> {

}
