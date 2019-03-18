# Tagarela

Projeto para transcrever audios utilizando a API da Google

## Para usar o projeto

1. Tenha o maven instalado
2. Execute o `mvn install`
3. Dentro do diretório `target/` será gerado um `*.zip` com o executável
4. Desempacote o `.zip` e coloque a chave de segurança no mesmo diretório
5. Execute o arquivo `./tagarela.sh` passando como parâmetro `-u` a URL do arquivo do google `gs://[bucket]/arquivo.wav`