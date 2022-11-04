package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.programmingLanguages.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.technologies.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.programmingLanguages.GetAllProgramingLanguagesResponse;
import kodlama.io.devs.business.responses.technologies.GetAllTechnologiesResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;
import kodlama.io.devs.entities.concretes.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyRepository technologyRepository;
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @Autowired
    public TechnologyManager(TechnologyRepository technologyRepository, ProgrammingLanguageRepository programmingLanguageRepository) {
        this.technologyRepository = technologyRepository;
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public List<GetAllTechnologiesResponse> getAll() {
        List<Technology> technologies = this.technologyRepository.findAll();
        List<GetAllTechnologiesResponse> getAllTechnologiesResponses = new ArrayList<GetAllTechnologiesResponse>();

        for (Technology technology : technologies) {
            GetAllTechnologiesResponse responseItem = new GetAllTechnologiesResponse();
            responseItem.setId(technology.getId());
            responseItem.setProgrammingLanguageId(technology.getProgrammingLanguage().getId());
            responseItem.setName(technology.getName());
            responseItem.setProgrammingLanguageName(technology.getProgrammingLanguage().getName());

            getAllTechnologiesResponses.add(responseItem);
        }

        return getAllTechnologiesResponses;
    }

    @Override
    public void add(CreateTechnologyRequest createTechnologyRequest) throws Exception {
        Technology technology = new Technology();
        ProgrammingLanguage programmingLanguage = this.programmingLanguageRepository.findById(createTechnologyRequest.getProgrammingLanguagesId()).get();

        technology.setName(createTechnologyRequest.getName());
        technology.setProgrammingLanguage(programmingLanguage);

        if (isNameExistForAdd(technology)) throw new Exception("Bu isimde programlama dili zaten mevcuttur");
        if (isNameEmptyForAdd(technology)) throw new Exception("İsim boş geçilemez");
        this.technologyRepository.save(technology);
    }

    @Override
    public void update(UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
        if (!isIdExist(updateTechnologyRequest.getId())) throw new Exception("Id bulunamadı");
        if (isNameEmptyForUpdate(updateTechnologyRequest)) throw new Exception("İsim alanı boş geçilemez");
        if (isNameExistForUpdate(updateTechnologyRequest))
            throw new Exception("Bu isimde programlama dili zaten var");
        Technology technology = this.technologyRepository.findById(updateTechnologyRequest.getId()).get();

        ProgrammingLanguage programmingLanguage = this.programmingLanguageRepository.findById(updateTechnologyRequest.getProgrammingLanguageId()).get();

        technology.setName(updateTechnologyRequest.getName());
        technology.setProgrammingLanguage(programmingLanguage);

        this.technologyRepository.save(technology);
    }

    @Override
    public void delete(DeleteTechnologyRequest deleteTechnologyRequest) throws Exception {
        if (!isIdExist(deleteTechnologyRequest.getId())) throw new Exception("Id bulunamadı");
        this.technologyRepository.deleteById(deleteTechnologyRequest.getId());
    }

    private boolean isNameExistForAdd(Technology technology) {
        for (GetAllTechnologiesResponse technologiesResponse : getAll()) {
            if (technology.getName().equals(technologiesResponse.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isNameEmptyForAdd(Technology technology) {
        if (technology.getName().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isNameEmptyForUpdate(UpdateTechnologyRequest updateTechnologyRequest) {
        if (updateTechnologyRequest.getName().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isNameExistForUpdate(UpdateTechnologyRequest updateTechnologyRequest) {
        for (GetAllTechnologiesResponse technologiesResponse : getAll()) {
            if (updateTechnologyRequest.getName().equals(technologiesResponse.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isIdExist(int id) {
        for (GetAllTechnologiesResponse language : getAll()) {
            if (language.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
