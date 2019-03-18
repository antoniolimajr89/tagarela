# Tagarela

Projeto para transcrever audios utilizando a API da Google

## Para usar o projeto

1. Tenha o maven instalado
2. Execute o `mvn install`
3. Dentro do diret�rio `target/` ser� gerado um `*.zip` com o execut�vel
4. Desempacote o `.zip` e coloque a chave de seguran�a no mesmo diret�rio
5. Execute o arquivo `./tagarela.sh` passando como par�metro `-u` a URL do arquivo do google `gs://[bucket]/arquivo.wav`