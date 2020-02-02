package zengrong;

import org.openqa.selenium.WebDriver;

import base.Base;
import myStudy.file.ExcelFile;

public class Bddr {
	public static void Login(WebDriver driver) {
		String str = "H:\\Desktop\\12315\\baidu.xlsx" ;
		String ys = "元素";
		
		String clicklogin = ExcelFile.readXls_x(str,ys,1,4);
		Base.clickId(driver, clicklogin);
		
		
		
		
	}
}
