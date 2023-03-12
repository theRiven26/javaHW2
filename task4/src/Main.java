import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Main {
    public static Double errorNum = 9999999999.0;
    public static void main(String[] args) throws Exception{

        Logger logger =  Logger.getLogger(Main.class.getName());
        FileHandler fh = new FileHandler("log.xml");
        logger.addHandler(fh);
        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);

        Scanner iScanner = new Scanner(System.in);
        System.out.print("=>");
        String action = iScanner.nextLine();
        iScanner.close();
        action.replace(" ","");

        StringBuilder sb = new StringBuilder();

        int indexAction = FindActionPosition(action);
        if (indexAction >= 0) {
            Double x = Double.parseDouble(action.substring(0, indexAction));
            Double y = Double.parseDouble(action.substring(indexAction + 1, action.length()));
            Double actionResult = GetAction(action, x, y);
            sb.append("Ввод пользователя: ");
            sb.append(action);
            sb.append(" результат: ");
            sb.append(actionResult);
            if (actionResult == errorNum){
                logger.warning(sb.toString());
            } else{
                logger.info(sb.toString());
            }

            System.out.println(action + " = " + actionResult);
        }
    }
    public static int FindActionPosition(String action){

        int indexAction = - 1;
        if (action.contains("+")){
            indexAction = action.indexOf("+");
        } else if (action.contains("-")) {
            indexAction = action.indexOf("-");
        } else if (action.contains("*")) {
            indexAction = action.indexOf("*");
        } else if (action.contains("/")) {
            indexAction = action.indexOf("/");
        }
        return indexAction;
    }

    public static Double GetAction(String action, Double x, Double y){
        if (action.contains("+")){
            return  x + y;
        } else if (action.contains("-")) {
            return  x - y;
        } else if (action.contains("*")) {
            return  x * y;
        } else if (action.contains("/")) {
            if (y == 0){
                return errorNum;
            }
            return  x / y;
        };
        return errorNum;
    }

}