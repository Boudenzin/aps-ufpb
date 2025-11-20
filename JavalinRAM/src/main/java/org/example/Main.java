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

        app.get("/alomundo", ctx -> {
            String serverId = System.getenv("SERVER_ID");
            ctx.result("Alo Mundo respondido por:" + serverId);
        });
    }
}
/*
Exemplo de comando 'for' no Windows para fazer 100 requisições sequenciais
for /l %i in (1,1,100) do curl http://localhost:7000/

Para rodar o Jconsole: "C:\Program Files\Java\jdk-25\bin\jconsole.exe"

Para pausar um container use docker stop

Para terminar um container use docker rm

O ponto no docker significa diretorio atual, ele
pega todos os arquivos e pastas na pasta atual e
envia para o daemon do Docker

docker build -t servico-ram-final .

docker run -d --name javalin-1 -e SERVER_ID=8081 -p 8081:7000 servico-ram-final


docker run -d --name meu-nginx -p 80:80 -v "C:\Users\romil\IdeaProjects\aps-ufpb\JavalinRAM\nginx.conf:/etc/nginx/nginx.conf" nginx
 */

