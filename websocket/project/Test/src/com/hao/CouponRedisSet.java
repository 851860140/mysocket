package com.hao;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

public class CouponRedisSet  extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html;charset=GB2312"); // �������ָ������ͻ��˷��͵����ݸ�ʽ�Ͳ��õ��ַ����룮
		Jedis jedis = new Jedis("haoning.net",6379); //���ӳ���
		System.out.println("---------/coupon/check");
		try {   
			String machine_product_id = request.getParameter("machine_product_id"); 
			String result =null;
			if(machine_product_id==null){
				 result="0";
			}else{
				 result=jedis.set(machine_product_id,"1"); 
				 System.out.println(result);
			}
		    try {
				response.getWriter().write(result);
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		} catch (Exception e) {  
		    e.printStackTrace();  
		} finally {  
			
		}  
	}

	// ���ڴ���ͻ��˷��͵�POST����
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // �������������ǣ����ͻ��˷���POST����ʱ������doGet()�������д���
	}
	public static void main(String[] args) {
		Jedis jedis = new Jedis("haoning.net",6379); 
		try {   
		    String cc=jedis.set("abc", "1");  
		    System.out.println(cc);
		    String ss = jedis.get("abc");  
		    System.out.println(ss);
		} catch (Exception e) {  
		    e.printStackTrace();  
		} finally {  
			
		}  
	}

}
