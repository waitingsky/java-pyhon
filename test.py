# -*- coding:utf-8 -*-

#通过java package导入java类
from com.sky.java2 import SayHello

execpy = SayHello()

#将python属性传入后续调用的java实例
execpy.setUserName(userName)

def say():
    execpy.say(5)
    return

say()