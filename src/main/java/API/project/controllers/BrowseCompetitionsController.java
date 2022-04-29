package API.project.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.transcoder.*;
//import org.apache.batik.transcoder.TranscoderInput;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import API.project.businessLogic.Manager;
import API.project.ui.MainGUI;
import API.project.uicontrollers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.control.Button;

import API.project.domain.*;
import API.project.helper.BufferedImageTranscoder;
import javafx.embed.swing.SwingFXUtils;

public class BrowseCompetitionsController implements Controller{
	
	private Manager bl;

	public BrowseCompetitionsController(Manager bl) {
	    this.bl = bl;
	}
	private MainGUI mainGUI;  
	
	@FXML
    private Button nextBtn;

    @FXML
	private Button previousBtn;
	    
	@FXML
    private TextField txtAreaCC;

    @FXML
    private TextField txtAreaId;

    @FXML
    private TextField txtAreaName;

    @FXML
    private TextField txtCompetionId;

    @FXML
    private TextField txtCompetionName;
    
    @FXML
    private ImageView imgViewCompetition;

    
    private String body;
    private  List<Competition> competitions;
    private int i;
    
    @FXML
    void initialize()
    {
    	
    	txtAreaCC.setDisable(true);
    	txtAreaId.setDisable(true);
    	txtAreaName.setDisable(true);
    	txtCompetionId.setDisable(true);
    	txtCompetionName.setDisable(true);
    	
    	 body=bl.request("competitions");
    	 Gson gson=new Gson();
    	 JsonObject jsonObject;
    	 
    	 jsonObject=gson.fromJson(body, JsonObject.class);
    	 
    	 Type competitionListType=new TypeToken<ArrayList<Competition>>(){}.getType();
    	 
    	 competitions =gson.fromJson((jsonObject.get("competitions")), competitionListType);
    	 
    	 try {
			print_competition(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 previousBtn.setDisable(true);
		 
		 i=1;
    	 
    }

    @FXML
    void ClickNext(ActionEvent event) throws IOException {
    	
    	if(i>0)
    		previousBtn.setDisable(false);
    	
    	if(i<competitions.size())
    	{
    		nextBtn.setDisable(false);
    		print_competition( i);
    		
    		
			 
    	}
    	
    	 if(i == competitions.size()-1)
    	 {
    		 nextBtn.setDisable(true);
    	 }		 
    	else
    		i++;
    	 
    	 if(i == 1)
    	 	{
    		 	previousBtn.setDisable(true);
    	 	}	
    	
		 
    }

    @FXML
    void ClickPrevious(ActionEvent event) {
    	
    	i--;
    	
    	if(i < competitions.size())
   	 	{
   		 	nextBtn.setDisable(false);
   	 	}	
    	 
    	if(i>=0)
    	{
    		previousBtn.setDisable(false);
    		try {
				print_competition( i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
    	}
    	if(i == 0)
   	 	{
   		 	previousBtn.setDisable(true);
   	 	}		 
	   	
    }
    
    //function to fill the textarea
    void print_competition(int i) throws IOException
    {
    	txtCompetionId.setText(competitions.get(i).id+"");
		 txtCompetionName.setText(competitions.get(i).name);
		 txtAreaId.setText(competitions.get(i).area.getId()+"");
		 txtAreaName.setText(competitions.get(i).area.getName());
		 txtAreaCC.setText(competitions.get(i).area.getCountryCode());
		 
 
		load_svg(i);
		
		
    }
    
    //function to convert svg to BufferedImage
    void load_svg(int i) throws IOException
    {
    	String extension;
    	
    	System.out.println("URL :"+competitions.get(i).emblemUrl);
    	// https://crests.football-data.org/UCL.png error in this image
    	if(competitions.get(i).emblemUrl!=null)
 		{
    		extension=competitions.get(i).emblemUrl.substring(competitions.get(i).emblemUrl.length() - 3);
    		//System.out.println("last 3 character:" +extension);
    		
    		if(extension.equals("svg")) 
    		{
		 		BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
		
		 		URL url = new URL(competitions.get(i).emblemUrl);
		 		URLConnection conn = url.openConnection();
		
		 		try (InputStream file = conn.getInputStream()) {
		 			//System.out.println("File class :"+file.getClass().getTypeName());
		 			//System.out.println("File Type of " + file.getName() + " is " +new MimetypesFileTypeMap().getContentType(file));
		 		    TranscoderInput transIn = new TranscoderInput(file);
		 		    try {
						transcoder.transcode(transIn, null);
		 		        WritableImage img = SwingFXUtils.toFXImage(transcoder.getBufferedImage(), null);
		 		        imgViewCompetition.setImage(img);
		 		        
		 		    } catch (TranscoderException ex) {
		 		        ex.printStackTrace();
		 		    }
		 		}
		 		catch (IOException io) {
		 			
		 		    io.printStackTrace();
		 		}
		 		
    		}
    		else
    		{
    			Image img2=new Image(competitions.get(i).emblemUrl);
 		    	imgViewCompetition.setImage(img2);
    		}
	   }
		else
		{
			imgViewCompetition.setImage(null);
			//imgViewCompetition.
		}
    }
    
    
    @Override
    public void setMainApp(MainGUI mainGUI) {
      this.mainGUI = mainGUI;
    }

}
