package com.deceive.jvm.ClassToJava.Main;

import com.deceive.jvm.ClassToJava.utils.ClassToHex;
import com.deceive.jvm.ClassToJava.utils.ConvertHexToASCLL;
import com.sun.org.apache.regexp.internal.RE;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static com.deceive.jvm.ClassToJava.io.encode;

/**
 * @author ：cherry
 * @description： 反编译主方法
 * @modified By：
 * @version: $version$
 * @date ：Created in 2019/11/3 14:55
 */
public class MainMethod {
    private static final String CAFEBABE = "CAFEBABE";
    private static Map<Long,String> JDKEdition = new HashMap<Long, String>();
    private static ResourceBundle descriptor = ResourceBundle.getBundle("descriptor");
    private static ResourceBundle ClassAccessFlag = ResourceBundle.getBundle("ClassAccessFlag");
    private static ResourceBundle FieldAccessModifier = ResourceBundle.getBundle("FieldAccessModifier");
    private static ResourceBundle MethodJurisdiction = ResourceBundle.getBundle("MethodJurisdiction");
    private static ResourceBundle codeContent = ResourceBundle.getBundle("codeContent");
    private static Map<Integer, Object> ConstantPool;
    private static List<String> Jurisdiction = new ArrayList<String>();
    private static List<String> ClassInformation = new ArrayList<String>();
    private static List<Map<String,Object>> FieldSets = new ArrayList<Map<String, Object>>();
    private static List<Map<String,Object>> MethodSetList = new ArrayList<Map<String, Object>>();
    public static StringBuffer Hex;
    static {
        int k = 1;
        for(Long i = 45L; i <= 52; ++i,++k){
            JDKEdition.put(i,"JDK 1."+k);
        }
    }

    public static void main(String[] args)throws Exception {
        Hex = ClassToHex.classToHex("C:\\Users\\Cherry blossoms\\Desktop\\MyTest2.class");
        //魔数验证
        if(CAFEBABE.equals(Hex.substring(0,8))){
            Hex.delete(0,8);
            //生成JDK版本，主副版本号
            String JDK = JDKEdition.get(Long.parseLong(Hex.substring(0, 8), 16));
            Hex.delete(0,8);
            //生成常量池
            ConstantPool = Constant.GenerateConstantPool(Hex);
            System.out.println(ConstantPool);
            //类访问标志
            GetJurisdiction();
            //类索引、父类索引、接口计数器
            ClassIndexs();
            //字段个数
            Integer NumberOfFields = Integer.parseInt(Hex.substring(0, 4));
            Hex.delete(0,4);
            //字段集合
            GetFieldSet(NumberOfFields,FieldAccessModifier,true);
            //方法计数器
            Integer MethodCounter = Integer.parseInt(Hex.substring(0, 4));
            Hex.delete(0,4);
            //方法集合
            GetFieldSet(MethodCounter,MethodJurisdiction,false);
            //为静态变量赋值
            for(int i = 0;i < MethodSetList.size();++i){
                Map<String, Object> map = MethodSetList.get(i);
                if("<clinit>".equals(map.get("name"))){
                    List<Map<String, String>> codecontent = (List<Map<String, String>>)map.get("codecontent");
                    for(int s = 0;s <FieldSets.size();++s){
                        Object modifier = FieldSets.get(s).get("modifier");
                        if(modifier != null){
                            if(modifier.toString().indexOf("static") != -1){
                                FieldSets.get(s).put("values",codecontent.get(0).get("values"));
                            }
                        }
                    }
                }
            }
            //附加属性计数器
            //附加属性集合


            System.out.println("类访问标志 " + Jurisdiction);
            System.out.println("类索引、父类索引、接口计数器   " + ClassInformation);
            System.out.println("字段集合 " + FieldSets);
            System.out.println("方法集合  " + MethodSetList);

            out();



        }
    }

    public static void GetJurisdiction(){
        String AccessFlag = Hex.substring(0, 4);
        Hex.delete(0,4);
        String substring = AccessFlag.substring(AccessFlag.length() - 1);
        String ks = "1000";
        for (int len = 1;len < AccessFlag.length()+1;++len){
            for (int lens = len -1;lens < len;++lens) {
                int i = Integer.parseInt(AccessFlag.substring(lens, len));
                int i1 = i * Integer.parseInt(ks);
                if(i1 != 0){
                    String string = ClassAccessFlag.getString(i1+"");
                    Jurisdiction.add(string);
                }
                ks = ks.substring(0,ks.length()-1);
            }
        }
    }

