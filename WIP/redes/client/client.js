
$(document).ready(function(){
	
	
	// Realiza a conexão com servidor pela porta 3000 pela biblioteca do socket.io
	// que foi requisitada no index.html
    var socket = io.connect("http://localhost:3000");
	
	
	var ready = false;

	// Função para realizar ações na página do chat ao submeter o seu apelido
    $("#submit").submit(function(e) {
		e.preventDefault();
		$("#nick").fadeOut();
		$("#chat").fadeIn();
		var name = $("#nickname").val();
		var time = new Date();
		$("#name").html('Olá,' + name);
		$("#time").html('Logado às: ' + time.getHours() + ':' + time.getMinutes());

		ready = true;

		// Enviar para servidor um objeto que o usuário entrou na sala
		socket.emit("join", name);

	});

	// Função para realizar ações quando o usuário escreve uma mensagem no chat
	$("#textarea").keypress(function(e){
        if(e.which == 13) {
        	var text = $("#textarea").val();
        	$("#textarea").val('');
        	var time = new Date();
			$(".chat").append('<div class="columns"> <div class="column is-4 is-offset-8"> <article class="message is-dark"> <div class="message-body"> <p><strong>' + $("#nickname").val() + '</strong> <span class="content is-small"> às' + time.getHours() + ':' + time.getMinutes() + '</span></p> <p>' + text + '</p> </div> </article> </div> </div>');
			
			// Envia para o servidor um objetvo que o usuário enviou uma mensagem
			socket.emit("send", text);

        }
    });

	// Recebe mensagem de alguem que entrou/saiu da sala 
    socket.on("update", function(msg) {
    	if (ready) {
    		$('.chat').append('<li class="notification is-warning"><strong>' + msg + '</li>')
    	}
    }); 

	// Recebe mensagem dos outros usuarios
    socket.on("chat", function(client,msg) {
    	if (ready) {
	    	var time = new Date();
			$(".chat").append('<div class="columns"> <div class="column is-4"> <article class="message"> <div class="message-body"> <p><strong>' + client + '</strong> <span class="content is-small"> às' + time.getHours() + ':' + time.getMinutes() + '</span></p> <p>' + msg + '</p> </div> </article> </div> </div>');
    	}
    });




});

