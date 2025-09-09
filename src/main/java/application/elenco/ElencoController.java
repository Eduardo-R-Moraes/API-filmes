package application.elenco;

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
@RequestMapping("/elencos")
public class ElencoController {
    @Autowired
    private ElencoService elencoService;

    @GetMapping
    public Iterable<ElencoDTO> getAll() {
        return elencoService.getAll();
    }

    @GetMapping("/{id}")
    public ElencoDTO getById(@PathVariable long id) {
        return elencoService.getById(id);
    }

    @PostMapping
    public ElencoDTO insert(@RequestBody ElencoInsertDTO novoElenco) {
        return elencoService.insert(novoElenco);
    }

    @PutMapping("/{id}")
    public ElencoDTO update(@PathVariable long id, @RequestBody ElencoInsertDTO dados){
        return elencoService.update(id, dados);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        elencoService.deleteById(id);
    }
}
