import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class ConfigLoader
{
  public static HashMap<Integer,String> strings = new HashMap();
  
  public static void load()
  {
    try
    {     
      InputStream sourceStream = ConfigLoader.class.getResourceAsStream("config.txt");
	  byte[] sourceArray = new byte[sourceStream.available()];
	  sourceStream.read(sourceArray);
      ByteArrayInputStream byteStream = new ByteArrayInputStream(sourceArray);
      sourceStream.close();
      InputStreamReader reader = new InputStreamReader(byteStream, "UTF-8");
	  BufferedReader e = new BufferedReader(reader);
      
      int i = 0;
      boolean flag = false;
      while (!flag)
      {
        String str = e.readLine();
        if (str == null)
        {
          flag = true;
        }
        else
        {
          str = str.trim();
          if (!str.isEmpty())
          {
            strings.put(i++, str);
          }
        }
      }
      e.close();
      reader.close();
      byteStream.close();
    }
    catch (Exception var5)
    {
      var5.printStackTrace();
    }
  }
  
  public static String getRandomString(){
	Random rand = new Random();
	return strings.get(rand.nextInt(strings.size()-1));
  }
}
