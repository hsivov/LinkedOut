package bg.softuni.linkedout.service.impl;

import bg.softuni.linkedout.model.dto.CompanyDTO;
import bg.softuni.linkedout.model.dto.EmployeeDTO;
import bg.softuni.linkedout.model.entity.Company;
import bg.softuni.linkedout.model.entity.Employee;
import bg.softuni.linkedout.model.view.EmployeeGetAllView;
import bg.softuni.linkedout.repository.CompanyRepository;
import bg.softuni.linkedout.repository.EmployeeRepository;
import bg.softuni.linkedout.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(CompanyRepository companyRepository,
                               ModelMapper modelMapper,
                               EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map((company) -> modelMapper.map(company, CompanyDTO.class))
                .toList();
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Company company = companyRepository.findByName(employeeDTO.getCompany());

        employee.setCompany(company);

        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeGetAllView> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeGetAllView.class))
                .toList();
    }
}
