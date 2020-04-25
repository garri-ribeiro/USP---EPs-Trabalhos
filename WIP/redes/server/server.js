// Requisita o framework para Node.js
var app = require('express')();

// Requisita a biblioteca http
var http = require('http').Server(app);

// Requisita  biblioteca socket.io
var io = require('socket.io')(http);

// Lista de usuario conectados a sala
var clients = {}; 

app.get('/', function(req, res){
  res.send('Servidor ativo');
});

// Funções "escutadas" pelo WebSocket utilizando a biblioteca socket.io
io.on("connection", function (client) {
    
    // Função JOIN para quando o usuário entra na sala
    client.on("join", function(name){

    	console.log("Entrou na sala: " + name);
        clients[client.id] = name;

        // Retorna a mensagem para o usuario que entrou na sala
        client.emit("update", "Você foi conectado conectado a sala");

        // Retorna a mensagem para todos conectados ao servidor que o usuario X 
        // acabou de entrar na sala
        client.broadcast.emit("update", name + " entrou na sala")
    });

    // Função SEND para quando o usuário envia uma mensagem
    client.on("send", function(msg){

        // Retorna a mensagem para todos usuário que um usuário X enviou
        client.broadcast.emit("chat", clients[client.id], msg);
    });

    // Função DISCONNECT para avisar que o usuário saiu da sala
    client.on("disconnect", function(){
        console.log("Desconectado: " + clients[client.id]);

        // Retorna a mensagem que o usuario X foi desconectado do servidor
        io.emit("update", clients[client.id] + " saiu da sala");

        // Remove o usuario X da lista de pessoas onlines
        delete clients[client.id];
    });
});

// Conexão aberta na porta 3000
http.listen(3000, function(){
  console.log('Recebendo mensagens pela porta 3000');
});
