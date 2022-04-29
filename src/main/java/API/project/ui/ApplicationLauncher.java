package API.project.ui;

import java.util.Locale;

import API.project.businessLogic.Manager;
import API.project.businessLogic.ManagerImplementation;



public class ApplicationLauncher {

	  public static void main(String[] args) {

	    System.out.println("Locale: " + Locale.getDefault());

	    Manager businessLogic =new ManagerImplementation();
	    
	    
	    new MainGUI(businessLogic);

	  }


	}
