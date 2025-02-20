# TestarPortas (tcp listener)

Programa criado com objetivo de validar portas abertas.
Ele utiliza a função ServerSocket para listar uma porta e aguardar uma conexão.
É ótimo para testar apontamentos IP e furos no NAT do roteador ou firewall.
Facilita para saber se o problema é no provedor ou configuracao incorreta no equipamento.

É indispensável o uso do Java JDK 21 ou mais recente para execução do código.
Não desenvolvi para testar portas abaixo de 1024 pois são portas reservadas de serviço, e normalmente estão
disponíveis em qualquer sistema operacional.

**Dependências** 🟩

Download JDK: https://www.oracle.com/br/java/technologies/downloads/

**Execução** 🟩

Basta ter o JDK instalado, extrair .zip e executar TestarPortas.jar

Como ele cria uma porta a nível de rede, compilar em uma versão .exe seria extremamente dificil e muitos SOs como Windows iriam achar
que é virus, então pretendo deixar apenas .jar, assim ele fica isolado na VM do próprio JVM e assim nenhum SO vai entender como ameaça.

Contatos:
Gabriel Marques Ferrarezi.
Linkedin: https://www.linkedin.com/in/gabriel-marques-ferrarezi-8a0913190/
