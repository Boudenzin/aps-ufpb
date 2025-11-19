package org.example;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;

import java.io.FileNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        var app = Javalin.create()
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070)
                .stop();

        // Javalin.create(config -> {
        //    config.jetty.modifyServer(server -> server.setStopTimeout(5_000)); // wait 5 seconds for existing requests to finish
        //});

        // Esse create pode ter um config que me entrega u
        /*
        Esse create pode ter um config que me entrega um
        objeto de configuração, e dentro dele eu digo o que quero
        configurar, registrar dados globais como appdata, configurar logs
         */

        app.before(ctx -> {}); // Roda antes de tudo

        app.after(ctx -> {}); // Roda depois de tudo

        app.afterMatched(ctx -> {}); // Só roda se a solicitação for respondida
        app.beforeMatched(ctx -> {}); // Só roda se a requisição corresponde realmente a uma rota existente

        app.before(ctx -> {
            System.out.println("Sempre roda, mesmo em 404");
        });

        app.beforeMatched(ctx -> {
            System.out.println("Só roda se a rota foi encontrada!");
        });

        app.get("/output", ctx -> {// Algum metodo ou codigo
        });

        app.post("/input", ctx -> {// Algum código ou metodo
        });

        app.get("/hello/{name}", ctx -> {
            // /hello/Ana -> "Ana"
            ctx.result("Hello: " + ctx.pathParam("name"));
        });

        app.get("/hello/<name>", ctx -> {
            // /hello/Ana/Souza -> "Ana/Souza"
            ctx.result("Hello: " + ctx.pathParam("name"));
        });

        // Use dois pontos, pra diszer que algo é variavel, como /:item


        /*
        ctx.cookieStore().set(key, value);   // guarda um valor com uma chave
        ctx.cookieStore().get(key);          // recupera o valor pela chave
        ctx.cookieStore().clear();           // apaga tudo
         */

        app.ws("/websocket/{path}", ws -> {
            ws.onConnect(ctx -> System.out.println("Connected"));
        }); // Esse cara aqui é um websocket. Quando alguem se conecta nele, ele emite "Connected"


        // Sim, existe um WsAfter e um WsBefore

        /*
        ws.onConnect(WsConnectContext)
        ws.onError(WsErrorContext)
        ws.onClose(WsCloseContext)
        ws.onMessage(WsMessageContext)
        ws.onBinaryMessage(WsBinaryMessageContext)
         */

        // Final: eventos de ciclo de vida: Domingo
        // Sabado: grupos de manipuladores
        // Sexta: Fim do WS


        // Pra mais fácil, as opções de CRUD, lembra de banco de dados

        /*
        interface CrudHandler {
            getAll(ctx)
            getOne(ctx, resourceId)
            create(ctx)
            update(ctx, resourceId)
            delete(ctx, resourceId)
        }
         */


        int age = ctx.queryParamAsClass("age", Integer.class)
                .check(a -> a > 0, "Age must be positive")
                .get();

        app.exception(ValidationException.class, (e,ctx) -> {});


        app.exception(FileNotFoundException.class, (e, ctx) -> {
            ctx.status(404);
        }).error(404, ctx -> {
            ctx.result("Página não encontrada 404");
        });

        /*
        Javalin.create(config -> {
            config.http              // Configuração da camada HTTP: etags, tamanho máximo da requisição, timeout, etc
            config.router            // Configuração de roteamento: caminho base, tratamento de barras (/), etc
            config.jetty             // Configuração do servidor Jetty embutido
            config.staticFiles       // Configuração de arquivos estáticos e webjars
            config.spaRoot =         // Configuração das raízes de Single Page Application (SPA)
            config.requestLogger     // Configuração do logger de requisições: logs HTTP e WebSocket
            config.bundledPlugins    // Configuração de plugins integrados: habilitar plugins padrão ou adicionar personalizados
            config.events            // Configuração de eventos
            config.vue               // Configuração do plugin Vue
            config.contextResolver   // Configuração da implementação de resolução de contexto
            config.validation        // Configuração padrão do validador
            config.useVirtualThreads // Usar *virtual threads* (baseado no Project Loom do Java)
            config.showJavalinBanner // Mostrar o banner do Javalin nos logs
            config.startupWatcherEnabled // Exibir aviso se a instância não iniciar após 5 segundos
            config.pvt               // "Privado", só use se souber exatamente o que está fazendo

            config.events(listenerConfig)     // Adicionar um listener de eventos
            config.jsonMapper(jsonMapper)     // Definir um JsonMapper personalizado
            config.fileRenderer(fileRenderer) // Definir um FileRenderer personalizado
            config.registerPlugin(plugin)     // Registrar um plugin
            config.appData(key, data)         // Armazenar dados na instância do Javalin
        });

         */


    }
}