//Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder.
// Данные для фильтрации приведены ниже в виде json строки.
//        Если значение null, то параметр не должен попадать в запрос.
//        Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{

        StringBuilder sb = new StringBuilder("select * from students where ");
        BufferedReader br = new BufferedReader(new FileReader("file.txt"));
        String str = br.readLine();
        br.close();
        str = str.replace("{", "");
        str = str.replace("}", "");
        str = str.replace("\"","");
        String[] params = str.split(",");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String[] keyValue = param.split(":");
            if (!keyValue[1].contains("null")) {
                map.put(keyValue[0], keyValue[1]);
            }

        }
        int count = 1;
        for (Map.Entry<String, String> item : map.entrySet()) {
            sb.append("Students.");
            sb.append(item.getKey().toString());
            sb.append(" = ");
            sb.append(item.getValue());
            count ++;
            if (count <= map.size()){
                sb.append(" and ");
            }
        }
        sb.append("; ");
        System.out.println(sb);
    }
}