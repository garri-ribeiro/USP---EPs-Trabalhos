## Instalação
 - **Python** (após instalação do python, instalar libraries abaixo)
	 - pip install flask
	 - pip install -U flask-cors
	 - pip install flask-mysql

## Executar

 1. Ter o banco instalado -> [informações banco de dados](https://github.com/garri-ribeiro/drBarato-database)
 2. Verificar a configurações do seu banco e alterar o MySQL configurations do arquivo **DAO.py**
~~~~
MySQL configurations
  
app.config['MYSQL_DATABASE_USER'] = 'root'
     
app.config['MYSQL_DATABASE_PASSWORD'] = 'password'
        
app.config['MYSQL_DATABASE_DB'] = 'drBarato'
        
app.config['MYSQL_DATABASE_HOST'] = 'localhost'	
~~~~

 3. Dentro da pasta do repositório, executar:
 	- **server.py**

## Dicas

- Usar postman para testar as rotas
- Verbo tipo **GET** o front envia **parametros**
- Verbo tipo **POST** o front envia **JSON**

## Rotas
- [x] /doctors 	(GET) - Traz lista de médicos
- [x] /doctor	(GET) - Traz um médico
- [x] /schedule (POST) - Marca uma consulta
	- Dados que o front envia: **id_medico, id_paciente, data_inicio, data_fim**
	- Criar um número de voucher aleatório para salvar no BD
	- O voucher tem que ser chave única junto com o id_agendamento criado pelo bd
	- Se a marcação foi um sucesso, retornar o o número do POST na requisição, caso não, enviar uma mensagem de erro avisando que esse horário não está disponível.
- [x] /login (POST) - Logar no sistema
	- Dados que o front envia: **username, password**
	- Em caso de sucesso retornar uma mensagem de credencias corretas, caso não, uma mensagem que as credenciais estão incorretas.
- [x] /register (POST) - Registrar no sistema
	- A primeiro momento só registrar usuário , médicos a gente coloca direto no sistema
	- Dados que o front envia: **username, password, email**
	- Em caso de sucesso retornar uma mensagem que foi cadastrado com sucesso
	- Precisamos conversar sobre os erros caso não é possível casdastrar um usuário.

	
## Outros repositórios
#### [dr-barato-frontend](https://github.com/garri-ribeiro/drBarato-frontend)
####  [dr-barato-database](https://github.com/garri-ribeiro/drBarato-database)
