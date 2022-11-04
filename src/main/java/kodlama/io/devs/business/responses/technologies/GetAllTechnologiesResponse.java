package kodlama.io.devs.business.responses.technologies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTechnologiesResponse {
    private int id;
    private int programmingLanguageId;
    private String programmingLanguageName;
    private String name;
}
