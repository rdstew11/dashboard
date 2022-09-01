
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableAutoConfiguration

public class SpotifyDashboard{

    private static boolean authenticate(String client_id, String response_type, String redirect_uri){
        String uri = "https://api.spotify.com/authorize";
        

        return true;
    }
    public static void main(String[] args){
        String client_id = "cb2a0b81852c42f8ac04df51eff19f6c";
        String response_type = "code";
        String redirect_uri = "";
        boolean response = authenticate(client_id, response_type, redirect_uri);
        System.out.println(response);
    }
}