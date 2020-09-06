package controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.UserModel;

public class LoginController {

	public Boolean login(UserModel user) {
		String userName = "admin";
		String password = "1234";
		ArrayList<String> loginDetail=new ArrayList<String>();
		loginDetail.add(userName);
		loginDetail.add(password);
		if (loginDetail.contains(user.getUserName())==true) {
			if (loginDetail.contains(user.getPassword())==true) {
				return true;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Kullanýcý Adý veya Þifre Geçersiz");
		return false;
	}
}
