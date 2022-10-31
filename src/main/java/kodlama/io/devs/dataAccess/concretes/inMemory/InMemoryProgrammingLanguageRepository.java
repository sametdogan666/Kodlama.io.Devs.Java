package kodlama.io.devs.dataAccess.concretes.inMemory;

import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

import java.util.List;

public class InMemoryProgrammingLanguageRepository implements ProgrammingLanguageRepository {
    @Override
    public List<ProgrammingLanguage> getAll() {
        return null;
    }

    @Override
    public ProgrammingLanguage getById(int id) {
        return null;
    }

    @Override
    public void add(ProgrammingLanguage programmingLanguage) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(ProgrammingLanguage programmingLanguage) {

    }
}
