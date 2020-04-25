# -*- coding: utf-8 -*-

import thriftpy
import math


from thriftpy.rpc import make_server

calc_thrift = thriftpy.load("calc.thrift", module_name="calc_thrift")


class Dispatcher(object):
    def SquareRoot(self, a):
        print("Executando Square")
        return math.sqrt(a)
    
    def UpperCase(self, s1):
        print("Executando UpperCase")
        return s1.upper()
    
    def SquareEight(self, x1, x2, x3, x4, x5, x6, x7, x8):
        print("Executando SquareEight")
        return math.sqrt(x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8)
    
    def ExecVoid(self):
        
        print("Executando Void")


def main():
    server = make_server(calc_thrift.Calculator, Dispatcher(),
                         '127.0.0.1', 6000)
    print("serving...")
    server.serve()


if __name__ == '__main__':
    main()
