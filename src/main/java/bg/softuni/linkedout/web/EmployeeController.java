package bg.softuni.linkedout.web;

import bg.softuni.linkedout.model.EducationLevel;
import bg.softuni.linkedout.model.dto.CompanyDTO;
import bg.softuni.linkedout.model.dto.EmployeeDTO;
import bg.softuni.linkedout.model.view.EmployeeGetAllView;
import bg.softuni.linkedout.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        List<CompanyDTO> companies = employeeService.getAllCompanies();

        model.addAttribute("companies", companies);
        model.addAttribute("levels", EducationLevel.values());

        if (!model.containsAttribute("employeeDTO")) {
            model.addAttribute("employeeDTO", new EmployeeDTO());
        }

        return "employee-add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid EmployeeDTO employeeDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeDTO", employeeDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.employeeDTO", bindingResult
            );
            return "employee-add";
        }

        employeeService.addEmployee(employeeDTO);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<EmployeeGetAllView> employees = employeeService.getAllEmployees();

        model.addAttribute("employees", employees);

        return "employee-all";
    }
}
