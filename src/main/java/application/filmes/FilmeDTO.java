package application.filmes;

import java.util.List;

import application.generos.GeneroDTO;
import application.produtoras.ProdutoraDTO;

// É aqui onde as relações brilham 
public record FilmeDTO(long id, String titulo, GeneroDTO genero, List<ProdutoraDTO> produtoras) {
    public FilmeDTO(Filme filme) {
        this(
            filme.getId(), 
            filme.getTitulo(), 
            new GeneroDTO(filme.getGenero()),
            filme.getProdutoras().stream().map(ProdutoraDTO::new).toList()
        );
    }
}
