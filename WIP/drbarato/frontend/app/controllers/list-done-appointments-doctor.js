window.onload = function() {
    var id_usuario = localStorage.getItem("id_login")

    console.log("OOOOOOOOOOOOOOI");
    
    
    $.ajax({
        url: 'http://188.166.101.143:5000/doctor/history',
        type: 'GET',
        data: { "id_medico": id_usuario },
        success: function (data) {
            console.log("OIOIOIOI");
            
            consultas = data.consultas;
            
            consultas.forEach(consulta => {

                consulta.formatDate = moment(consulta.inicio).format("DD/MM/YYYY");
                consulta.formatHour = moment(consulta.inicio).format("h:mm");
                if(consulta.voucher == null) {
                    consulta.voucher = "";
                }
                if(consulta.ID_paciente) {
                    $.ajax({
                        url: 'http://188.166.101.143:5000/user',
                        type: 'GET',
                        data: { id_usuario: consulta.ID_paciente},
                        success: function (data) {
                            user = data.usuario;

                            htmlScheduleList = "<tr>" +
                                "<td>" + user.nome + "</td>" +
                                "<td>" + consulta.voucher + "</td>" +
                                "<td>" + consulta.valor + "</td>" +
                                "<td>" + consulta.formatDate + ", " + consulta.formatHour + "</td>" +
                                "</tr>";
                            $('#schedule-list').append(htmlScheduleList);
                        }
                    }); 
                }
                else {
                    htmlScheduleList = '<tr>' +
                        '<td>'+'<a href="#"><span class="label label-info">Horário aberto</span></a>'+'</td>' +
                        '<td>'+consulta.voucher+'</td>'+
                        '<td>'+consulta.valor+'</td>'+
                        '<td>'+consulta.formatDate+', '+consulta.formatHour+'</td>'+
                    '</tr>';
                    $('#schedule-list').append(htmlScheduleList);
                }


            });
        }
    });
};

