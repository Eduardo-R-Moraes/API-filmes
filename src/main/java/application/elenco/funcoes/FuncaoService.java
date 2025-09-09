package application.elenco.funcoes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FuncaoService {
    @Autowired
    private FuncaoRepository funcaoRepo;

    public Iterable<FuncaoDTO> getAll() {
        return funcaoRepo.findAll().stream().map(FuncaoDTO::new).toList();
    }

    public FuncaoDTO getById(long id) {
        Optional<Funcao> funcao = funcaoRepo.findById(id);

        if (funcao.isEmpty()) {
             throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Função não encontrado."
            );
        }

        return new FuncaoDTO(funcao.get());
    }

    public FuncaoDTO insert(FuncaoInsertDTO dados) {
        return new FuncaoDTO(funcaoRepo.save(new Funcao(dados)));
    }

    public FuncaoDTO update(long id, FuncaoInsertDTO dados) {
        Optional<Funcao> funcao = funcaoRepo.findById(id);

        if (funcao.isEmpty()) {
             throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Função não encontrado."
            );
        }

        funcao.get().setDescricao(dados.descricao());

        return new FuncaoDTO(funcaoRepo.save(funcao.get()));
    }

    public void deleteById(long id) {
        Optional<Funcao> funcao = funcaoRepo.findById(id);

        if (funcao.isEmpty()) {
             throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "função não encontrado."
            );
        }

        funcaoRepo.deleteById(id);
    }
}
