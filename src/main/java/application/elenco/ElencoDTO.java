package application.elenco;

import application.elenco.artistas.ArtistaDTO;
import application.elenco.funcoes.FuncaoDTO;
import application.filmes.FilmeDTO;

public record ElencoDTO(
        long id, 
        FilmeDTO filme, 
        ArtistaDTO artista,
        FuncaoDTO funcao
    ) {
    
    public ElencoDTO(Elenco dados) {
        this(
            dados.getId(),
            new FilmeDTO(dados.getFilme()),
            new ArtistaDTO(dados.getArtista()),
            new FuncaoDTO(dados.getFuncao())
        );
    }
}
