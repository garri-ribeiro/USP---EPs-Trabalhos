// https://servicodados.ibge.gov.br/api/docs/localidades?versao=1#api-Municipios-estadosUFMunicipiosGet

// Array de sigla de estados
var states = [
    {
        "id": 12,
        "sigla": "AC"
    },
    {
        "id": 27,
        "sigla": "AL"
    },
    {
        "id": 13,
        "sigla": "AM"
    },
    {
        "id": 16,
        "sigla": "AP"
    },
    {
        "id": 29,
        "sigla": "BA"
    },
    {
        "id": 23,
        "sigla": "CE"
    },
    {
        "id": 53,
        "sigla": "DF"
    },
    {
        "id": 32,
        "sigla": "ES"
    },
    {
        "id": 52,
        "sigla": "GO"
    },
    {
        "id": 21,
        "sigla": "MA"
    },
    {
        "id": 31,
        "sigla": "MG"
    },
    {
        "id": 50,
        "sigla": "MS"
    },
    {
        "id": 51,
        "sigla": "MT"
    },
    {
        "id": 15,
        "sigla": "PA"
    },
    {
        "id": 25,
        "sigla": "PB"
    },
    {
        "id": 26,
        "sigla": "PE"
    },
    {
        "id": 22,
        "sigla": "PI"
    },
    {
        "id": 41,
        "sigla": "PR"
    },
    {
        "id": 33,
        "sigla": "RJ"
    },
    {
        "id": 11,
        "sigla": "RO"
    },
    {
        "id": 24,
        "sigla": "RN"
    },
    {
        "id": 14,
        "sigla": "RR"
    },
    {
        "id": 43,
        "sigla": "RS"
    },
    {
        "id": 42,
        "sigla": "SC"
    },
    {
        "id": 28,
        "sigla": "SE"
    },
    {
        "id": 35,
        "sigla": "SP"
    },
    {
        "id": 17,
        "sigla": "TO"
    }
]

// Array de especialidades medicas
var medical_specialitys = [
    { "id": 1, "nome": "Acupuntura" },
    { "id": 2, "nome": "Imunologista" },
    { "id": 3, "nome": "Anestesiologia" },
    { "id": 4, "nome": "Angiologia" },
    { "id": 5, "nome": "Cardiologia" },
    { "id": 6, "nome": "Cirurgia Cardiovascular" },
    { "id": 7, "nome": "Cirurgia da Mão" },
    { "id": 8, "nome": "Cirurgiade cabeça e pescoço" },
    { "id": 9, "nome": "Cirurgiado Aparelho Digestivo" },
    { "id": 10, "nome": "Cirurgia Geral" },
    { "id": 11, "nome": "Cirurgia Pediátrica" },
    { "id": 12, "nome": "Cirurgia Plástica" },
    { "id": 13, "nome": "Cirurgia Torácica" },
    { "id": 14, "nome": "Cirurgia Vascular" },
    { "id": 15, "nome": "Coloproctologia" },
    { "id": 16, "nome": "Dermatologia" },
    { "id": 17, "nome": "Endocrinologia" },
    { "id": 18, "nome": "Endoscopia" },
    { "id": 19, "nome": "Gastroenterologia" },
    { "id": 20, "nome": "Genéticamédica" },
    { "id": 21, "nome": "Geriatria" },
    { "id": 22, "nome": "Ginecologia" },
    { "id": 23, "nome": "Hematologia" },
    { "id": 24, "nome": "Homeopatia" },
    { "id": 25, "nome": "Infectologia" },
    { "id": 26, "nome": "Mastologia" },
    { "id": 27, "nome": "Medicinade Família e Comunidade" },
    { "id": 28, "nome": "Medicina do Trabalho" },
    { "id": 29, "nome": "Medicina do Tráfego" },
    { "id": 30, "nome": "Medicina Esportiva" },
    { "id": 31, "nome": "Medicina física e reabilitação" },
    { "id": 32, "nome": "Medicina Intensiva" },
    { "id": 33, "nome": "Medicina Nuclear" },
    { "id": 34, "nome": "Médico sanitarista" },
    { "id": 35, "nome": "Nefrologia" },
    { "id": 36, "nome": "Neurocirurgia" },
    { "id": 37, "nome": "Neurologia" },
    { "id": 38, "nome": "Nutrologia" },
    { "id": 39, "nome": "Obstetrícia" },
    { "id": 40, "nome": "Oftalmologia" },
    { "id": 41, "nome": "Ortopedia" },
    { "id": 42, "nome": "Otorrinolaringologia" },
    { "id": 43, "nome": "Patologia" },
    { "id": 44, "nome": "PatologiaClínica" },
    { "id": 45, "nome": "Pediatria" },
    { "id": 46, "nome": "Pneumologia" },
    { "id": 47, "nome": "Psiquiatria" },
    { "id": 48, "nome": "Radiologia" },
    { "id": 49, "nome": "Radioterapia" },
    { "id": 50, "nome": "Reumatologia" },
    { "id": 51, "nome": "Urologia" }
]


