package application.elenco;

import application.elenco.artistas.Artista;
import application.elenco.funcoes.Funcao;
import application.filmes.Filme;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "elencos")
@Getter
@Setter
@NoArgsConstructor
public class Elenco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // No banco ele vai criar as três colunas
    // de fk.

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "funcao_id")
    private Funcao funcao;

    public Elenco(ElencoDTO dados) {
        this.setId(dados.id());
        this.setFilme(new Filme(dados.filme()));
        this.setArtista(new Artista(dados.artista()));
        this.setFuncao(new Funcao(dados.funcao()));
    }

}
