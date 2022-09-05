import java.util.List;

public interface ExtratorDeConteudo {
     
        /**
         * @param json
         * @return
         */
        List<Conteudo> extraiConteudos(String json);
}