package evnspc.cskh.vn.cskh.object;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ObjectClient implements Serializable {
	public String Command="";
	public String Param2="";
	public String Param3="";
	public String Param4="";
	public String Param5="";
	private Obj_khachhang oKH;
	private Obj_hoadon oHD;

	public String getCommand() {
		return Command;
	}

	public void setCommand(String command) {
		Command = command;
	}

	public String getParam2() {
		return Param2;
	}

	public void setParam2(String param2) {
		Param2 = param2;
	}

	public String getParam3() {
		return Param3;
	}

	public void setParam3(String param3) {
		Param3 = param3;
	}

	public String getParam4() {
		return Param4;
	}

	public void setParam4(String param4) {
		Param4 = param4;
	}

	public String getParam5() {
		return Param5;
	}

	public void setParam5(String param5) {
		Param5 = param5;
	}
	public Obj_khachhang getoKH() {
		return oKH;
	}
	public void setoKH(Obj_khachhang oKH) {
		this.oKH = oKH;
	}

	public Obj_hoadon getoHD() {
		return oHD;
	}

	public void setoHD(Obj_hoadon oHD) {
		this.oHD = oHD;
	}
	
	
	
}
