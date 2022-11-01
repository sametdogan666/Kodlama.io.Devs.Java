package kodlama.io.devs.dataAccess.concretes.inMemory;

import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProgrammingLanguageRepository implements ProgrammingLanguageRepository {

    private List<ProgrammingLanguage> programmingLanguages;

    @Autowired
    public InMemoryProgrammingLanguageRepository() {
        programmingLanguages = new ArrayList<ProgrammingLanguage>();
        programmingLanguages.add(new ProgrammingLanguage(1, "Java"));
        programmingLanguages.add(new ProgrammingLanguage(2, "C#"));
        programmingLanguages.add(new ProgrammingLanguage(3, "Javascript"));
        programmingLanguages.add(new ProgrammingLanguage(4, "React"));
        programmingLanguages.add(new ProgrammingLanguage(5, "Kotlin"));
    }

    @Override
    public List<ProgrammingLanguage> getAll() {
        return programmingLanguages;
    }

    @Override
    public ProgrammingLanguage getById(int id) {
        for (ProgrammingLanguage programmingLanguage : programmingLanguages) {
            if (programmingLanguage.getId() == id) {
                return programmingLanguage;
            }
        }
        throw null;
    }

    @Override
    public void add(ProgrammingLanguage programmingLanguage) {
        programmingLanguages.add(programmingLanguage);
    }

    @Override
    public void delete(int id) {
        ProgrammingLanguage deleteProgrammingLanguage = getById(id);
        programmingLanguages.remove(deleteProgrammingLanguage);
    }

    @Override
    public void update(ProgrammingLanguage programmingLanguage) {
        ProgrammingLanguage updateProgrammingLanguage = getById(programmingLanguage.getId());
        updateProgrammingLanguage.setName(programmingLanguage.getName());
    }
}
