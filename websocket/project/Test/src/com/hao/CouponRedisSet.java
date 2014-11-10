package com.hao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import redis.clients.jedis.Jedis;

import com.efan.dao.DBEngine;

public class CouponRedisSet  extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html;charset=GB2312"); // �������ָ������ͻ��˷��͵����ݸ�ʽ�Ͳ��õ��ַ����룮
		Jedis jedis = new Jedis("haoning.net",6379); //���ӳ���
		System.out.println("---------/coupon/check");
		try {   
			String machine_product_id = request.getParameter("machine_product_id"); 
			String user_id = request.getParameter("user_id"); 
			String channel_id = request.getParameter("channel_id"); 
			
			String result =null;
			if(machine_product_id==null){
				 result="0";
			}else{
				 //�޸����ݿ� -1 ����
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
	private int setCoupon(String user_id,String channel_id){
		int result=-1;
		try{
			DBEngine dbe = new DBEngine("efan",false);
			String sql="select ott_app.*,ott_channel_app_rel.app_weight " +
					"from ott_app ,ott_channel_app_rel " +
					"where " +
					" ott_app.pub_status = 'P' " +
					"  and ott_app.id = ott_channel_app_rel.app_id" +
					" and ott_channel_app_rel.channel_id = '"+user_id+"'";
			result = dbe.executeUpdate(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
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
