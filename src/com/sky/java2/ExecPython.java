package com.sky.java2;

import java.util.Map;
import java.util.Map.Entry;

import org.python.util.PythonInterpreter;

/*enum������÷�,������Ϊ���ֵİ�ȫ����,ֵ�ý��Ŷ ^_^ */
public enum ExecPython {
	INSTANCE;

	public void execute(String scriptFile, Map<String, String> properties) {
		// ��ȡpython������
		final PythonInterpreter inter = JythonEnvironment.getInstance().getPythonInterpreter();

		// ����python����,python�ű��п���ʹ��
		for (Entry<String, String> entry : properties.entrySet()) {
			inter.set(entry.getKey(), entry.getValue());
		}

		try {
			// ͨ��python����������python�ű�
			inter.execfile(scriptFile);
		} catch (Exception e) {
			System.out.println("ExecPython encounter exception:" + e);
		}
	}
}