// Insere especialidades medicas no chosen select
for (var key in medical_specialitys) {
    $('#index-select-medical-speciality').append('<option value="' + medical_specialitys[key].id + '">' + medical_specialitys[key].nome + '</option>');   
}
$('#index-select-medical-speciality').trigger("chosen:updated")



// Insere estados no chosen select
for (var key in states) {
    $('#index-select-states').append('<option value="' + key + '">' + states[key].sigla + '</option>');
}
$('#index-select-states').trigger("chosen:updated")


// Requisita da API do IBGE quando escolhido um estado
$('#index-select-states').on('change', function (evt, params) {
    
    var id_state = states[$("#index-select-states").chosen().val()].id;
    
    $('#index-select-citys').empty();
    
    // Requisição
    $.get("http://servicodados.ibge.gov.br/api/v1/localidades/estados/"+ id_state +"/municipios", function (data) {
    
    var citys = data;
    for (var key in citys) {
        $('#index-select-citys').append('<option value="' + citys[key].nome + '">' + citys[key].nome + '</option>');
    }
    
    $('#index-select-citys').trigger("chosen:updated")  
    });
});

$(function () {
    $('#form-search-doctors').submit(function (e) {
        e.preventDefault(); 
        
        var request = {
            'speciality': $("#index-select-medical-speciality").chosen().val(),
            'state': states[$("#index-select-states").chosen().val()].sigla,
            'city': $("#index-select-citys").chosen().val()
        }
        
        // var request = {
        //     "city": "São Paulo",
        //     "state": "SP",
        //     "speciality": "1"
        // }
        
        $.ajax({
            url: 'http://188.166.101.143:5000/doctors',
            type: 'GET',
            data: request,
            success: function (data) {
                if(data){
                    
                    var myNode = document.getElementById("doctors-list");
                    myNode.innerHTML = '';
                    data.medicos.forEach(medico => {
                        
                        htmlDoctorList = '<div id="element-doctor-list" class="col-lg-3 col-md-6 col-sm-6 col-xs-12">'+
                        '<div class="contact-list">' +
                        '<div class="contact-win">'+
                        '<div class="contact-img">'+
                        '<img src="img/doctor.png" alt="" />'+
                        '</div>'+
                        '</div>'+
                        '<div class="contact-ctn">'+
                        '<div class="contact-ad-hd">'+
                        '<h2>'+medico.nome+'</h2>'+
                        '<p class="ctn-ads">'+medico.logradouro+','+medico.cidade+' - '+medico.estado+'</p>'+
                        '</div>'+
                        '<p>'+medico.bio+'</p>'+
                        '</div>'+
                        '<div>'+
                        '<a onclick="goDoctorAppointment('+medico.id_usuario+')'+'" href="#" class="btn btn-success notika-btn-success waves-effect">Detalhes</a>'+
                        '</div>'+
                        '</div>'+
                        '</div>';
                        
                        $('#doctors-list').append(htmlDoctorList)
                    });
                }
            },
            error: function(){
                var myNode = document.getElementById("doctors-list");
                myNode.innerHTML = '<div id="element-doctor-list" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">' +
                '<h1>Não foi encontrado nenhum médico nessa região</h1>'+
                '</div>';
            }
        });
        
    });
});

function goDoctorAppointment(id_doctor){
    
    localStorage.setItem("id_doctor", id_doctor);
    localStorage.setItem("speciality_doctor", medical_specialitys[$("#index-select-medical-speciality").chosen().val()-1].nome);
    window.location.href = 'doctor-appointment.html'
}
