# -*- coding: utf-8 -*-
import grpc

# import the generated classes
import calculator_pb2
import calculator_pb2_grpc
import time
import matplotlib.pyplot as plt
import numpy

time.clock() 

# open a gRPC channel
channel = grpc.insecure_channel('localhost:50051')

# create a stub (client)
stub = calculator_pb2_grpc.CalculatorStub(channel)

time_sq = []
time_eight = []
time_upper = []
time_void = []

for i in range(5):
    number = calculator_pb2.Number(value=98881111)
    inicio = time.clock()
    response = stub.SquareRoot(number)
    final = time.clock() - inicio
    time_sq.append(final)

    string = calculator_pb2.String(word="Testando string para materia de Sistemas Distribuidos")
    inicio = time.clock()
    response = stub.UpperCase(string)
    final = time.clock() - inicio
    time_upper.append(final)
    
    numbers = calculator_pb2.Numbers(x1=2,x2=4,x3=8,x4=16,x5=32,x6=64,x7=128,x8=256)
    inicio = time.clock()
    response = stub.SquareEight(numbers)
    final = time.clock() - inicio
    time_eight.append(final)

    empty = calculator_pb2.Empty()
    inicio = time.clock()
    stub.ExecVoid(empty)
    final = time.clock() - inicio
    time_void.append(final)
    
def show(time):
    print(time)
    plt.plot(time)
    plt.ylabel('times')
    plt.show()
    print("Media:")
    print(numpy.mean(time))
    print("Desvio Padrão:")
    print(numpy.std(time))
    print("")

print("Square Root:")
plt.xlabel('square')
show(time_sq)

print("Upper:")
plt.xlabel('upper')
show(time_upper)

print("Square Eight:")
plt.xlabel('square eight')
show(time_eight)


print("Void:")
plt.xlabel('void')
show(time_void)