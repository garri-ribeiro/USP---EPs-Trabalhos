# -*- coding: utf-8 -*-

import grpc
from concurrent import futures
import time

# import the generated classes
import calculator_pb2
import calculator_pb2_grpc

# import the original calculator.py
import calculator

# create a class to define the server functions
# derived from calculator_pb2_grpc.CalculatorServicer
class CalculatorServicer(calculator_pb2_grpc.CalculatorServicer):

    # calculator.square_root is exposed here
    # the request and response are of the data types
    # generated as calculator_pb2.Number
    def SquareRoot(self, request, context):
        print("Executando Square")
        response = calculator_pb2.Number()
        response.value = calculator.square_root(request.value)
        return response
    
    def UpperCase(self, request, context):
        print("Executando UpperCase")
        response = calculator_pb2.String()
        response.word = calculator.uppercase(request.word)
        return response
    
    def SquareEight(self, request, context):
        print("Executando SquareEight")
        response = calculator_pb2.Number()
        response.value = calculator.sq_eight(request.x1,request.x2,request.x3,request.x4,request.x5,request.x6,request.x7,request.x8)
        return response
    
    def ExecVoid(self, request, context):
        print("Executando Void")
        response = calculator_pb2.Empty()
        calculator.execvoid()
        return response
   
# create a gRPC server
server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))

# use the generated function `add_CalculatorServicer_to_server`
# to add the defined class to the created server
calculator_pb2_grpc.add_CalculatorServicer_to_server(
        CalculatorServicer(), server)

# listen on port 50051
print('Starting server. Listening on port 50051.')
server.add_insecure_port('[::]:50051')
server.start()

# since server.start() will not block,
# a sleep-loop is added to keep alive
try:
    while True:
        time.sleep(86400)
except KeyboardInterrupt:
    server.stop(0)
