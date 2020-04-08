package RateSC;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Category {
	List<RatedObject> listObjects;
	
	Category(Category parentCategory) {
		listObjects= Collections.synchronizedList(new ArrayList<RatedObject>());
		parentCategory= parentCategory;
	}
	
	public List<RatedObject> getListObjects() {
		return listObjects;
	}

	public void setListObjects(ArrayList<RatedObject> listObjects) {
		this.listObjects = listObjects;
	}
	
}
