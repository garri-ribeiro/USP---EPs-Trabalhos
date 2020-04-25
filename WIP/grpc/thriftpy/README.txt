DOCUMENTAÇÃO THRIFT

* Qualquer duvida, essa é a documentação oficial do framework: https://thriftpy.readthedocs.io/en/latest/

- Para rodar o código é necessário ter -> Python 2.7 ou Python 3.4+

- Instalar as bibliotecas:

    pip install thriftpy
    pip install cython thriftpy (se rodar em windows)
    pip install matplotlib
    pip install numpy

- Atualizar o IP no co código calc_client.py e calc_serve.py:
    * Se for rodar em localhost utilizar o IP -> 127.0.0.1 (não utilizar a string localhost)
    * alterar o IP na linha 15 (calc_client) e linha 33 (calc_serve)


- Para executar -> inicializar o server e depois executar o client :

    python calc_serve.py
    python calc_client.py