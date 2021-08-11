# jogodavelha
Projeto API de JOGO da VELHA

o serviço recebe um JSON com um jogo da velha e faz a verificaçao se houve ou nao vencedor.

#GIT REPOSITORY
https://github.com/regifelix/jogodavelha.git

#SONAR
mvn sonar:sonar -Dsonar.organization=regifelix -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=e4dc5bf94d5cc712620e29ddcd7cdf09592268c7

Relatório do Sonar de análise do código disponível na url abaixo:
https://sonarcloud.io/dashboard?id=br.com.regifelix:jogodavelha


#Documentação Swagger da API disponível
http://ecs-apploadbalancer-jogodavelha-575760530.sa-east-1.elb.amazonaws.com/swagger-ui.html


Exemplo da chamada usando CURL via terminal:
curl -X POST "http://ecs-apploadbalancer-jogodavelha-575760530.sa-east-1.elb.amazonaws.com/v1/jogodavelha" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"jogo\": [ \"XOO\", \"OXO\", \"OOX\" ]}"

#Container Docker 
Arquivo Dockerfile para criação do container encontra-se dentro do diretório raiz da aplicação

Estando no diretório raiz do projeto, onde se encontra o arquivo Dockerfile, execute o seguinte comando:
docker build -t jogodavelha .

Iniciar o container:
docker run -p 8080:8080 jogodavelha


#JMeter Stress test
Executar o Jmeter
O Plano de testes do JMeter esta no arquivo jmeter-testplan.jmx do diretório /src/man/resources da aplicação
O plano de testes esta configurado para gerar 1000 threads, com um loop de 100 execuções a cada 1s.
mvn jmeter:jmeter
O relatório dos testes será gerado no diretorio /target/jmeter/reports
Observação o plano de teste esta sendo executado contra a url do aws da aplicação.


#EXECUÇÃO TESTES
mvn test

#RELATORIO DE COBERTURA DOS TESTES
mvn package
O relatório de cobertura sera gerado no diretório /target/site/jacoco


#Hospedagem na cloud pública AWS

Foi feita a hospedagem na AWS na URL:
http://ecs-apploadbalancer-jogodavelha-575760530.sa-east-1.elb.amazonaws.com/

Abaixo segue uma breve descrição das configurações realizadas na AWS:

	Foi realizadao a configuração para publicação automática no repositório do AWS ECS, DE de forma que sempre que gerado um novo jar compilado basta subir no AWS Code Commit que uma nova imagem é geraa e publicada.

A seguir faço uma breve descrição dos passos realizados para automatizar esse processo:

	Configurado um repositório git no "AWS Code Commit" e o repositório foi clonado numa pasta na maquina local de forma que fique nessa pasta o arquivo Dockerfile, buildspec.yml e o última versão do jar compilado da aplicação
	Criado o buildspec.yml para fazer Building/Push da imagem do container e configurado no serviço Code Build no AWS para a execução do buildspec.yml
	
	Foi criado o Pipeline no AWS CodePipeline  de forma que qualquer publicação de commit no repositório git AWS da maquina local, ao fazer push o pipeline ira rodar o codecommit automaticamente e executar o buildspec.yml para publicar uma nova imagem no Repositorio AWS ECS, por exemplo ao commitar uma nova versao do jogodavelha.jar o processo de execução da build da imagem e publicação será automatico.

Configuração do Cluster AWS e do Loadbalance:
	
Cluster AWS
	Cluster do tipo FARGATE
	Task Definition:
     		memory (GB): 1Gb
	 	CPU (vCPU): 0,5 CPU

Foi configurado o Loadbalance no AWS com serviço auto escalável na seguinte fonfiguração:

 Quando a utilização da CPU for maior que 10% por um periodo consecutivo de 1 minuto será adicionado mais uma task em execução no cluster, até o limite máximo de 3 tasks.
  Quando ficar com menos de 10% de utilização do CPU por um periodo de 1 minuto será removido uma task.
