# -*- coding: utf-8 -*-

from flask import Flask, jsonify, request, json, abort
from flask_cors import CORS, cross_origin
import DAO

app = Flask(__name__)
CORS(app)

# Cadastro de usuário
@app.route('/register',methods=['POST'])
def register():
	if(request.method == 'POST'):
		jss = request.get_json()
		return DAO.registerUser(jss["email"],jss["password"],jss["name"],jss["dtbirth"],jss["phone"])		

# Login do usuário
@app.route('/login',methods=['POST'])
def login():
	if(request.method == 'POST'):
		jss = request.get_json()
		return DAO.userLogin(jss["email"],jss["password"])	

# Usuário
@app.route('/user', methods=['GET'])
def user():
	if (request.method == 'GET'):
		id_usuario = request.args.get('id_usuario')
		return DAO.getUser(id_usuario)

# Lista de medicos
@app.route('/doctors', methods=['GET'])
def medicos():
	if (request.method == 'GET'):
		speciality = request.args.get('speciality')
		state = request.args.get('state')
		city = request.args.get('city')
		return DAO.getMedicos(city, speciality, state)

# Medico
@app.route('/doctor', methods=['GET'])
def medico():
	if (request.method == 'GET'):
		id_usuario = request.args.get('id_usuario')
		return DAO.getMedico(id_usuario)

@app.route('/create-schedule', methods=['POST','GET'])
def agendamento():
	if (request.method == 'POST'): 										
		jss = request.get_json()
		return DAO.createAgendamento(jss["id_medico"],jss["data_inicio"],jss["data_fim"])
	else:
		return DAO.getAgendamentoById(jss["id_agendamento"])

@app.route('/schedule', methods=['POST','GET'])
def agendar():
	if (request.method == 'POST'): 										
		jss = request.get_json()
		return DAO.scheduleAgendamento(jss["id_paciente"],jss["id_agendamento"])
	else:
		return DAO.getAgendamentoByVoucher(jss["voucher"])

@app.route('/doctor/history', methods=['GET'])
def medicoHistory():
	if (request.method == 'GET'):
		id_medico = request.args.get('id_medico')
		return DAO.getHistoricoMedico(id_medico)

@app.route('/user/history', methods=['GET'])
def userHistory():
	if (request.method == 'GET'):
		id_usuario = request.args.get('id_usuario')
		return DAO.getHistoricoUsuario(id_usuario)

if __name__ == '__main__':
	app.debug = True
	app.run(host='0.0.0.0')