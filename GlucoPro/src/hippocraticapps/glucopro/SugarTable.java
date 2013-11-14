package hippocraticapps.glucopro;

public class SugarTable implements DatabaseTable{

	@Override
	public void insert(GlucoDBAdapter adptr, Record record) {
		// TODO Auto-generated method stub
		
	}
	static SugarRecord[] peekRange( GlucoDBAdapter adptr,int maxSize ){
		SugarRecord[] records = new SugarRecord[maxSize];
		//make an adapter query call to get the nth entry from the top, starting newest first
		
		return records; // return array of records
	}
}
