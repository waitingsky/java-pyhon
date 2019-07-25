package com.sky.java2;

import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

/**
 * Jython����,����python������
 * 
 * @author webim
 * 
 */
public final class JythonEnvironment {
	private static JythonEnvironment INSTANCE = new JythonEnvironment();

	/**
	 * ˽�й��췽��
	 */
	private JythonEnvironment() {
	}

	/**
	 * ��ȡ����
	 * 
	 * @return JythonEnvironment
	 */
	public static JythonEnvironment getInstance() {
		return INSTANCE;
	}

	/**
	 * ��ȡpythonϵͳ״̬,�ɸ�����Ҫָ��classloader/sys.stdin/sys.stdout��
	 * 
	 * @return PySystemState
	 */
	private PySystemState getPySystemState() {
		PySystemState.initialize();
		final PySystemState py = new PySystemState();
		py.setClassLoader(getClass().getClassLoader());
		return py;
	}

	/**
	 * ��ȡpython������
	 * 
	 * @return PythonInterpreter
	 */
	public PythonInterpreter getPythonInterpreter() {
		PythonInterpreter inter = new PythonInterpreter(null, getPySystemState());
		return inter;
	}
}
