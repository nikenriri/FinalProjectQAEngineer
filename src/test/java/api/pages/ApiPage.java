package api.pages;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiPage {
    private String baseUrl;
    private RequestSpecification request;
    public Response lastResponse;

    public void setBaseUrl(String url) {
        this.baseUrl = url;
        RestAssured.baseURI = this.baseUrl;
        // Inisialisasi request builder bawaan RestAssured Java
        this.request = RestAssured.given().header("Content-Type", "application/json");
    }

    public void setAuthToken() {
        // Menggunakan library io.github.cdimascio:dotenv-java pada Gradle
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String token = dotenv.get("APP_ID");
        if (token == null || token.isEmpty()) {
            token = System.getenv("APP_ID");
        }

        // Validasi akhir jika di kedua tempat tersebut memang benar-benar kosong
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("ERROR: Variabel APP_ID tidak ditemukan di file .env maupun Environment Variable Sistem!");
        }

        // Memasukkan token ke header request
        this.request.header("app-id", token);
    }

    public void createUser(String jsonBody) {
        this.lastResponse = this.request.body(jsonBody).post("user/create");
    }

    public void getAllUsers() {
        this.lastResponse = this.request.get("user");
    }

    public void updateUser(String userId, String jsonBody) {
        this.lastResponse = this.request.body(jsonBody).put("user/" + userId);
    }

    public void getAlltags(){
        this.lastResponse = this.request.get("tag");
    }

    public void deleteUser(String userId) {
        this.lastResponse = this.request.delete("user/" + userId);
    }
}


