package Function;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Models.*;

public class Func {

	public static void Clear() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}
	
	public static void email(double balance,BankAccountID bank,Account acc,String opt) {
		
		final String username = "wengchin1234567@gmail.com";
		final String password = "drwzhcsmtskejbeq";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable","true");
		
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		
		try {
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress("wengchin1234567@gmail.com"));//from
			
			message.setRecipients(
					Message.RecipientType.TO, 
					InternetAddress.parse("wengchin123456@gmail.com")
			);//to email
			
			String type = "";
			if(opt.equals("d")) {
				message.setSubject("Deposit Cash");
				type = "deposited";
			}	
			else if(opt.equals("w")) {
				message.setSubject("Withdraw Cash");
				type = "withdraw";
			}
				
			
			String bal = String.format("%.2f",balance);
			String total = String.format("%.2f",bank.getBalance());
			
			String sb = "<head>" +
                    "<style type=\"text/css\">" 
                    + ".view {margin-left: 150px;display: flex;padding-top: 4em;position: relative; }"
                    + ".full-email-view {width: 100%; }" 
                    + ".email-view--inner {padding: 3em 1em;max-width: 1000px;width: 90%;border: 1px solid #ECEAEA;margin: auto;font-size: 100%; }"
                    + ".from {display: flex;align-items: center; }"
                    + ".fromImg {max-width: 100px;width: 100%;height: 120px;background: #eee;background-size: cover;background-position: center;object-fit: cover;margin-right: 1em;}"
                    + ".emailSection {padding: 0.26em 0em;line-height: 1.5; }"
                    + ".is-default {color: #777; }"
                    + ".the-email {line-height: 2;color: #777;padding-top: 2em;font-size: 100%; }"
                    +
                    "</style>" +
                    "</head>" +
                    "<section class=\"view\">" +
                    "<section class=\"full-email-view is-component\">" +
                    "<div class=\"email-view--inner\">" +
                    "<div class=\"from\">" +
                    "<div class=\"fromSub isFrom-component\">" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">From: </span>" +
                    "<strong class=\"fromName\">Brad Smith </strong><span class=\"emailBubble\">wengchin1234567@gmail.com</span>" +
                    "</div>" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">To: </span>" +
                    "<strong class=\"clientName\">You</strong> <i>wengchin123456@gmail.com</i>" +
                    "</div>" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">Subject:</span> " +
                    message.getSubject() +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<p>----------------------------------------------------------</p>" +
                    "<div class=\"the-email\">" +
                    "<p>" +
                    "You just "+ type +
                    "<strong>" +" RM" + bal + "</strong>" +
                    " in " +
                    acc.getBankType()+ "(" + bank.getBankAccNo() + ")" + 
                    "." +
                    "</p>" +
                    "<p>" +
                    "Now the " +
                    acc.getBankType()+ "(" + bank.getBankAccNo() + ")" + 
                    " has a total deposit of " +
                    "<strong>" + "RM" + total + "</strong>" +
                    "</p>" +
                    "</div>" +
                    "</section>";
			
            message.setContent(sb, "text/html; charset=utf-8");
            message.saveChanges();
			
			Transport.send(message);
		
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void email_OPT(String str) {
		
		final String username = "wengchin1234567@gmail.com";
		final String password = "drwzhcsmtskejbeq";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable","true");
		
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		
		try {
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress("wengchin1234567@gmail.com"));//from
			
			message.setRecipients(
					Message.RecipientType.TO, 
					InternetAddress.parse("wengchin123456@gmail.com")
			);//to email
			
			message.setSubject("EDUVO BANK OPT");

			
			String sb = "<head>" +
                    "<style type=\"text/css\">" 
                    + ".view {margin-left: 150px;display: flex;padding-top: 4em;position: relative; }"
                    + ".full-email-view {width: 100%; }" 
                    + ".email-view--inner {padding: 3em 1em;max-width: 1000px;width: 90%;border: 1px solid #ECEAEA;margin: auto;font-size: 100%; }"
                    + ".from {display: flex;align-items: center; }"
                    + ".fromImg {max-width: 100px;width: 100%;height: 120px;background: #eee;background-size: cover;background-position: center;object-fit: cover;margin-right: 1em;}"
                    + ".emailSection {padding: 0.26em 0em;line-height: 1.5; }"
                    + ".is-default {color: #777; }"
                    + ".the-email {line-height: 2;color: #777;padding-top: 2em;font-size: 100%; }"
                    +
                    "</style>" +
                    "</head>" +
                    "<section class=\"view\">" +
                    "<section class=\"full-email-view is-component\">" +
                    "<div class=\"email-view--inner\">" +
                    "<div class=\"from\">" +
                    "<div class=\"fromSub isFrom-component\">" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">From: </span>" +
                    "<strong class=\"fromName\">Brad Smith </strong><span class=\"emailBubble\">wengchin1234567@gmail.com</span>" +
                    "</div>" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">To: </span>" +
                    "<strong class=\"clientName\">You</strong> <i>wengchin123456@gmail.com</i>" +
                    "</div>" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">Subject:</span> " +
                    message.getSubject() +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<p>----------------------------------------------------------</p>" +
                    "<div class=\"the-email\">" +
                    "<p>" +
                    "You OTP is " + "<strong>" + str + "</strong>" +
                    ". This OTP will only be valid for 2 minutes upon request. Please do not share your OTP with anyone." +
                    "</p>" +
                    "</div>" +
                    "</section>";
			
            message.setContent(sb, "text/html; charset=utf-8");
            message.saveChanges();
			
			Transport.send(message);
		
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void email_tr(long myacc,long other,String bangtype,double d) {
		
		final String username = "wengchin1234567@gmail.com";
		final String password = "drwzhcsmtskejbeq";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable","true");
		
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		
		try {
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress("wengchin1234567@gmail.com"));//from
			
			message.setRecipients(
					Message.RecipientType.TO, 
					InternetAddress.parse("wengchin123456@gmail.com")
			);//to email
			
			message.setSubject("Transaction");

			
			String sb = "<head>" +
                    "<style type=\"text/css\">" 
                    + ".view {margin-left: 150px;display: flex;padding-top: 4em;position: relative; }"
                    + ".full-email-view {width: 100%; }" 
                    + ".email-view--inner {padding: 3em 1em;max-width: 1000px;width: 90%;border: 1px solid #ECEAEA;margin: auto;font-size: 100%; }"
                    + ".from {display: flex;align-items: center; }"
                    + ".fromImg {max-width: 100px;width: 100%;height: 120px;background: #eee;background-size: cover;background-position: center;object-fit: cover;margin-right: 1em;}"
                    + ".emailSection {padding: 0.26em 0em;line-height: 1.5; }"
                    + ".is-default {color: #777; }"
                    + ".the-email {line-height: 2;color: #777;padding-top: 2em;font-size: 100%; }"
                    +
                    "</style>" +
                    "</head>" +
                    "<section class=\"view\">" +
                    "<section class=\"full-email-view is-component\">" +
                    "<div class=\"email-view--inner\">" +
                    "<div class=\"from\">" +
                    "<div class=\"fromSub isFrom-component\">" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">From: </span>" +
                    "<strong class=\"fromName\">Brad Smith </strong><span class=\"emailBubble\">wengchin1234567@gmail.com</span>" +
                    "</div>" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">To: </span>" +
                    "<strong class=\"clientName\">You</strong> <i>wengchin123456@gmail.com</i>" +
                    "</div>" +
                    "<div class=\"emailSection\">" +
                    "<span class=\"is-default\">Subject:</span> " +
                    message.getSubject() +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<p>----------------------------------------------------------</p>" +
                    "<div class=\"the-email\">" +
                    "<p>" +
                    "You used "+myacc+" to transfer money to "+other+" ("+bangtype+")" +
                    "</p>" +
                    "<p>" + 
                    "<strong>"+"RM "+String.format("%.2f",d) +"</strong>"+
                    "</p>" +
                    "</div>" +
                    "</section>";
			
            message.setContent(sb, "text/html; charset=utf-8");
            message.saveChanges();
			
			Transport.send(message);
		
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
