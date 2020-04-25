var medico;
var schedule;


window.onload = function() {

   



    var id_doctor = localStorage.getItem("id_doctor")
    var speciality_doctor = localStorage.getItem("speciality_doctor")
    $("#doctor-speciality").text(speciality_doctor);
    console.log("IDODOTOR" + id_doctor);

    
    
    $.ajax({
        url: 'http://188.166.101.143:5000/doctor',
        type: 'GET',
        data: {"id_usuario": id_doctor },
        success: function (data) {
            medico = data.medico[0];

            address = medico.logradouro + " - " + medico.bairro + ",  CEP:  " + medico.CEP;
            
            $("#doctor-name").text(medico.nome);
            $("#doctor-phone").text(medico.telefone);
            $("#doctor-address").text(address);
            $("#doctor-bio").text(medico.bio);

            initMap(address)
            
        }
    });
    
    $.ajax({
        url: 'http://188.166.101.143:5000/doctor/history',
        type: 'GET',
        data: { "id_medico": id_doctor },
        success: function (data) {
            schedule = data.consultas;

            schedule.forEach(schedule => {

                if(!schedule.ID_paciente) {
                    
                    schedule.formatDate = moment(schedule.inicio).format("DD/MM/YYYY");
                    schedule.formatHour = moment(schedule.inicio).format("h:mm");
    
                    htmlScheduleList = '<li role = "presentation">' +
                        '<a onclick="arrangeAppointment(' + schedule.ID_agendamento + ',\'' + schedule.formatDate + '\',\'' + schedule.formatHour+'\')">'+schedule.formatDate+' | Horário: '+schedule.formatHour+'<span class="pull-right"> Valor: R$ '+schedule.valor+'</span></a>' +
                        '</li >'     
    
                    $('#schedule-list').append(htmlScheduleList);
                }

            });

                    
                                    
        }
    });


};

function arrangeAppointment(id_consulta,date,hour) {
    console.log(id_consulta);
    console.log(localStorage.getItem("id_login"));
    
    swal({
        title: "Gostaria de marcar a consulta com "+medico.nome+" ?",
        text: "A consulta está marcada para o dia <h2><strong>"+date+" às "+hour+"</strong></h2>",
        // type: "info",
        showCancelButton: true,
        confirmButtonText: "Sim",
        cancelButtonText: "Não",
    }).then(function (isConfirm) {

        if(isConfirm) {
            var request = {
                "id_agendamento": "\""+id_consulta+"\"",
                "id_paciente": localStorage.getItem("id_login")
            }
    
            $.ajax({
                url: 'http://188.166.101.143:5000/schedule',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(request),
                success: function (data) {
                    swal("Consulta Marcada !!", "<h1>Número do voucher: <u>DFHFH</u></h1><p>O <strong>número do voucher para o email cadastrado.</strong> Para quaisquer dúvidas, ligue diretamente ao telefone do médico indicado na página da consulta.</p><p><u>Não esqueça de levar esse código no dia da consulta</u></p>", "success");
                },
                error: function (data) {
                    swal("Algo deu errado!", "Alguém ja marcou esse horário :(", "error");
                }
            });
        }

    });
}


function initMap(CEP) {
    var geocoder1 = new google.maps.Geocoder();
    setCenter(geocoder1, CEP);

}


function setCenter(geocoder, address) {
    geocoder.geocode({
        'address': address
    }, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            var map = new google.maps.Map(document.getElementById('map2'), {
                zoom: 16,
                center: results[0].geometry.location
            });

            var marker = new google.maps.Marker({
                position: results[0].geometry.location,
                map: map,
                title: address
            });
        } else {
            // alert('Geocode was not successful for the following reason: ' + status);
            // return null;
        }
    });
}