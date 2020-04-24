package RateSC.RateSCAPI;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Category {
	List<RatedObject> listObjects;
	String name;
	
	Category(String name) {
		listObjects= Collections.synchronizedList(new ArrayList<RatedObject>());
		this.name= name;
	}
	
	public List<RatedObject> getListObjects() {
		return listObjects;
	}

	public void setListObjects(ArrayList<RatedObject> listObjects) {
		this.listObjects = listObjects;
	}
	
}
