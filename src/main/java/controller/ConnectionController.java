package controller;

import org.hibernate.Session;


import util.HibernateUtil;

public class ConnectionController {

	public String establishConnection(){
		try(Session session = HibernateUtil.getSession().openSession()) {
			
			return "Connected";
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
