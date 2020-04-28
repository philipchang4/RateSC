package RateSC.RateSCAPI;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Category {
	List<RatedObject> listObjects;
	String name;
	Integer ID;
	
	Category(String name, Integer ID) {
		listObjects= Collections.synchronizedList(new ArrayList<RatedObject>());
		this.name= name;
		this.ID=ID;
	}
	
	public List<RatedObject> getListObjects() {
		return listObjects;
	}

	public void setListObjects(ArrayList<RatedObject> listObjects) {
		this.listObjects = listObjects;
	}
	
}
