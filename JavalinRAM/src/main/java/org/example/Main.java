package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> {

            String serverId = System.getenv("SERVER_ID");

            if (serverId == null) {
                serverId = "UNKNOWN";
            }

            ctx.result("Resposta do Servidor ID: " + serverId);

        });
    }
}
/*
Exemplo de comando 'for' no Windows para fazer 100 requisições sequenciais
for /l %i in (1,1,100) do curl http://localhost:7000/

Para rodar o Jconsole: "C:\Program Files\Java\jdk-25\bin\jconsole.exe"

docker run -d --name meu-nginx -p 80:80 -v "%cd%/nginx.conf:/etc/nginx/nginx.conf" nginx
 */

