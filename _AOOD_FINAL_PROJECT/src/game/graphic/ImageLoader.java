package game.graphic;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static HashMap<String,BufferedImage> images = null;
	
	private static BufferedImage loadImage(String name)
	{
		if(images == null)
			images = new HashMap<String,BufferedImage>();
		try {
			if(images.containsKey(name))
				return images.get(name);
			BufferedImage bi = ImageIO.read(ImageLoader.class.getResourceAsStream("/images/"+name));
			images.put(name, bi);
			return bi;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage getImage(String name)
	{
		if(images == null)
			images = new HashMap<String,BufferedImage>();
		if (images.containsKey(name))
			return images.get(name);
		else
			return loadImage(name);
	}

}