    public static void ClassIndexs(){
        String ClassIndexs = Hex.substring(0, 12);
        Hex.delete(0,12);
        for(int index=0;index < ClassIndexs.length();index+=4){
            String ClassIndexHex = ClassIndexs.substring(index, index + 4);
            int i = Integer.parseInt(ClassIndexHex,16);
            if(index != 8 || i != 0){
                if(index != 8){
                    ClassInformation.add((String) ConstantPool.get(ConstantPool.get(i)));
                }
            }
        }
    }

    public static void GetFieldSet(Integer NumberOfFields,ResourceBundle transfer,boolean b){
        while (NumberOfFields != 0){

            String FieldHexContent = Hex.substring(0, 16);
            Hex.delete(0,16);
            Map<String,Object> FieldSet = new HashMap<String, Object>();
            for(int index=0;index < FieldHexContent.length();index+=4){
                String FieldIndexHex = FieldHexContent.substring(index, index + 4);
                switch (index){
                    case 0:
                        if(!"0000".equals(FieldIndexHex)){
                            if("0009".equals(FieldIndexHex)){
                                FieldSet.put("modifier",transfer.getString("0001") +" "+ transfer.getString("0008"));
                            }else{
                                FieldSet.put("modifier",transfer.getString(FieldIndexHex));
                            }
                        }
                        break;
                    case 4:
                    case 8:
                        String s = null;
                        if(!"0000".equals(FieldIndexHex)){
                            int i = Integer.parseInt(FieldIndexHex, 16);
                            s = ConstantPool.get(Integer.parseInt(FieldIndexHex,16)).toString();
                        }
                        if(index == 8 ){
                            if(s.length() == 1){
                                s = descriptor.getString(s);
                            }else{
                                if(!"(".equals(s.substring(0,1))){
                                    s = s.substring(s.lastIndexOf("/")+1,s.lastIndexOf(";"));
                                }
                            }                        }
                        FieldSet.put(index == 4 ?"name" : "type", s);
                        break;
                    default:
                        break;
                }
            }
            if(b){
                FieldSets.add(FieldSet);
            }else{
                List<Map<String, String>> maps = CodeUnscramble(FieldSet);
                FieldSet.put("codecontent",maps);
                MethodSetList.add(FieldSet);
            }
            NumberOfFields--;
        }
    }

