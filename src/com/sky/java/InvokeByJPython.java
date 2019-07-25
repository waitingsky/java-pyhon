package com.sky.java;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import com.sky.java.bean.Apple;
import com.sky.java.bean.Fruit;
import com.sky.java.bean.GroovyController;
import com.sky.java.bean.Orange;

/**
 * -Dpython.console.encoding=UTF-8
 *
 */
public class InvokeByJPython {

	@Test
	public void testExePY() {
		PythonInterpreter interp = new PythonInterpreter();
		interp.exec("import sys");
		interp.set("a", new PyInteger(42));
		interp.exec("print a");
		interp.exec("x = 2+2");
		PyObject x = interp.get("x");
		System.out.println("x: " + x);
	}

	@Test
	public void testCallPY() throws IOException {
		String python = "helloworld.py";
		PythonInterpreter interp = new PythonInterpreter();
		interp.execfile(python);
		interp.cleanup();
		interp.close();
	}

	@Test
	public void testCallPYWithParam() throws IOException {
		// 1. Python面向函数式编程: 在Java中调用Python函数
		String pythonFunc = "calculator_func.py";

		PythonInterpreter pi1 = new PythonInterpreter();
		// 加载python程序
		pi1.execfile(pythonFunc);
		// 调用Python程序中的函数
		PyFunction pyf = pi1.get("power", PyFunction.class);
		PyObject dddRes = pyf.__call__(Py.newInteger(2), Py.newInteger(3));
		System.out.println(dddRes);
		pi1.cleanup();
		pi1.close();

		// 2. 面向对象式编程: 在Java中调用Python对象实例的方法
		String pythonClass = "calculator_clazz.py";
		// python对象名
		String pythonObjName = "cal";
		// python类名
		String pythonClazzName = "Calculator";
		PythonInterpreter pi2 = new PythonInterpreter();
		// 加载python程序
		pi2.execfile(pythonClass);
		// 实例化python对象
		pi2.exec(pythonObjName + "=" + pythonClazzName + "()");
		// 获取实例化的python对象
		PyObject pyObj = pi2.get(pythonObjName);
		// 调用python对象方法,传递参数并接收返回值
		PyObject result = pyObj.invoke("power", new PyObject[] { Py.newInteger(2), Py.newInteger(3) });
		double power = Py.py2double(result);
		System.out.println(power);

		pi2.cleanup();
		pi2.close();
	}

	@Test
	public void testCallPYWithInterface() throws IOException {
		// Python程序路径
		String python = "fruit_controller.py";
		// Python实例对象名
		String pyObjName = "pyController";
		// Python类名
		String pyClazzName = "FruitController";

		Fruit apple = new Apple();
		Fruit orange = new Orange();

		PythonInterpreter interpreter = new PythonInterpreter();
		// 如果在Python程序中引用了第三方库,需要将这些被引用的第三方库所在路径添加到系统环境变量中
		// 否则,在执行Python程序时将会报错: ImportError: No module named xxx
		PySystemState sys = interpreter.getSystemState();
		sys.path.add("D:\\Workspaces\\Python\\java-python");

		
		// 加载Python程序
		interpreter.execfile(python);
		// 实例 Python对象
		interpreter.exec(pyObjName + "=" + pyClazzName + "()");

		// 1.在Java中获取Python对象,并将Python对象转换为Java对象
		// 为什么能够转换? 因为Python类实现了Java接口,通过转换后的Java对象只能调用接口中定义的方法
		GroovyController controller = (GroovyController) interpreter.get(pyObjName).__tojava__(GroovyController.class);
		controller.controllFruit(apple);
		controller.controllFruit(orange);

		// 2.在Java直接通过Python对象调用其方法
		// 既可以调用实现的Java接口方法,也可以调用Python类自定义的方法
		PyObject pyObject = interpreter.get(pyObjName);
		pyObject.invoke("controllFruit", Py.java2py(apple));
		pyObject.invoke("controllFruit", Py.java2py(orange));
		pyObject.invoke("printFruit", Py.java2py(apple));
		pyObject.invoke("printFruit", Py.java2py(orange));

		// 3.在Java中获取Python类进行实例化对象: 没有事先创建 Python对象
		PyObject pyClass = interpreter.get("FruitController");
		PyObject pyObj = pyClass.__call__();
		pyObj.invoke("controllFruit", Py.java2py(apple));
		pyObj.invoke("controllFruit", Py.java2py(orange));

		PyObject power = pyObj.invoke("power", new PyObject[] { Py.newInteger(2), Py.newInteger(3) });
		if (power != null) {
			double p = Py.py2double(power);
			System.out.println("----------------"+p);
		}

		interpreter.cleanup();
		interpreter.close();

	}
}
