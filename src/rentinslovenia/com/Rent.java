package rentinslovenia.com;

public class Rent {

	public String _ID;
	public String _name;
	public String _address;
	public String _eMail;
	public String _X;
	public String _Y;
	public String _phone;
	public String _IDcat;
	public String _type;
	public String _image;
	public String _price;
	
	public String _description;//opis ponudbe
	public String _calling;//klic èe je mogoè
	public String _rate;//offer rate
	public String _numOfVotes;//number of votes
	
	//offer times
	public String _ponOd;
	public String _ponDo;
	public String _torOd;
	public String _torDo;
	public String _sreOd;
	public String _sreDo;
	public String _cetOd;
	public String _cetDo;
	public String _petOd;
	public String _petDo;
	public String _sobOd;
	public String _sobDo;
	public String _nedOd;
	public String _nedDo;
	
	//in which languages can we speak
	public String _slo;
	public String _eng;
	public String _ger;
	public String _cro;
	public String _ita;
	public String _fra;
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return _name+" "+_address;
	}
	
	public static String RestUrl="http://rent-in-slovenia.com/Rent/Rent.svc/json/";
}
