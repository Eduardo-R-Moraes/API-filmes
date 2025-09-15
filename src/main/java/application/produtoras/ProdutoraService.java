package application.produtoras;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoraService {
    @Autowired
    private ProdutoraRepository produtoraRepo;

    public Iterable<ProdutoraDTO> getAll() {
        return produtoraRepo.findAll().stream().map(ProdutoraDTO::new).toList();
    }

    public ProdutoraDTO getById(long id) {
        Optional<Produtora> resultado = produtoraRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produtora não encontrada."
            );
        }

        return new ProdutoraDTO(resultado.get());
    }

    public ProdutoraDTO insert(ProdutoraInsertDTO dados) {
        Produtora novaProdutora = new Produtora(dados);
        return new ProdutoraDTO(produtoraRepo.save(novaProdutora));
    }

    public ProdutoraDTO update(ProdutoraInsertDTO dados, long id) {
        Optional<Produtora> resultado = produtoraRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produtora não encontrada."
            );
        }

        resultado.get().setNome(dados.nome());

        return new ProdutoraDTO(produtoraRepo.save(resultado.get()));
    }

    public void delete(long id) {
        Optional<Produtora> resultado = produtoraRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produtora não encontrada."
            );
        }

        produtoraRepo.deleteById(id);
    }
}
