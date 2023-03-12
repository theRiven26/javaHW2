//Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
import java.util.Arrays;
import java.util.logging.*;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException{

        Logger logger =  Logger.getLogger(Main.class.getName());
        FileHandler fh = new FileHandler("log.xml");
        logger.addHandler(fh);
        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);

        int[] array = new int[] {1,25,6,18,9,4,5,30};
        int temp;
        for(int i = 1; i < array.length; i++){
            for(int j = 0; j < array.length - i; j++){
                if(array[j + 1] < array[j]){
                    StringBuilder sb = new StringBuilder();
                    sb.append("Поменяли местами индексы: ");
                    sb.append(j);
                    sb.append(" и ");
                    sb.append(j + 1);
                    sb.append(" получили массив:");

                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;

                    sb.append(Arrays.toString(array));
                    logger.log(Level.INFO, sb.toString());
                }

            }
        }
    }
}