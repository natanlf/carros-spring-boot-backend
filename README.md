# Aluguel de carros - Back end

O app aluguel de carros foi criado para mostrar um pouco do meu trabalho na programação.
Inicialmente estava estudando um pouco de UML e fiz o seguinte diagrama de classes:

![Diagrama](/assets/diagrama-de-classe.png)

Para me manter atualizado e mostrar um pouco do trabalho, pensei em usá-lo para fazer um app Android.

Com o diagrama criado comecei a fazer o Web Service usando a ferramenta Spring Boot que é um framework que usa a linguagem JAVA. Fiz um sistema de segurança que usa JWT (JSON WEB TOKEN), assim o usuário precisa estar autenticado e autorizado para utilizar alguns recursos do sistema e para testar os recursos do Web Service utilizei a ferramenta POSTMAN e banco de dados em memória H2 e mysql. Foi feita uma integração com o S3 da Amazon, assim todas as imagens de perfil de clientes e carros se encontram no S3. Para envio de e-mail foi usado o SMTP da Google.

Ao longo do desenvolvimento tive que colocar senha e perfil para o cliente, assim ele tem uma conta e sabemos que tem autorização para acessar tal recurso.
O cliente pode alugar um carro e ao alugar o sistema envia um e-mail de confirmação.

O app android foi feito usando a ferramenta Ionic que é um framework para desenvolvimento de aplicativos que utiliza o Javascript, Typescript, pois usa o framework Angular que atualmente é mantido pela grande empresa Google.
Então vamos ver o app funcionando:

[Vídeo demonstrativo](https://www.youtube.com/watch?v=v-fXGdTJF_o)

No vídeo acima, criei uma conta, fiz uma locação e coloquei uma foto em meu perfil.

Podemos perceber que antes de carregar as imagens dos carros, aparece um Monza novo vermelho, isso acontece porque enquanto o aplicativo não carrega as imagens do S3 da Amazon ele mostra uma imagem como padrão, que é a do Monza.

Outra coisa que podemos perceber é que ao alugar o carro, demora alguns segundos, pois há um envio de e-mail de confirmação de locação:

![Email](/assets/email-app.png)

Aprendi bastante fazendo esse aplicativo mas não está disponível na web por causa dos gastos que eu teria com o servidor.
Abaixo está o link do projeto Front end que está no GitHub:

https://github.com/natanlf/carros-ionic-app

