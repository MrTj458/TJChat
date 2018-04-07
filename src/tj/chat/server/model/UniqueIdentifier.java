package tj.chat.server.model;

import java.util.ArrayList;
import java.util.Collections;

public class UniqueIdentifier
{
	private static ArrayList<Integer> ids = new ArrayList<Integer>();
	private static final int RANGE = 100;
	
	//Private constructor so you cannot accidentally create this class;
	private UniqueIdentifier(){}
	
	static
	{
		for(int i = 0; i < RANGE; i++)
		{
			ids.add(i);
		}
		Collections.shuffle(ids);
	}
	
	public static int getIdentifier()
	{
		int idIndex = (int) Math.random() * ids.size();
		int id = ids.get(idIndex);
		ids.remove(idIndex);
		return id;
	}
	
	public static void addID(int id)
	{
		ids.add(id);
		Collections.shuffle(ids);
	}
}
