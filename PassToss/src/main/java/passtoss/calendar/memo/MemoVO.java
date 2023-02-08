package passtoss.calendar.memo;
public class MemoVO {

	int memo_num;
	String id;
	String memo;
	String reg_date;
	
	
	public MemoVO(String id2, String memo2) {
		// TODO Auto-generated constructor stub
	}
	public MemoVO() {
		// TODO Auto-generated constructor stub
	}
	public int getMemo_num() {
		return memo_num;
	}
	public void setMemo_num(int memo_num) {
		this.memo_num = memo_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
