package com.sky.java2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExecPythonTest {

	@Test
	public void test() {
		String scriptFile = "test.py";
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("userName", "Demo");
		ExecPython.INSTANCE.execute(scriptFile, properties);
	}

}
