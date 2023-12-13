package sales.model;

public class SalesList {

	private int s_Num;	
	private int p_No;
	private String p_Name;
	private int s_Seoul;
	private int s_Suwon;
	private int s_Incheon;
	private String s_Date;
	
	public SalesList (int s_Num, int p_No, String p_Name, int s_Seoul, int s_Suwon, int s_Incheon, String s_Date) {
		this.s_Num = s_Num;		
		this.p_No = p_No;
		this.p_Name = p_Name;
		this.s_Seoul = s_Seoul;
		this.s_Suwon = s_Suwon;
		this.s_Incheon = s_Incheon;
		this.s_Date = s_Date;
	}

	public int getS_Num() {
		return s_Num;
	}

	public void setS_Num(int s_Num) {
		this.s_Num = s_Num;
	}

	public int getP_No() {
		return p_No;
	}

	public void setP_No(int p_No) {
		this.p_No = p_No;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public int getS_Seoul() {
		return s_Seoul;
	}

	public void setS_Seoul(int s_Seoul) {
		this.s_Seoul = s_Seoul;
	}

	public int getS_Suwon() {
		return s_Suwon;
	}

	public void setS_Suwon(int s_Suwon) {
		this.s_Suwon = s_Suwon;
	}

	public int getS_Incheon() {
		return s_Incheon;
	}

	public void setS_Incheon(int s_Incheon) {
		this.s_Incheon = s_Incheon;
	}

	public String getS_Date() {
		return s_Date;
	}

	public void setS_Date(String s_Date) {
		this.s_Date = s_Date;
	}

	
}
