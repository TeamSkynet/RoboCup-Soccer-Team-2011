import java.net.*;
import java.util.*;
import java.io.*;
//*******************************************
// 			- ObjMemory Class -				*
// 											*
// This class implements an array list to	*
// store and access ObjInfos				*
//											*
//*******************************************
class ObjMemory {
	
	//  * Default constructor *
	public ObjMemory() {
		setTime(0);
	}
	
	// * ObjMemory Constructor *
	// Input:	-ArrayList<ObjInfo> ObjArray - to store ObjInfos in
	//			-int t - the time integer
	//
	// Output: none
	public ObjMemory(ArrayList<ObjInfo> ObjArray, int t) {
		this.ObjArray = ObjArray;
		this.time = t;
	}
	
	// * A method to add ObjInfos to the ArrayList *
	// Input:	-ObjInfo newInfo - the ObjInfo to add to the ArrayList
	//
	// Output:	none
	public void addInfo(ObjInfo newInfo) {
		ObjArray.add(newInfo);
	}
	
	// * A method to access the time integer *
	// Input: none
	//
	// Output: integer containing the time
	public int getTime() {
		return time;
	}
	
	// * A method to set time *
	// Input: int t - the time integer
	//
	// Output: none
	public void setTime(int t) {
		this.time = t;
	}
	
	// * A method to access the ObjArray size *
	// Input: none
	//
	// Output: integer containing ObjArray size
	public int getSize() {
		return ObjArray.size();
	}
	
	// * A method to access ObjInfo data by index *
	// Input: int index - the index of the ObjInfo to return
	// 
	// Output: An ObjInfo located at given index
	public ObjInfo getObj(int index) {
		return ObjArray.get(index);
	}
	
	// * A method to access ObjInfo data by name *
	// Input: String name - name of object to be searched for
	//
	// Output: An ObjInfo with the name being searched for
	public ObjInfo getObj(String name) {
		String searchName = "";
		ObjInfo foundObj = new ObjInfo();
		int i = 0;
		while(searchName.compareTo(name) != 0) {
			foundObj = getObj(i);
			searchName = foundObj.getObjName();
			i++;
		}
		
		return foundObj;
	}

	public ArrayList<ObjFlag> FlagArray;
	// ArrayList to store ObjInfo
	public ArrayList<ObjInfo> ObjArray;
	// The time integer
	private int time;
	
}
