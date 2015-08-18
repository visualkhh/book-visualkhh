package helloservice;

public class HelloImpl implements HelloIF {
	public String message = "Hello ";

	public String sayHello(String s) {
		return message + s;
	}
}