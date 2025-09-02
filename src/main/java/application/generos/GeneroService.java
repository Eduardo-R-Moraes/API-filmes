package application.generos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepo;

    
    public Iterable<GeneroDTO> getAll() {
        return generoRepo.findAll().stream().map(GeneroDTO::new).toList();
    }

    public GeneroDTO getById(long id) {
        Optional<Genero> genero = generoRepo.findById(id);

        if (genero.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Genero não encontrado."
            );
        }

        return new GeneroDTO(genero.get());
    }

    public GeneroInsertDTO insert(GeneroInsertDTO dados) {
        Genero novoGenero = new Genero(dados);
        return new GeneroInsertDTO(generoRepo.save(novoGenero));
    }

    public GeneroInsertDTO update(long id, String nome) {
        Optional<Genero> genero = generoRepo.findById(id);

        if (genero.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Genero não encontrado."
            );
        }

        genero.get().setNome(nome);

        return new GeneroInsertDTO(generoRepo.save(genero.get()));
    }

    public void delete(long id) {
        Optional<Genero> genero = generoRepo.findById(id);

        if (genero.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Genero não encontrado."
            );
        }

        generoRepo.delete(genero.get());
    }
}
