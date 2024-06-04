package bg.softuni.linkedout.service;

import bg.softuni.linkedout.model.dto.CompanyDTO;
import bg.softuni.linkedout.model.dto.EmployeeDTO;
import bg.softuni.linkedout.model.view.EmployeeGetAllView;

import java.util.List;

public interface EmployeeService {
    List<CompanyDTO> getAllCompanies();

    void addEmployee(EmployeeDTO employeeDTO);

    List<EmployeeGetAllView> getAllEmployees();
}
