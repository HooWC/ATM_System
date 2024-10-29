package AppProgram;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import AppDbContext.DBConnection;
import Components.Menu;
import Text_Color.BackgroundColor;
import Text_Color.Color;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Index {

	static BackgroundColor backgrondcolor = new BackgroundColor();
	static Color color = new Color();
	
	public static void main(String[] args)  {
		
		
//		DBConnection.createConnection();
		
		Menu menu = new Menu();
		menu.Home();

//       Date d = new Date();
//       SimpleDateFormat sdf = new SimpleDateFormat();
//       sdf.applyPattern("yyyy-MM-dd"); 
//       System.out.println(sdf.format(d));

		
		
	}
	
	

}
