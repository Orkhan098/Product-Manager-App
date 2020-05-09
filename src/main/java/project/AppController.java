package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    private final ProductService productService;

    @Autowired
    public AppController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts",listProducts);

        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        return "new_product";
    }
    @RequestMapping(method=RequestMethod.POST,value = "/save")
    public String saveProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        return "redirect:/ ";
    }

//    @RequestMapping("/edit/{id}")
//    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id){
//    ModelAndView mav= new ModelAndView("edit_product");
//    Product product=productService.get(id);
//    mav.addObject("product",product);
//    return mav;
//    }
    @RequestMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id,Model model){
        model.addAttribute("product",productService.get(id));
        return "edit_product";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/";
    }

}
