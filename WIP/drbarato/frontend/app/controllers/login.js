function login() {
    console.log("OOOOOOOOOOOI");
    
    var email = $('#login-email').val();
    var password = $('#login-password').val();


    var request = {
        "email" : email,
        "password": password
    }

    $.ajax({
        url: 'http://188.166.101.143:5000/login',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(request),
        success: function (data) {
            id_usuario = data[0].id_usuario;
            console.log("MAAOE, CERTA RESPOSTAT");
            localStorage.setItem("id_login", id_usuario);
            window.location.href = 'app/home.html'
            
        },
        error: function(data) {
            console.log("NAO FOI DESSA VEZ ABIGO")
        }
    });
}
