package application.filmes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.generos.Genero;
import application.generos.GeneroService;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepo;

    @Autowired
    private GeneroService generoService;

    public Iterable<FilmeDTO> getAll() {
        return filmeRepo.findAll().stream().map(FilmeDTO::new).toList();
    }

    public FilmeDTO getById(long id) {
        Optional<Filme> filme = filmeRepo.findById(id);

        if (filme.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Filme não encontrado."
            );
        }

        return new FilmeDTO(filmeRepo.save(filme.get()));
    }

    public FilmeDTO insert(FilmeInsertDTO filme_novo) {
        Genero genero = new Genero(generoService.getById(filme_novo.idGenero()));
        Filme filme = new Filme();

        filme.setTitulo(filme_novo.titulo());
        filme.setGenero(genero);

        return new FilmeDTO(filmeRepo.save(filme));
    }

    public FilmeDTO update(long id, FilmeInsertDTO novosDados) {

        Optional<Filme> filme = filmeRepo.findById(id);

        if (filme.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Filme não encontrado."
            );
        }

        Genero genero = new Genero(generoService.getById(novosDados.idGenero()));

        filme.get().setTitulo(novosDados.titulo());
        filme.get().setGenero(genero);

        return new FilmeDTO(filmeRepo.save(filme.get()));
    }

    public void delete(long id) {
        Optional<Filme> filme = filmeRepo.findById(id);

        if (filme.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Filme não encontrado."
            );
        }

        filmeRepo.deleteById(id);
    }
}
