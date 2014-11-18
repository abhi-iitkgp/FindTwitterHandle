
import java.io.FileReader;
import java.io.FileWriter;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import au.com.bytecode.opencsv.CSVReader;

public class Main 
{
	public static void main(String [] args)
	{
		ConfigurationBuilder conf_builder = new ConfigurationBuilder();
		conf_builder.setDebugEnabled(true).setOAuthConsumerKey("whn49lZKie9VnFmjvcFOUdNUy")
										  .setOAuthConsumerSecret("UpTxFKlRxy4932K7Lqr1rCeE4PWIvpGiUvNhgjV4GklijuxDQy")
										  .setOAuthAccessToken("142562715-gxVABSi2j6vXGfXdcDQHATguTLD5xR1yACu36VTd")
										  .setOAuthAccessTokenSecret("V6cw66XvfuF0jTTxcf7dPyWP2X3Ne7aGWcl6LwZNeF8IC");
		
		TwitterFactory twitter_factory = new TwitterFactory(conf_builder.build());		
		Twitter twitter = twitter_factory.getInstance();				
		FileWriter output_file_writer = null;
		
		try
		{
			output_file_writer = new FileWriter("D:\\RumourExtractionOutput\\twitter_handles.txt");
			CSVReader csv_reader = new CSVReader(new FileReader("D:\\RumourExtractionInput\\list_news_org.csv"));
			String [] present_line = null;
			present_line = csv_reader.readNext();
			
			while( (present_line = csv_reader.readNext()) != null)
			{
				String org_name = present_line[1];				
				ResponseList<User> users = twitter.users().searchUsers(org_name, 1);
				
				if(users.size() > 0)
				{
					User first_result_user = users.get(0);		
					output_file_writer.write(org_name + "     @" + first_result_user.getScreenName() + "\r\n");					
				}			
			}		
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		
		try
		{			
			output_file_writer.close();
		}
		catch(Exception e)
		{
			
		}
		

	}
}
