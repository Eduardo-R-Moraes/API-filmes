package application.elenco;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.elenco.artistas.Artista;
import application.elenco.artistas.ArtistaRepository;
import application.elenco.funcoes.Funcao;
import application.elenco.funcoes.FuncaoRepository;
import application.filmes.Filme;
import application.filmes.FilmeRepository;

@Service
public class ElencoService {
    @Autowired
    private ElencoRepository elencoRepo;

    @Autowired
    private FilmeRepository filmeRepo;

    @Autowired
    private ArtistaRepository artistaRepo;

    @Autowired
    private FuncaoRepository funcaoRepo;

    public Iterable<ElencoDTO> getAll() {
        return elencoRepo.findAll().stream().map(ElencoDTO::new).toList();
    }

    public ElencoDTO getById(long id) {
        Optional<Elenco> elenco = elencoRepo.findById(id);

        if (elenco.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Elenco não encontado"
            );
        }

        return new ElencoDTO(elenco.get());
    }

    public ElencoDTO insert(ElencoInsertDTO dados) {
        Optional<Filme> filme = filmeRepo.findById(dados.idFilme());

        Optional<Artista> artista = artistaRepo.findById(dados.idArtista());

        Optional<Funcao> funcao = funcaoRepo.findById(dados.idFuncao());

        String mensagem = "Dados não encontrados: ";
        boolean isError = false;

        if (filme.isEmpty()) {
            if(isError) {
                mensagem += ", ";
            }

            mensagem += "Filme";
            isError = true;
        }

        if (artista.isEmpty()) {
            if(isError) {
                mensagem += ", ";
            }

            mensagem += "Artista";
            isError = true;
        }

        if (funcao.isEmpty()) {
            if(isError) {
                mensagem += ", ";
            }

            mensagem += "Função";
            isError = true;
        }

        if (isError) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, mensagem
            );
        }

        Elenco elenco = new Elenco();

        elenco.setFilme(filme.get());
        elenco.setArtista(artista.get());
        elenco.setFuncao(funcao.get());

        return new ElencoDTO(elencoRepo.save(elenco));
    }

    public ElencoDTO update(long id, ElencoInsertDTO dados) {

        Optional<Elenco> elenco = elencoRepo.findById(id);

        if (elenco.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Elenco não encontado"
            );
        }

        Optional<Filme> filme = filmeRepo.findById(dados.idFilme());

        Optional<Artista> artista = artistaRepo.findById(dados.idArtista());

        Optional<Funcao> funcao = funcaoRepo.findById(dados.idFuncao());

        String mensagem = "Dados não encontrados: ";
        boolean isError = false;

        if (filme.isEmpty()) {
            if(isError) {
                mensagem += ", ";
            }

            mensagem += "Filme";
            isError = true;
        }

        if (artista.isEmpty()) {
            if(isError) {
                mensagem += ", ";
            }

            mensagem += "Artista";
            isError = true;
        }

        if (funcao.isEmpty()) {
            if(isError) {
                mensagem += ", ";
            }

            mensagem += "Função";
            isError = true;
        }

        if (isError) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, mensagem
            );
        }

        elenco.get().setFilme(filme.get());
        elenco.get().setArtista(artista.get());
        elenco.get().setFuncao(funcao.get());

        return new ElencoDTO(elencoRepo.save(elenco.get()));
    }

    public void deleteById(long id) {
        Optional<Elenco> elenco = elencoRepo.findById(id);

        if (elenco.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Elenco não encontrado."
            );
        }

        elencoRepo.deleteById(id);
    }
}
