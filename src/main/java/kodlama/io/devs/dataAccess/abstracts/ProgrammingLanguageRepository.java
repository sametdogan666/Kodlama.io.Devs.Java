package kodlama.io.devs.dataAccess.abstracts;



import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

import java.util.List;

public interface ProgrammingLanguageRepository {
    List<ProgrammingLanguage> getAll();
    ProgrammingLanguage getById(int id);
    void add(ProgrammingLanguage programmingLanguage);
    void delete(int id);
    void update(ProgrammingLanguage programmingLanguage);
}
