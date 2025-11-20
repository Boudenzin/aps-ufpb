package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class LoadTester {

    private static final String ENDPOINT = "http://localhost/alomundo";
    private static final int NUM_REQUESTS = 100;

    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        long startTime = System.currentTimeMillis();
        int successCount = 0;

        System.out.println("Iniciando o Teste de Carga (" + NUM_REQUESTS + "requisições)...");

        for (int i = 0; i < NUM_REQUESTS; i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ENDPOINT))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    successCount++;
                } else {
                    System.out.println("Erro na requisição" + i + ": Status " + response.statusCode());
                }
            } catch (Exception e) {
                System.out.println("Erro de conexão na requisição " + i +": " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Resultado do Teste");
        System.out.println("Total de Requisições: " + NUM_REQUESTS);
        System.out.println("Requisições Bem-Sucedidas: " + successCount);
        System.out.println("Tempo Total Gasto: " + duration + "ms");
        System.out.println("Taxa de Transferência: " + (NUM_REQUESTS * 1000.0 /duration) + "req/s");
    }
}
