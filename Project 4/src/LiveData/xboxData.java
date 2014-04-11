package LiveData;
import java.net.*;
import java.awt.image.BufferedImage;
import java.io.*;
import net.sf.json.*;
import javax.imageio.ImageIO;
/**
 * Handles all the of the JSON API data.
 * @author danielstratton
 *
 */
public class xboxData {
	/**
	 * Gets basic gamer information.
	 * @param profile
	 * @return a string of what your information is.
	 * @throws Exception
	 */
	public String profileData(String profile) throws Exception{
		String jso = readURL("https://www.xboxleaders.com/api/profile.json?gamertag=" + profile);
		JSONObject x = JSONObject.fromObject(jso);
		JSONObject getData = (JSONObject)(x.get("Data"));
		String n = "\n";
		return "Your gamertag is: " + getData.getString("Gamertag") + n +
				"Your tier is: " + getData.getString("Tier")+ n +
				"Your Gamer Score is: " + getData.getInt("GamerScore") + n +
				"Your Reputation is: " + getData.getString("Reputation");
	}
	/**
	 * Retrieves the avatar image of the specific gamertag
	 * @param profile
	 * @return BufferedImage ab
	 * @throws Exception
	 */
	public BufferedImage avatarBodyImage(String profile) throws Exception{
		String jso = readURL("https://www.xboxleaders.com/api/profile.json?gamertag=" + profile);
		JSONObject x = JSONObject.fromObject(jso);
		JSONObject getData = (JSONObject)(x.get("Data"));
		String avatarBody = getData.getString("AvatarBody");
		String avatarURL = (String)(avatarBody);
		BufferedImage aB;
		aB = ImageIO.read(new URL(avatarURL));
		return aB;
	}
	/**
	 * Gets a summary of a players history with a specific game.
	 * @param profile
	 * @param g
	 * @return output
	 * @throws Exception
	 */
	public String gameData(String profile,String g) throws Exception{
		String jso = readURL("https://www.xboxleaders.com/api/games.json?gamertag=" + profile);
		JSONObject x = JSONObject.fromObject(jso);
		JSONObject getGameData = (JSONObject)(x.get("Data"));
		JSONArray games = getGameData.getJSONArray("PlayedGames");
		String output = "";
		for(int i = 0; i < games.size();i++){
			JSONObject data = games.getJSONObject(i);
			if(data.getString("Title").equals(g)){
				output = "Title of the Game: " + data.getString("Title") + "\n" 
						+ "You've earned " + data.getInt("EarnedGamerScore") + " gamer points" + "\n"
						+ "You've earned " +data.getInt("EarnedAchievements") + " achievement points" + "\n";
			}
		}
		return output;
	}
	/**
	 * Retrieves the image of the cover art for a specific game.
	 * @param profile
	 * @param g
	 * @return BufferedImage bA
	 * @throws Exception
	 */
	public BufferedImage getBoxArt(String profile,String g) throws Exception{
		String jso = readURL("https://www.xboxleaders.com/api/games.json?gamertag=" + profile);
		JSONObject x = JSONObject.fromObject(jso);
		JSONObject getGameData = (JSONObject)(x.get("Data"));
		JSONArray games = getGameData.getJSONArray("PlayedGames");
		String boxart = "";
		BufferedImage bA = null;
		for(int i = 0;i < games.size();i++){
			JSONObject data = games.getJSONObject(i);
			if(data.getString("Title").equals(g)){
				boxart = data.getString("LargeBoxArt");
				String boxartURL = (String)(boxart);
				bA = ImageIO.read(new URL(boxartURL));
			}
		}
		return bA;
	}
	/**
	 * Gets a summary of a gamer's friend information.
	 * @param profile
	 * @return output
	 * @throws Exception
	 */
	public String friendsData(String profile) throws Exception{
		String jso = readURL("https://www.xboxleaders.com/api/friends.json?gamertag=" + profile);
		JSONObject x = JSONObject.fromObject(jso);
		JSONObject getGameData = (JSONObject)(x.get("Data"));
		String output = "Total friends: " + getGameData.getString("TotalFriends") + "\n" 
						+ "Total friends online: " + getGameData.getString("TotalOnlineFriends") + "\n"
						+ "Total friends offline: " + getGameData.getString("TotalOfflineFriends") + "\n";
		return output;
	}
	/**
	 * Retrieves a summary of a specific gamer inside someone's friend list.
	 * @param profile
	 * @param gamertag
	 * @return output
	 * @throws Exception
	 */
	public String friendGTData(String profile,String gamertag) throws Exception{
		String jso = readURL("https://www.xboxleaders.com/api/friends.json?gamertag=" + profile);
		JSONObject y = JSONObject.fromObject(jso);
		JSONObject getGamertagData = (JSONObject)(y.get("Data"));
		JSONArray friends = getGamertagData.getJSONArray("Friends");
		String output = "";
		for(int i = 0;i< friends.size();i++){
			JSONObject fdata = friends.getJSONObject(i);
			if(fdata.getString("Gamertag").equalsIgnoreCase(gamertag)){
				output = "Gamertag: " + fdata.getString("Gamertag") + "\n";
				JSONObject pInfo = (JSONObject)(fdata.get("PresenceInfo"));
				output += "Currently online? " + pInfo.getString("OnlineStatus") + "\n";
				JSONObject gInfo = (JSONObject)(pInfo.get("Game"));
				output += "Current game: " + gInfo.getString("Title");
				}
			}
		return output;

		}
	/**
	 * Retrieves the gamer tag picture of a certain gamer.
	 * @param profile
	 * @param gamertag
	 * @return fArt
	 * @throws Exception
	 */
	public BufferedImage friendBoxArt(String profile,String gamertag) throws Exception{
		String jso = readURL("https://www.xboxleaders.com/api/friends.json?gamertag=" + profile);
		JSONObject y = JSONObject.fromObject(jso);
		JSONObject getGamertagData = (JSONObject)(y.get("Data"));
		JSONArray friends = getGamertagData.getJSONArray("Friends");
		String friendsArt = "";
		BufferedImage fArt = null;
		for(int i = 0;i< friends.size();i++){
			JSONObject fdata = friends.getJSONObject(i);
			if(fdata.getString("Gamertag").equalsIgnoreCase(gamertag)){
					try{
						friendsArt = fdata.getString("AvatarLarge");
						String fArtURL = (String)(friendsArt);
						fArt = ImageIO.read(new URL(fArtURL));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
		}
		return fArt;
	}
	/**
	 * Opens up a url to a the specific website.
	 * @param webservice
	 * @return result
	 * @throws Exception
	 */
	public String readURL(String webservice) throws Exception{
		 URL oracle = new URL(webservice);
		  BufferedReader in = new BufferedReader(
		        new InputStreamReader(
		        oracle.openStream()));

		  String inputLine;
		  String result = "";

		  while ((inputLine = in.readLine()) != null)
		      result = result + inputLine;

		  in.close();
		  return result;
	}
}
