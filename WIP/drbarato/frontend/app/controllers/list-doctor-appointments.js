window.onload = function() {
    var id_usuario = localStorage.getItem("id_login");
    
    $.ajax({
        url: 'http://188.166.101.143:5000/user/history',
        type: 'GET',
        data: { "id_usuario": id_usuario },
        success: function (data) {
            
            consultas = data.consultas;

            consultas.forEach(consulta => {

                consulta.formatDate = moment(consulta.inicio).format("DD/MM/YYYY");
                consulta.formatHour = moment(consulta.inicio).format("h:mm");
                
                $.ajax({
                    url: 'http://188.166.101.143:5000/user',
                    type: 'GET',
                    data: { id_usuario: consulta.ID_medico },
                    success: function (data) {
                        user = data.usuario;

                        htmlScheduleList = "<tr>" +
                            "<td>" + user.nome + "</td>" +
                            "<td>" + consulta.voucher + "</td>" +
                            "<td>" + consulta.valor + "</td>" +
                            "<td>" + consulta.formatDate + ", " + consulta.formatHour + "</td>" +
                            "<td>" + user.telefone + "</td>" +
                            "</tr>";
                        $('#schedule-list').append(htmlScheduleList);
                    }
                });



            });
        }
    });
};

