# coding=utf-8

# from calculator_clazz import Calculator
from calculator_clazz import Calculator
from java.lang import String
# from org.test.inter import GroovyController
# from org.test.inter import Fruit

from com.sky.java.bean import GroovyController
from com.sky.java.bean import Fruit


# 在Python中实现Java接口: org.test.inter.GroovyController
class FruitController(GroovyController):
    
    # 实现接口方法
    def controllFruit(self, fruit):
        # 在Python中调用Java对象方法
        fruit.show()
        
        if(fruit.getType() == "apple"):
            print ("controllFruit Python Apple")
            
        if(fruit.getType() == "orange"):
            print ("controllFruit Python Orange")
        
        print ("controllFruit END")
    
    # 自定义新方法    
    def printFruit(self, fruit):
        fruit.show()
        
        if(fruit.getType() == "apple"):
            print ("printFruit Python Apple")
            
        if(fruit.getType() == "orange"):
            print ("printFruit Python Orange")
        
        print ("printFruit END")
    
    # 引用第三方python程序
    def power(self, x, y):
        cal = Calculator()
        return cal.power(x, y)
