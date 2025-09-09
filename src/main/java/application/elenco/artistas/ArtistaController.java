package application.elenco.artistas;

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
@RequestMapping("/artistas")
public class ArtistaController {
    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public Iterable<ArtistaDTO> getAll() {
        return artistaService.getAll();
    }

    @GetMapping("/{id}")
    public ArtistaDTO getById(@PathVariable long id) {
        return artistaService.getById(id);
    }

    @PostMapping
    public ArtistaDTO insert(@RequestBody ArtistaInsertDTO novoArtista) {
        return artistaService.insert(novoArtista);
    }

    @PutMapping("/{id}")
    public ArtistaDTO update(@RequestBody ArtistaInsertDTO novoArtista, @PathVariable long id) {
        return artistaService.update(id, novoArtista);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        artistaService.deleteById(id);
    }
}
