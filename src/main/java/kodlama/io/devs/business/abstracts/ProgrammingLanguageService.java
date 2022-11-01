package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

import java.util.List;

public interface ProgrammingLanguageService {
    List<ProgrammingLanguage> getAll();
    ProgrammingLanguage getById(int id) throws Exception;
    void add(ProgrammingLanguage programmingLanguage) throws Exception;
    void update(ProgrammingLanguage programmingLanguage) throws Exception;
    void delete(int id) throws Exception;
}
