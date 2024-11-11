package zambom.pf.prova_final.feedback;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Feedback {
    @Id
    private String id;
    private String queixa; //equivalente à nome
    private String descricao;
    private Double nota;
    private String autor;

}
