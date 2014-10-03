import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	public static final int PORT = 5000;
	public static void main(String[] args) {
		System.out.println("������...\n");
		Server ser = new Server();
		ser.sock();
	}
	public void sock() {
		try {
			ServerSocket server = new ServerSocket(PORT);
			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = server.accept();
				System.out.println("server accept");
				// �����������
				new PServer(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	private class PServer implements Runnable {
		private Socket socket;
		public PServer(Socket sock) {
			socket = sock;
			new Thread(this).start();
		}
		public void run() {
			System.out.println("һ���ͻ�������ip:" + socket.getInetAddress());
			try {
				// ��ȡ�ͻ�������
				DataInputStream input = new DataInputStream(
						socket.getInputStream());
				// ��ͻ��˷�������
				DataOutputStream out = new DataOutputStream(
						socket.getOutputStream());
				// ��ȡ�ͻ�������
				//System.out.println("�ͻ��˷�����������: " + input.readUTF());
				byte[] bt = new byte[1024];
				int leng = input.read(bt);
				System.out.println(new String(bt, 0, leng));
				// ���ͼ��������һ��
				// String s = new BufferedReader(new
				// InputStreamReader(System.in)).readLine();
				String s = "server d shu ru";
				out.write(s.getBytes());
				out.flush();
				input.close();
				out.close();
				socket.close();
			} catch (Exception e) {
				System.out.println("������ run �쳣: " + e.getMessage());
			}
		}

	}

}