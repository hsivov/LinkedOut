package bg.softuni.linkedout.service.impl;

import bg.softuni.linkedout.model.dto.CompanyDTO;
import bg.softuni.linkedout.model.entity.Company;
import bg.softuni.linkedout.model.view.CompanyGetAllView;
import bg.softuni.linkedout.repository.CompanyRepository;
import bg.softuni.linkedout.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(ModelMapper modelMapper,
                              CompanyRepository companyRepository) {
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public void add(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);

        companyRepository.save(company);
    }

    @Override
    public List<CompanyGetAllView> getAll() {
        return companyRepository.findAll().stream()
                .map(company -> modelMapper.map(company, CompanyGetAllView.class))
                .toList();
    }
}
