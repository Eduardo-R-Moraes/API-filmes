package application.elenco.funcoes;

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
@RequestMapping("/funcoes")
public class FuncaoController {
    @Autowired
    private FuncaoService funcaoService;

    @GetMapping
    public Iterable<FuncaoDTO> getAll() {
        return funcaoService.getAll();
    }

    @GetMapping("/{id}")
    public FuncaoDTO getById(@PathVariable long id) {
        return funcaoService.getById(id);
    }

    @PostMapping
    public FuncaoDTO insert(@RequestBody FuncaoInsertDTO novaFuncao) {
        return funcaoService.insert(novaFuncao);
    }

    @PutMapping("/{id}")
    public FuncaoDTO update(@RequestBody FuncaoInsertDTO novaFuncao, @PathVariable long id) {
        return funcaoService.update(id, novaFuncao);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        funcaoService.deleteById(id);
    }
}
