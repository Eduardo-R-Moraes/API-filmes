package application.filmes;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.generos.Genero;
import application.generos.GeneroService;
import application.produtoras.Produtora;
import application.produtoras.ProdutoraService;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepo;    
    
    @Autowired
    private GeneroService generoService;

    @Autowired
    private ProdutoraService produtoraService;

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

    public FilmeDTO insert(FilmeInsertDTO novoFilme) {
        Genero genero = new Genero(generoService.getById(novoFilme.idGenero()));

        Set<Produtora> produtoras = novoFilme.idsProdutoras().stream().map(id -> new Produtora(produtoraService.getById(id))).collect(Collectors.toSet());
        
        Filme filme = new Filme();

        filme.setTitulo(novoFilme.titulo());
        filme.setGenero(genero);
        filme.setProdutoras(produtoras);

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

        Set<Produtora> produtoras = novosDados.idsProdutoras().stream().map(idProdutora -> new Produtora(produtoraService.getById(idProdutora))).collect(Collectors.toSet());

        filme.get().setTitulo(novosDados.titulo());
        filme.get().setGenero(genero);
        filme.get().setProdutoras(produtoras);

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
