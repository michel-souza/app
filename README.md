# APP

Para executar a app, deve-se iniciar o arquivo jar 

```sh
$ cd pastaOrigem
$ java -jar app.jar
```
após iniciado a aplicação, acessar via browser o url
  - http://localhost:8080/textoit/
  Ao acessar a url o sistema ira popular a base de dados com o arquivo **movielist.csv**

# Para Obter o(s) vencedor(es), informando um ano

  - acessar a URL http://localhost:8080/textoit/winners/{ano}
    - no qual {ano} é a chave de busca desejada
  
# Para Obter os anos que tiveram mais de um vencedor

  - acessar a URL http://localhost:8080/textoit/winners/year
    
# Para Obter a lista de estúdios, ordenada pelo número de premiações;

  - acessar a URL http://localhost:8080/textoit/winners/studio
               
# Para Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois prêmios mais rápido;

  - acessar a URL http://localhost:8080/textoit/winners/producer/interval
               
# Para Remover um filme 

  - acessar a url http://localhost:8080/textoit/movie/del
    - caso seja um filme vencedor, o mesmo não deverá ser excluido.