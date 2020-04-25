DOCUMENTAÇÃO GRPC

* Qualquer duvida, essa é a documentação oficial do framework: https://grpc.io/docs/quickstart/python.html

- Para rodar o código é necessário ter -> Python 2.7 ou Python 3.4+

- Instalar as bibliotecas:

        pip install grpcio
        pip install grpcio-tools
        pip install protobuf
        pip install matplotlib
        pip install numpy

- Atualizar o IP no co código client.py e serve.py:
    * Se for rodar em localhost utilizar o IP -> localhost
    * alterar o IP na linha 14 (client.py)


- Para a execução dos testes, primeiramente deve ser executado o código do servidor, com o comando:

        python server.py

- No código do cliente, estão as chamadas de função para o servidor assim como as funções de cálculo de tempo e geração de gráficos:

	    python client.py


As quatro funções de teste serão executadas 5 vezes cada. Alterações de parâmetros nas chamadas de função podem ser alterados diretamente no código client.py.