    private static  List<Map<String,String>> CodeUnscramble(Map<String, Object> fieldSet) {
        Integer variableindex = 0;
        Map<String,Map<String,Object>> codeMap = new HashMap<String, Map<String,Object>>();
        Map<String,Object> codeAttributes = new HashMap<String, Object>();
        List<Map<String,String>> listCode = new ArrayList<>();
        String substring = Hex.substring(0, 12);
        Hex.delete(0,12);
        int size = Integer.parseInt(substring.substring(4), 16)*2;
        StringBuffer code = new StringBuffer(Hex.substring(0, size));
        Hex.delete(0,size);
        codeAttributes.put("max_stack",Integer.parseInt(code.substring(0,4),16));
        codeAttributes.put("max_locals",Integer.parseInt(code.substring(4,8),16));
        codeAttributes.put("code_length",Integer.parseInt(code.substring(8,16),16));
        code.delete(0,16);
        String codeContent = code.substring(0, (Integer) codeAttributes.get("code_length")*2);
        code.delete(0,(Integer) codeAttributes.get("code_length")*2+12);
        //解读方法内容
        for(int i = 0;i < codeContent.length();i+=2){
            Map<String,String> map = new HashMap<>();
            String substring1 = codeContent.substring(i, i + 2);
            if(!"1B".equals(substring1)){
                String string = MainMethod.codeContent.getString(substring1);
                if(string.matches("^[0-9]*$")) {
                    if(!"B7".equals(substring1)) {
                        String substring2 = codeContent.substring(i + 2, i + 2 + Integer.parseInt(string));
                        String o = null;
                        String s = null;
                        if(!"10".equals(substring1)){
                            o = ConstantPool.get(Integer.parseInt(substring2, 16)).toString();
                            s = CodeRecursion(o,substring1);
                        }else{
                            codeAttributes.put("push",Integer.parseInt(substring2, 16));
                        }
                        switch (substring1){
                            case "B3":
                                String[] split1 = s.split("&");
                                map.put("type","true");
                                map.put("name",split1[1]);
                                map.put("values",codeAttributes.get("push").toString());
                                break;
                            case "B5":
                                String index = i - Integer.parseInt(string) + "";
                                String values;
                                try {
                                     values = codeAttributes.get("push").toString();
                                }catch (Exception e){
                                    map.put("name",s.substring(s.indexOf(".")+1,s.lastIndexOf(":")));
                                    map.put("variableExpected","1");
                                    map.put("type",descriptor.getString(s.substring(s.lastIndexOf(":") + 1)));
                                    values = "null";
                                }
                                codeAttributes.remove(index);
                                Map<String, Object> stringObjectMap = FieldSets.get(variableindex);
                                String type = stringObjectMap.get("type").toString();
                                if("String".equals(type)) {
                                    values = values.replace("[","\"").replace("]","\"");
                                }
                                if(stringObjectMap.get("values") == null){
                                    stringObjectMap.put("values",values);
                                }
                                ++variableindex;
                                break;
                            case "B6":
                                for(int indexs = 0;indexs < listCode.size();++indexs){
                                    String[] split = s.split("&");
                                    Map<String, String> imap = listCode.get(indexs);
                                    String types = imap.get("type");
                                    if(split[0].substring(split[0].lastIndexOf("/")+1).equals(types)){
                                        map.put("type","false");
                                        map.put("respondents",imap.get("name"));
                                        map.put("name",split[1]);
                                        map.put("value", codeAttributes.get("push").toString());
                                    }
                                }
                                break;
                            case "BB":
                                String types = s.substring(s.lastIndexOf("/") + 1);
                                map.put("type",types);
                                map.put("name",types);
                                map.put("method"," = new "+types+"()");
                                break;
                            default:
                                break;
                        }
                        if(map.size() > 0){
                            listCode.add(map);
                        }
                        if(!"10".equals(substring1) && !"B8".equals(substring1)) {
                            codeAttributes.put("push", Arrays.asList(s));
                        }
                    }
                    i+=Integer.parseInt(string);
                }
                if(string.indexOf("Push_") != -1){
                    codeAttributes.put("push",string.substring(5));
                }
            }else{

            }
        }
        //异常偏移跳过
        //解读局部变量
        code.delete(0,Integer.parseInt(code.substring(0, 8), 16)*2+12);
        System.out.println("code："+code);
        Map<String,String> stringMap = new HashMap<>();
        if(code.length() != 0){
            int i = Integer.parseInt(code.substring(0, 8), 16);
            int num = Integer.parseInt(code.substring(8, 12), 16);
            StringBuilder localVariableTable = new StringBuilder(code.substring(12, i * 2 + 4));
            code.delete(0,i*2+4);
            int ss = 0;
            for(int is = 1;is <= num;++is){
                localVariableTable.delete(0,8);
                if(is >= 2){
                    localVariableTable.delete(0,12);
                }
                System.out.println(localVariableTable);

                String s = ConstantPool.get(Integer.parseInt(localVariableTable.substring(0, 4), 16)).toString();
                String s1 = ConstantPool.get(Integer.parseInt(localVariableTable.substring(4, 8), 16)).toString();
                boolean sk = true;
                for (Map<String, String> stringStringMap : listCode) {
                    if (s1.length() > 1){
                        if(stringStringMap.get("type").equals(s1.substring(s1.lastIndexOf("/")+1,s1.lastIndexOf(";")))){
                            stringStringMap.put("name",s);
                            stringMap.put(stringStringMap.get("type"),s);
                            sk = false;
                            break;
                        }
                    }
                }
                if(sk){
                    Map<String,String> maps = new HashMap<>();
                    maps.put("name",s);
                    maps.put("desc",s1);
                    maps.put("index",ss+"");
                    ss++;
                    listCode.add(maps);
                }
            }
        }

        for (Map<String, String> stringStringMap : listCode) {
            try {
                String respondents = stringStringMap.get("respondents");
                if(respondents != null){
                    stringStringMap.put("respondents",stringMap.get(respondents));
                }
            }catch (Exception e){

            }

        }
        codeMap.put(ConstantPool.get(Integer.parseInt(substring.substring(0, 4),16)).toString(),
                codeAttributes);


        return listCode;
    }

