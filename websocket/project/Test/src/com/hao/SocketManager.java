package com.hao;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

public class SocketManager extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=GB2312"); // �������ָ������ͻ��˷��͵����ݸ�ʽ�Ͳ��õ��ַ����룮
		/*PrintWriter out = response.getWriter();
		out.println("HelloWorldWebSocketServlet:"+HelloWorldWebSocketServlet.mmiList.size()+" "); // ����PrintWriter����ķ��������ݷ��͸��ͻ���
		for (Map.Entry entry : HelloWorldWebSocketServlet.mmiList.entrySet()) {
			String key= (String) entry.getKey();
			out.println(key);
		}
		out.println("LineWebSocketServlet:"+LineWebSocketServlet.mmiList.size()+" "); // ����PrintWriter����ķ��������ݷ��͸��ͻ���
		for (Map.Entry entry : LineWebSocketServlet.mmiList.entrySet()) {
			String key= (String) entry.getKey();
			out.println(key);
		}
		out.close();*/
	}

	// ���ڴ���ͻ��˷��͵�POST����
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // �������������ǣ����ͻ��˷���POST����ʱ������doGet()�������д���
	}
}
