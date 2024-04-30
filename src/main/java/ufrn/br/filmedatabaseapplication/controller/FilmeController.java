package ufrn.br.filmedatabaseapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ufrn.br.filmedatabaseapplication.domain.Filme;
import ufrn.br.filmedatabaseapplication.repository.FilmeRepository;
import ufrn.br.filmedatabaseapplication.service.FilmeService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class FilmeController {

    private final FilmeService service;
    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping("/update")
    public String listAll(Model model){
        model.addAttribute("filmes", service.findAll());
        return "index";
    }

   @GetMapping("/options")
   public String pagina() {
        return "filmeUpdate";
   }

   @PostMapping("/cadastrar")
    public String cadastrarFilme(@RequestParam("titulo") String titulo,
                                 @RequestParam("anoLancamento") int anoLancamento,
                                 @RequestParam("genero") String genero,
                                 @RequestParam("nota") Float nota,
                                 @RequestParam("resumo") String resumo){
        
        Filme filme = new Filme(); 
        filme.setTitulo(titulo);
        filme.setAnoLancamento(anoLancamento);
        filme.setGenero(genero);
        filme.setNota(nota);
        filme.setResumo(resumo);   
        service.create(filme);                      
        return "index";  
    }
    @GetMapping("/alterar")
    public String dadosFilme(@RequestParam("id") Long id, Model model) {
        Filme filme = service.findById(id).get();
        model.addAttribute("filme", filme);
        System.out.println(service.findById(id));
        return "filmeUpdate";
        
    }
    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute Filme filme) {
        service.update(filme);
        System.out.println(filme);
                                
        return "index";
    }
    @GetMapping("/excluir")
    public String excluir(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        service.delete(id);
        //redirectAttributes.addFlashAttribute("excluido", true);
        System.out.println(id);

        return "redirect:/update";
    }

    
    
   
  
    
    
    
}
