package com.hao;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

public class HelloWorldWebSocketServlet extends WebSocketServlet {

	protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest arg1) {

		return new MessageInbound() {

			@Override
			protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
				// TODO Auto-generated method stub

			}

			@Override
			protected void onTextMessage(CharBuffer message) throws IOException {
				// TODO Auto-generated method stub
				System.out.println("onText--->" + message.toString());
				Socket socket;
				String msg = "";
				try {
					// �����������Socket������Ϣ
					socket = new Socket("192.168.0.102", 5000);
					//socket = new Socket("127.0.0.1",5000);
					PrintWriter output = new PrintWriter(
							socket.getOutputStream());

					output.write(message.toString());
					output.flush();

					// �����ǽ��յ�Server����Ϣ
					DataInputStream input = new DataInputStream(
							socket.getInputStream());
					byte[] b = new byte[1024];
					input.read(b);
					// Server���ص���Ϣ
					msg = new String(b).trim();

					output.close();
					input.close();
					socket.close();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// �������������Ϣ
				CharBuffer cb = CharBuffer.wrap(new StringBuilder(msg));
				getWsOutbound().writeTextMessage(cb);
			}
		};
	}

	public static void main(String[] args) {
		Socket socket;
		String message ="haoning";
		String msg = "";
		try {
			// �����������Socket������Ϣ
			socket = new Socket("192.168.0.102", 5000);
			// socket = new Socket("127.0.0.1",5000);
			PrintWriter output = new PrintWriter(
					socket.getOutputStream());

			output.write(message.toString());
			output.flush();

			// �����ǽ��յ�Server����Ϣ
			DataInputStream input = new DataInputStream(
					socket.getInputStream());
			byte[] b = new byte[1024];
			input.read(b);
			// Server���ص���Ϣ
			msg = new String(b).trim();

			output.close();
			input.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