    private static String CodeRecursion(String o,String Codedesc) {
        StringBuilder o1 = new StringBuilder();
        if(o.matches("^[0-9]*$")){
             o1.append(ConstantPool.get(Integer.parseInt(o)));
        }
        if((o.contains("]") && o.contains("[") && o.contains(","))){
            String[] split = o.substring(o.indexOf("[")+1,o.lastIndexOf("]")).split(",");
            for(int is = 0;is < split.length;++is){
                o1.append(ConstantPool.get(Integer.parseInt(split[is].replace(" ",""))));
                if(!o1.toString().matches("^[0-9]*$")){
                    o1.append("&");
                }
            }
        }
        if(o1.toString().matches("^[0-9]*$")){
            return CodeRecursion(o1.toString(),Codedesc);
        }
        if((o1.toString().contains("]") && o1.toString().contains("[") && o1.toString().contains(","))){
            String[] split1 = o1.toString().replace("[",",").split("]");
            StringBuilder stringBuilder = new StringBuilder(split1[0].replace(" ",""));
            stringBuilder.insert(0,"[").append("]");
            return CodeRecursion(stringBuilder.toString(),Codedesc);
        }
        if("B5".equals(Codedesc)){
            String[] split = o1.toString().split("&");
            o1.delete(0,o1.length());
            o1.append(split[0] + "." + split[1] + ":" + split[2]);
        }
        return o1.toString();
    }

    public static void out() throws IOException {
        StringBuffer result = new StringBuffer();
        String s = ClassInformation.get(0);
        result.append("package " + s.substring(0,s.lastIndexOf("/")) + ";\n");
        result.append(Jurisdiction.get(1) + " class " + s.substring(s.lastIndexOf("/")+1) + "{\n");
        //字段
        for (Map<String, Object> fieldSet : FieldSets) {
            String modifier = fieldSet.containsKey("modifier") ? fieldSet.get("modifier").toString() : "";
            String sk = "";
            if(modifier!=""){
                sk = " ";
            }
            result.append("\t" + modifier +  sk + fieldSet.get("type") + " "
                    + fieldSet.get("name") + " = " + fieldSet.get("values") + ";\n");
        }
        //方法
        for (Map<String, Object> map : MethodSetList) {
            if(!"<clinit>".equals(map.get("name").toString())){
                if ("<init>".equals(map.get("name").toString())){
                    String s1 = ClassInformation.get(0);
                    map.put("name",s1.substring(s1.lastIndexOf("/")+1));
                }
                String type1 = map.get("type").toString();
                map.put("type",type1.substring(0,type1.lastIndexOf(")")+1));
                String type = map.get("type").toString();
                if(map.get("type").toString().length() > 2){
                    List<Map<String, String>> codecontent = (List<Map<String, String>>) map.get("codecontent");
                    for (Map<String, String> stringMap : codecontent) {
                        if(stringMap.containsKey("desc")){
                            System.out.println(stringMap);
                            //[Ljava/lang/String;
                            String mapdesc = type.substring(type.indexOf("(") + 1, type.lastIndexOf(")"));
                            String desc = stringMap.get("desc");
                            if(type.length() > 3){
                                String str = type.substring(1, 3);
                                String substring = mapdesc.substring(mapdesc.lastIndexOf("/") + 1, mapdesc.lastIndexOf(";"));
                                String sc = "[".equals(str.substring(0,1)) && "[".equals(str.substring(1,2)) ?
                                        substring+ "[][]" : substring+ "[]";
                                if(mapdesc.equals(desc)){
                                    map.put("type","("+ sc + " " + stringMap.get("name") +")");
                                }
                            }else{
                                if(mapdesc.equals(desc)){
                                    map.put("type","("+ descriptor.getString(mapdesc) + " " + stringMap.get("name") +")");
                                }
                            }
                        }
                    }
                }
                List<Map<String, String>> codecontent = (List<Map<String, String>>) map.get("codecontent");
                        result.append("\t" + map.get("modifier") + " " + descriptor.getString(type1.substring(type.lastIndexOf(")")+1)) + " "
                        + map.get("name") + map.get("type") + "{\n");
                for (Map<String, String> stringMap : codecontent) {
                    if(stringMap.containsKey("type")){
                        if(!"null".equals(stringMap.get("type"))){
                            if(stringMap.containsKey("variableExpected")){
                                String name = null;
                                String desc = null;
                                for (Map<String, String> stringStringMap : codecontent) {
                                    System.out.println(stringStringMap);
                                    if("1".equals(stringStringMap.get("index")) && "I".equals(stringStringMap.get("desc"))){
                                        name = stringStringMap.get("name");
                                    }
                                    if("0".equals(stringStringMap.get("index")) && "Lcom/deceive/jvm/bytecode/MyTest2;".equals(stringStringMap.get("desc"))){
                                        desc = stringStringMap.get("name");
                                    }
                                }
                                result.append("\t" + desc + "." + stringMap.get("name") + " = " +name + ";\n");
                            }else{
                                if(!"false".equals(stringMap.get("type")) && !"true".equals(stringMap.get("type"))){
                                    result.append("\t" + stringMap.get("type") + " " + stringMap.get("name")
                                    );
                                }else{
                                    boolean sc = true;
                                    String sk = "";
                                    String sk1 = "";
                                    if(stringMap.containsKey("respondents")){
                                        result.append("\t" + stringMap.get("respondents") + ".");
                                        sc = false;
                                    }
                                    if(sc){
                                        sk = "\t";
                                    }
                                    if("false".equals(stringMap.get("type"))){
                                        sk1 = "(" + stringMap.get("value") + ")";
                                    }else{
                                        sk1 = " = "+stringMap.get("values");
                                    }
                                    result.append( sk + stringMap.get("name") + sk1
                                            + "; \n");
                                }
                                if(stringMap.containsKey("method")){
                                    result.append( stringMap.get("method") + ";\n");
                                }
                            }

                        }
                    }
                }
                result.append("\t}\n");
            }

        }


        result.append("}");

        System.out.println(result);
        System.out.println(result);
        //输出转换后的文件要用字符输出流
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("C:\\Users\\Cherry blossoms\\Desktop\\MyTest1.java"));
        int readSize = 16;
        byte[] buffer_read = new byte[readSize];
        String line;
        int i = 0;
        String s1 = result.toString();
        System.out.println(s1);
        line = encode(buffer_read,readSize);
        bw.write(s1);
        bw.newLine();
        bw.close();
    }
}


