package mail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMail
{
    public static void main(String[] args) throws Exception
    {
        String host="smtp.gmail.com";
        int port=587;
        String user="quickmailerffhs@gmail.com";
        String pass="x14l27x14l27";
        
        Properties props=new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session=Session.getInstance(props);
        Transport transport=session.getTransport("smtp");
        transport.connect(host, port, user, pass);
        
        Address[] addresses=InternetAddress.parse("p45q@p45q.net");
        
        Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject("TEST SUBJECT");
        
        message.setText("text/plain BODY");
        
        transport.sendMessage(message, addresses);
        System.out.println("SEND");
        
        transport.close();
    }
}