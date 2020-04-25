from flask import Flask, jsonify, request, json,abort
from flaskext.mysql import MySQL
from werkzeug.security import generate_password_hash, check_password_hash
from hashids import Hashids

app = Flask(__name__)

mysql = MySQL()

salt = "20cd4702-6101-4fcd-a4d9-8f7ca14604f7"
hash_id = Hashids(salt, min_length=8)

# MySQL configurations
app.config['MYSQL_DATABASE_USER'] = 'root'
app.config['MYSQL_DATABASE_PASSWORD'] = 'admin001'
app.config['MYSQL_DATABASE_DB'] = 'drBarato'
app.config['MYSQL_DATABASE_HOST'] = 'localhost'
app.config['CORS_HEADERS'] = 'Content-Type'

mysql.init_app(app)
conn = mysql.connect()
cur = conn.cursor()

def registerUser(email,password,name,dtbirth,phone):
	cur.execute("insert into usuario values( 0,'"+email+"','CPF','"+password+"','"+name+"', '"+dtbirth+"', '"+phone+"');")
	data = cur.fetchall()
	if len(data) is 0:
		id = cur.lastrowid
		conn.commit()
		return json.dumps({'id_usuario':id})
	else:
		abort(404)

def userLogin(email,password):
	cur.execute("select senha from usuario where email = '"+email+"'")
	if not cur.rowcount:
		abort(404)
	r = cur.fetchall()
	for row in r:
		senha = row[0]
	if(password == senha):
		cur.execute("select id_usuario from usuario where email = '"+email+"'")	
		r = [dict((cur.description[i][0], value)
			for i, value in enumerate(row)) for row in cur.fetchall()]
		return jsonify(r)
	else:
		abort(404)

def getUser(idUsuario):
	cur.execute('SELECT * FROM usuario WHERE ID_usuario = "'+idUsuario+'"')
	if not cur.rowcount:
		abort(404)
	r = [dict((cur.description[i][0], value)
			  for i, value in enumerate(row)) for row in cur.fetchall()]
	cur.execute('SELECT * FROM medico WHERE ID_usuario = "'+idUsuario+'"')
	if not cur.rowcount:
		r[0]['tipo_usuario'] = 'paciente'
		return jsonify({'usuario' : r[0]})	
	else:
		r[0]['tipo_usuario'] = 'medico'
		return jsonify({'usuario' : r[0]})	


def getMedicos(cidade,especialidade,estado):
	cur.execute('select medico.id_usuario,usuario.nome, formacao,anos_experiencia,bio,estado,cidade,logradouro,bairro,complemento,CEP from medico ' + 'LEFT JOIN usuario ON usuario.ID_usuario = medico.ID_usuario' +
	            ' left join especialidade_medico ON medico.id_usuario '+'= especialidade_medico.id_medico LEFT JOIN lugar ON lugar.id_usuario = medico.id_usuario where '+'id_especialidade = '+especialidade+'  AND cidade = "'+cidade+'" AND estado = "'+estado+'";')
	if not cur.rowcount:
		abort(404)			
	r = [dict((cur.description[i][0], value)
			  for i, value in enumerate(row)) for row in cur.fetchall()]
	return jsonify({'medicos' : r})

def getMedico(idMedico):
	cur.execute('SELECT * FROM medico LEFT JOIN usuario ON usuario.ID_usuario = medico.ID_usuario LEFT JOIN lugar ON usuario.ID_usuario = lugar.ID_usuario WHERE medico.ID_usuario = "'+idMedico+'"')
	if not cur.rowcount:
		abort(404)
	r = [dict((cur.description[i][0], value)
		for i, value in enumerate(row)) for row in cur.fetchall()]
	return jsonify({'medico' : r})	

def getAgendamentoById(idAgendamento):
	cur.execute('SELECT * FROM AGENDAMENTO WHERE ID_AGENDAMENTO = "'+idAgendamento+'"')
	if not cur.rowcount:
		abort(404)
	r = [dict((cur.description[i][0], value)
			  for i, value in enumerate(row)) for row in cur.fetchall()]
	return jsonify({r})	

def getAgendamentoByVoucher(voucher):
	cur.execute('SELECT * FROM AGENDAMENTO WHERE VOUCHER = "'+voucher+'"')
	if not cur.rowcount:
		abort(404)
	r = [dict((cur.description[i][0], value)
			  for i, value in enumerate(row)) for row in cur.fetchall()]
	return jsonify({r})	

def createAgendamento(idMedico, inicio, fim):
	cur.execute("insert into agendamento values(0, "+str(idMedico)+", 0, '"+inicio+"','"+fim+"',0,NULL,NULL,32.2);")
	data = cur.fetchall()
	
	if len(data) is 0:
		id = cur.lastrowid
		conn.commit()
		return json.dumps({'id_agendamento':id})
	
	else:
		abort(404)

def scheduleAgendamento(idPaciente,idAgendamento):
	voucher = hash_id.encrypt(idAgendamento)
	cur.execute("update agendamento set id_paciente='"+str(idPaciente)+"', in_status=1, voucher='"+voucher+"' where id_agendamento="+str(idAgendamento))
	data = cur.fetchall()
	if len(data) is 0:
		conn.commit()
		return jsonify(voucher)
	else:
		abort(404)

def getHistoricoMedico(idMedico):
	cur.execute('select * from agendamento where id_medico = "'+idMedico+'"')
	if not cur.rowcount:
		abort(404)
	r = [dict((cur.description[i][0], value)
			  for i, value in enumerate(row)) for row in cur.fetchall()]
	return jsonify({'consultas' : r})

def getHistoricoUsuario(idUsuario):
	cur.execute('select * from agendamento where id_paciente = "'+idUsuario+'"')
	if not cur.rowcount:
		abort(404)
	r = [dict((cur.description[i][0], value)
			  for i, value in enumerate(row)) for row in cur.fetchall()]
	return jsonify({'consultas' : r})