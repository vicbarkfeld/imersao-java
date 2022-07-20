import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexão HTTP e buscar os Top 250 filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString() );
        String body = response.body();
                
        // extrair só os dados que interessam (Titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); 
        
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[3mTítulo: \u001b[m\u001b[1m"+ filme.get("title")+ "\u001b[m");
            System.out.println(filme.get("image"));
            System.out.println("\u001b[45m\u001b[3mClassificação: \u001b[m\u001b[45m\u001b[1m"+ filme.get("imDbRating")+ " \u001b[m");
            int classificacao = (int) Float.parseFloat(filme.get("imDbRating"));
            String stars = "";            
            for(int i = 0; i<classificacao; i++) {
                stars = stars + "\u2B50";
            }
            System.out.println(stars);
            System.out.println();
            
           
        }
    }
}
