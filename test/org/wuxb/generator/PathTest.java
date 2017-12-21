package org.wuxb.generator;

public class PathTest {

	public static void main(String[] args) {
		String url = "http://localhost:8080/jqueryWeb/resources/request.jsp";
		String path = "/jqueryWeb/resources/request.jsp";
		System.out.println(url.substring(0,url.indexOf(path)));
	}
}
