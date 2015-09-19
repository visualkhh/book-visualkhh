package net.daum.javacafe.util;

import java.lang.reflect.Constructor;

import net.daum.javacafe.queue.Queue;

public class DynamicClassLoader {

	public static Object createInstance(String type, Queue queue) throws Exception {
		Object obj = null;
		if (type != null) {
			Class[] paramType = new Class[] { Queue.class };
			Constructor con = Class.forName(type).getConstructor(paramType);
			Object[] params = new Object[] { queue };
			obj = con.newInstance(params);
		}
		return obj;
	}

}