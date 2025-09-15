package application.produtoras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtoras")
public class ProdutoraController {
    @Autowired
    private ProdutoraService produtoraService;

    @GetMapping
    public Iterable<ProdutoraDTO> getAll() {
        return produtoraService.getAll();
    }

    @GetMapping("/{id}")
    public ProdutoraDTO getById(@PathVariable long id) {
        return produtoraService.getById(id);
    }

    @PostMapping
    public ProdutoraDTO insert(@RequestBody ProdutoraInsertDTO novaProdutora) {
        return produtoraService.insert(novaProdutora);
    }

    @PutMapping("/{id}")
    public ProdutoraDTO update(@RequestBody ProdutoraInsertDTO dados, @PathVariable long id) {
        return produtoraService.update(dados, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        produtoraService.delete(id);
    }
}
