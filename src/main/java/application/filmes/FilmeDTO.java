package application.filmes;

import application.generos.GeneroDTO;

// É aqui onde as relações brilham 
public record FilmeDTO(long id, String titulo, GeneroDTO genero) {
    FilmeDTO(Filme filme) {
        this(
            filme.getId(), 
            filme.getTitulo(), 
            new GeneroDTO(filme.getGenero())
        );
    }
}
