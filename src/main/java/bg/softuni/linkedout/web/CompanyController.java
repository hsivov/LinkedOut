package bg.softuni.linkedout.web;

import bg.softuni.linkedout.model.dto.CompanyDTO;
import bg.softuni.linkedout.model.view.CompanyGetAllView;
import bg.softuni.linkedout.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/add")
    public String addCompany(Model model) {

        if (!model.containsAttribute("companyDTO")) {
            model.addAttribute("companyDTO", new CompanyDTO());
        }
        return "company-add";
    }

    @PostMapping("/add")
    public String addCompany(@Valid CompanyDTO companyDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("companyDTO", companyDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.companyDTO", bindingResult
            );
            return "company-add";
        }

        companyService.add(companyDTO);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<CompanyGetAllView> companies = companyService.getAll();

        model.addAttribute("companies", companies);

        return "company-all";
    }
}
