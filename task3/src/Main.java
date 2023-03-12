//3.** Дана json строка (можно сохранить в файл и читать из файла)
//        [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
//        Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
//        Пример вывода:
//        Студент Иванов получил 5 по предмету Математика.
//        Студент Петрова получил 4 по предмету Информатика.
//        Студент Краснов получил 5 по предмету Физика.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("file.txt"));
        String str ;
        while ((str = br.readLine())!= null) {
            sb.append(str);
        }
        br.close();
        str = sb.toString();
        String[] params = str.split("},\\{");

        Map<String, String> arrayMap[] = new HashMap[params.length];
        for (int i = 0; i < params.length;i++){
            Map<String, String> map = new HashMap<>();
            String[] mapValue = params[i].split(",");
            for (String item : mapValue){
                String[] keyValue = item.split(":");
                map.put(removeUnnecessary(keyValue[0]),removeUnnecessary(keyValue[1]));
                arrayMap[i] = map;
            };
        }
        sb = new StringBuilder();
        for (Map<String,String> map : arrayMap) {
            sb.append("Cтудент ");
            sb.append(map.get("фамилия"));
            sb.append(" получил ");
            sb.append(map.get("оценка"));
            sb.append(" по предмету ");
            sb.append(map.get("предмет"));
            sb.append(" \n ");
        }
        System.out.println(sb);


    }
    public static String removeUnnecessary(String str){
        str = str.replace("[","");
        str =str.replace("{","");
        str =str.replace("}","");
        str =str.replace("]","");
        str =str.replace("\"","");

        return str;
    }
}