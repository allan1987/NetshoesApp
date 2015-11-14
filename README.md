# NETSHOES APP  
###
###
Nome do Autor: Allan Ariel  
Email: allan.ariel1987@gmail.com  
Github: https://github.com/allan1987  
BitBucket: https://bitbucket.org/allan1987/

Para a criação deste aplicativo, buscou-se utilizar algumas bibliotecas cujo intuito é o de aumentar a produtividade, reuso de código e atender ao estado da arte na tecnologia.

Ao analisar o código-fonte, pode-se observar o uso:
- Da biblioteca EventBus, representando o padrão arquitetural Publisher-Subscriber para comunicação eficiente entre os componentes Android.
- Da classe EventManager, representando o padrão de projeto Singleton e responsável por gerenciar todas as trocas de mensagens entre objetos através do uso de eventos.
- Da biblioteca Dagger para realizar a injeção de dependências, utilizada principalmente no RestClient.
- Da biblioteca Android Support AppCompat com intuito de trabalhar também com Material Design para aparelhos com versões anteriores ao Lollipop.
- Da biblioteca Android Support CardView, provendo um componente mais elaborado para cada item da lista de produto.
- Da biblioteca Android Support RecyclerView, pois os elementos mudam em tempo de execução com base em eventos de ação do usuário ou da rede. Trabalha com cache de elementos para utilizações posteriores, apresentando melhor desempenho que outras soluções.
- Da biblioteca Android Annotations para produtividade, principalmente em relação aos gerenciamento de elementos de tela.
- Da biblioteca Retrofit, provendo uma interface simples para comunicação com serviços REST, permitindo a implantação do padrão arquitetural Client-Server.
- Da biblioteca Jackson Annotations como responsável pelo mapeamento JSON-Objeto.
- Da biblioteca Glide para carregamento assíncrono de imagens, providenciando cache das mesmas.
- Da biblioteca Robotium, ao permitir a realização de alguns testes funcionais feitos no projeto.
- Da biblioteca Unit para testes unitários.
- De Fragments, provendo uma sessão modular com ciclo de vida próprio e contribuindo para posterior reuso.
