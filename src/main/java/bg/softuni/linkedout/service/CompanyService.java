package bg.softuni.linkedout.service;

import bg.softuni.linkedout.model.dto.CompanyDTO;
import bg.softuni.linkedout.model.view.CompanyGetAllView;

import java.util.List;

public interface CompanyService {
    void add(CompanyDTO companyDTO);

    List<CompanyGetAllView> getAll();
}
