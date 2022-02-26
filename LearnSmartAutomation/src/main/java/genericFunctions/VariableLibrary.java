package genericFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codoid.products.fillo.Recordset;

public class VariableLibrary {

	public static com.codoid.products.fillo.Connection oConnection;
	public static Recordset oRecordset;
	
	public static WebDriver driver = null;
	public WebDriverWait wait = null;
	
	public static final Integer SHORT_WAIT = 2000;
	public static final Integer MEDIUM_WAIT = 6000;
	public static final Integer LONG_WAIT = 10000;
	public static String sQuery ="";
}
