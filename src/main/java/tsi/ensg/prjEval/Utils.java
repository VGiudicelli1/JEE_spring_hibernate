package tsi.ensg.prjEval;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String formatDate(Date date) {
        return (new SimpleDateFormat("MM/dd/yy")).format(date);
    }
}
