# -*- coding: utf-8 -*-

import thriftpy
import time
import matplotlib.pyplot as plt
import numpy

from thriftpy.rpc import client_context

calc_thrift = thriftpy.load("calc.thrift", module_name="calc_thrift")


def main():
    with client_context(calc_thrift.Calculator, '127.0.0.1', 6000) as cal:

        time.time()
        time_sq = []
        time_eight = []
        time_upper = []
        time_void = []

        for i in range(5):

            inicio = time.clock()
            response = cal.SquareRoot(98881111)
            print(response)
            final = time.clock() - inicio
            time_sq.append(final)   
    
            inicio = time.clock()
            response = cal.UpperCase('Testando string para materia de Sistemas Distribuidos')
            final = time.clock() - inicio
            time_upper.append(final)
            
            inicio = time.clock()
            response = cal.SquareEight(2, 4, 8, 16, 32, 64, 128, 256)
            final = time.clock() - inicio
            time_eight.append(final)

            inicio = time.clock()
            print(inicio)
            cal.ExecVoid()
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


if __name__ == '__main__':
    main()
