# APP

Para executar a app, deve-se executar o comando.

```sh
$ cd pastaOrigem
$ mvn clean install
```
após iniciar a app pelo arquivo jar

```sh
$ cd pastaOrigem/target 
$ java -jar app-0.0.1-SNAPSHOT.jar
```
após iniciado a aplicação, acessar via browser o url
  - http://localhost:8080/texoit/
  - Ao acessar a url o sistema ira popular a base de dados com o arquivo **movielist.csv**

# Para Obter o(s) vencedor(es), informando um ano

  - acessar a URL http://localhost:8080/texoit/winners/{ano}
    - no qual {ano} é a chave de busca desejada
  
# Para Obter os anos que tiveram mais de um vencedor

  - acessar a URL http://localhost:8080/texoit/winners/year
    
# Para Obter a lista de estúdios, ordenada pelo número de premiações;

  - acessar a URL http://localhost:8080/texoit/winners/studio
               
# Para Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois prêmios mais rápido;

  - acessar a URL http://localhost:8080/texoit/winners/producer/interval
               
# Para Remover um filme 

  - Enviar uma requisição **DELETE** à url http://localhost:8080/texoit/movie/del/
    - no qual deve se informar o id desejado para remoção como parametro
    - caso seja um filme vencedor, o mesmo não deverá ser excluido.
