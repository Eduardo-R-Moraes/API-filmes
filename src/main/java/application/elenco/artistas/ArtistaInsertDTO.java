package application.elenco.artistas;

public record ArtistaInsertDTO(String nome) {
    public ArtistaInsertDTO(Artista dados) {
        this(dados.getNome());
    }
}
