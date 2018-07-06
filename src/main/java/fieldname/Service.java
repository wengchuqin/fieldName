package fieldname;

import domian.MtOrgGradeDTO;
import org.junit.Test;
import top.chuqin.utils.tools.ColumnsConfig;
import top.chuqin.utils.tools.SimpleXlsxExportor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 翁楚钦（wengchuqin）
 * @date 18-7-6 下午6:46
 */
public class Service {
    @Test
    public void test() throws IOException {
        List<FiledTypeAndName> datas = reflect(MtOrgGradeDTO.class);
        SimpleXlsxExportor simpleXlsxExportor = new SimpleXlsxExportor();
        ColumnsConfig columnsConfig = new ColumnsConfig();
        columnsConfig.add("名字", "name");
        columnsConfig.add("类型", "type");

        File file = new File("/home/wengchuqin/Desktop/hello.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        //文件输出流
        FileOutputStream out = new FileOutputStream(file);

        simpleXlsxExportor.export(datas, columnsConfig, out);
    }



    public static List<FiledTypeAndName> reflect(Class clazz) {
        List<FiledTypeAndName> datas = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            String typeName = type.getTypeName();
            if(typeName.contains(".")){
                typeName = typeName.substring(typeName.lastIndexOf(".") + 1);
            }

            System.out.println(typeName);
            String name = field.getName();
            System.out.println(name);
            System.out.println();
            datas.add(new FiledTypeAndName(typeName, name));
        }
        return datas;
    }

}

class FiledTypeAndName{
    String type;
    String name;

    public FiledTypeAndName(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
