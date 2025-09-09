package application.elenco.artistas;

public record ArtistaDTO(long id, String nome) {
    public ArtistaDTO(Artista artista) {
        this(artista.getId(), artista.getNome());
    }
}
