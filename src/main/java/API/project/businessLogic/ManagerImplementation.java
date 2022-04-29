package API.project.businessLogic;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ManagerImplementation implements Manager {
	
	@Override
	public String request(String endpoint)
	{
		String result="";
		OkHttpClient client=new OkHttpClient();
		
		Request request=new Request.Builder()
				.url("https://api.football-data.org/v2/"+endpoint)
				.get()
				.addHeader("X-Auth-Token", "25659b933fab4b2b94e2fe2d65f8cc97")
				.build();
		
		try {
			Response response=client.newCall(request).execute();
			if(response.code()==200)
			{
				result=response.body().string();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
