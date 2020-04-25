$(".navbar-user").hide();
$(".navbar-doctor").hide();

    
    var user;
    $.ajax({
        url: 'http://188.166.101.143:5000/user',
        type: 'GET',
        data: { id_usuario: localStorage.getItem("id_login") },
        success: function (data) {
            user = data.usuario;
            $('#name-navbar').append(user.nome);

            if(user.tipo_usuario == 'paciente') {
                $(".navbar-user").show();
            }
            else {
                $(".navbar-doctor").show();
            }
        }
    }); 