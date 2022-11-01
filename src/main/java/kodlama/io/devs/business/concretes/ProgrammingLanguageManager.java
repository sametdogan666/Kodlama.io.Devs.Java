package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @Autowired
    public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository) {
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public List<ProgrammingLanguage> getAll() {
        return programmingLanguageRepository.getAll();
    }

    @Override
    public ProgrammingLanguage getById(int id) throws Exception {
        if (!isIdExist(id)) throw new Exception("Id bulunamadı");
        return programmingLanguageRepository.getById(id);
    }

    @Override
    public void add(ProgrammingLanguage programmingLanguage) throws Exception {
        if (isIdExist(programmingLanguage.getId())) throw new Exception("Id tekrar edemez");
        if (isNameEmpty(programmingLanguage)) throw  new Exception("İsim boş geçilemez");
        if (isNameExist(programmingLanguage)) throw new Exception("Bu isimde programlama dili zaten mevcuttur.");
        programmingLanguageRepository.add(programmingLanguage);
    }

    @Override
    public void update(ProgrammingLanguage programmingLanguage) throws Exception {
        if (!isIdExist(programmingLanguage.getId())) throw new Exception("Id bulunamadı");
        if (isNameEmpty(programmingLanguage)) throw  new Exception("İsim boş geçilemez");
        if (isNameExist(programmingLanguage)) throw new Exception("Bu isimde programlama dili zaten mevcuttur.");
        programmingLanguageRepository.update(programmingLanguage);
    }

    @Override
    public void delete(int id) throws Exception {
        if (!isIdExist(id)) throw new Exception("Id bulunamadı");
        programmingLanguageRepository.delete(id);
    }

    private boolean isNameExist(ProgrammingLanguage programmingLanguage){
        for (ProgrammingLanguage language : getAll()){
            if (programmingLanguage.getName().equals(language.getName())){
                return true;
            }
        }
        return false;
    }

    private boolean isNameEmpty(ProgrammingLanguage programmingLanguage){
       if (programmingLanguage.getName().isEmpty()){
           return true;
       }
       return false;
    }

    private boolean isIdExist(int id){
        for (ProgrammingLanguage language : getAll()){
            if (language.getId() == id){
                return true;
            }
        }
        return false;
    }

}
