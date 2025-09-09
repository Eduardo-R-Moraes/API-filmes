package application.elenco.artistas;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ArtistaService {
    @Autowired
    ArtistaRepository artistaRepo;

    public Iterable<ArtistaDTO> getAll() {
        return artistaRepo.findAll().stream().map(ArtistaDTO::new).toList();
    }

    public ArtistaDTO getById(long id) {
        Optional<Artista> artista = artistaRepo.findById(id);

        if (artista.isEmpty()) {
             throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Artista não encontrado."
            );
        }

        return new ArtistaDTO(artista.get());
    }

    public ArtistaDTO insert(ArtistaInsertDTO dados) {
        return new ArtistaDTO(artistaRepo.save(new Artista(dados)));
    }

    public ArtistaDTO update(long id, ArtistaInsertDTO dados) {
        Optional<Artista> artista = artistaRepo.findById(id);

        if (artista.isEmpty()) {
             throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Artista não encontrado."
            );
        }

        artista.get().setNome(dados.nome());

        return new ArtistaDTO(artistaRepo.save(artista.get()));
    }

    public void deleteById(long id) {
        Optional<Artista> artista = artistaRepo.findById(id);

        if (artista.isEmpty()) {
             throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Artista não encontrado."
            );
        }

        artistaRepo.deleteById(id);
    }
}
