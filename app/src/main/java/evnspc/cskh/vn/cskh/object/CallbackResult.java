package evnspc.cskh.vn.cskh.object;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CallbackResult implements Serializable {
	private String Command="";
	private String ResultString="";
	private String diengiai1="";
	private Obj_khachhang oKH;
	private List<Obj_hoadon> list_HD;
	private List<Obj_hdonctiet> list_HDCT;
	private List<Obj_sanluong> list_oSL;
	public CallbackResult() {
		super();
	}
	
	public String getDiengiai1() {
		return diengiai1;
	}

	public void setDiengiai1(String diengiai1) {
		this.diengiai1 = diengiai1;
	}

	public String getCommand() {
		return Command;
	}
	public void setCommand(String command) {
		Command = command;
	}
	public String getResultString() {
		return ResultString;
	}
	public void setResultString(String resultString) {
		ResultString = resultString;
	}
	public Obj_khachhang getoKH() {
		return oKH;
	}
	public void setoKH(Obj_khachhang oKH) {
		this.oKH = oKH;
	}

	public List<Obj_hoadon> getList_HD() {
		return list_HD;
	}

	public void setList_HD(List<Obj_hoadon> list_HD) {
		this.list_HD = list_HD;
	}

	public List<Obj_hdonctiet> getList_HDCT() {
		return list_HDCT;
	}

	public void setList_HDCT(List<Obj_hdonctiet> list_HDCT) {
		this.list_HDCT = list_HDCT;
	}

	public List<Obj_sanluong> getList_oSL() {
		return list_oSL;
	}

	public void setList_oSL(List<Obj_sanluong> list_oSL) {
		this.list_oSL = list_oSL;
	}

	
	
	
	
}