class Constant{
    private static ResourceBundle ConstantType = ResourceBundle.getBundle("ConstantType");
    private static Integer ConstantPoolKey = 1;

    public static Map<Integer,Object> GenerateConstantPool(StringBuffer Hex){
        Map<Integer,Object> ConstantPool;
        //获得常量池数量
        Integer ConstantPoolsize = Integer.parseInt(Hex.substring(0, 4), 16)-1;
        //限定常量池数量
        ConstantPool = new HashMap<Integer, Object>(ConstantPoolsize);
        Hex.delete(0,4);
        while (ConstantPool.size() != ConstantPoolsize){
            List list = new ArrayList();
            Integer content = null;
            String UTF8STR = null;
            Integer ideaxSize = null;
            //获得标记符 u1 u2 u4 u8
            int i = Integer.parseInt(Hex.substring(0, 2), 16);
            Hex.delete(0,2);
            //获得标记符对应所占的数量
            if(i != 1){
                ideaxSize = Integer.parseInt(ConstantType.getString(i + ""));
            }
            switch (i){
                case 1:
                    Integer l = Integer.parseInt(Hex.substring(0, 4), 16);
                    ideaxSize = 4+l*2;
                    String substring = Hex.substring(4, ideaxSize);
                    UTF8STR = ConvertHexToASCLL.convertHexToString(substring);
                    break;
                case 7:
                case 8:
                    content = Integer.parseInt(Hex.substring(0, ideaxSize), 16);
                    break;
                case 9:
                case 10:
                case 11:
                case 12:
                    list.add(Integer.parseInt(Hex.substring(0,ideaxSize/2),16));
                    list.add(Integer.parseInt(Hex.substring(ideaxSize/2,ideaxSize),16));
                    break;
                default:
                    break;
            }
            ConstantPool.put(ConstantPoolKey,(list != null && list.size() != 0 ? list : (content != null ? content : UTF8STR)));
            Hex.delete(0,ideaxSize);
            ConstantPoolKey++;
        }
        MainMethod.Hex = Hex;
        return ConstantPool;
    }
}
