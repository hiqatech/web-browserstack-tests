package common.data;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilHelp {

	 public static List<String> storedTexts = Arrays.asList("","","","","","","","","","");
	 static HashMap<String, String> testDataMap = new HashMap<>();

	    public static String getTimeStamp(String format){
	        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
	        return timeStamp;
	    }

	public static String readTestData(String featureFileName, String sheetName, String testId){
		try {
	        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\templates\\" + featureFileName +"\\"+ featureFileName + ".xlsx");   //creating a new file instance
	        FileInputStream fis = new FileInputStream(file);
	        	XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet sh = wb.getSheet(sheetName);
				Row headers = sh.getRow(0);
				int rown = Integer.valueOf(testId)+1;
				Row testRow = sh.getRow(1);

				for(int colIdx=0 ; colIdx < headers.getLastCellNum(); colIdx++) {
				    Cell valueCell = testRow.getCell(colIdx);
				    Cell keyCell = headers.getCell(colIdx);
				    String key = keyCell.getStringCellValue().trim();
				    String value = "";
				    switch(valueCell.getCellType()) {
		            case NUMERIC:
		            	value = String.valueOf(valueCell.getNumericCellValue());break;
		            case STRING:
		            	value = valueCell.getStringCellValue().trim().toString();break;

		        }
				    testDataMap.put(key, value);
				   }

	        return "PASS : TestData = " + printMap(testDataMap);
		}
	    catch(Exception ex)
	    {System.out.println(ex.toString()); return  ex.toString();}
	}

	public static String getTestData(String key){
		return testDataMap.get(key);
	}


    public static String prepText(String text){
        try
        {
            String extension = "ext";
            String addition = "add";

            if(text.contains("TimeStamp")) {
				text = text.replace("+TimeStamp","-" + getTimeStamp("YYYY-MM-DD-HH-mm-ss-SSS"));
			}

            if(text.contains("+Text"))
            {
                int index = Integer.parseInt(text.replaceAll("\\D+",""));
                text = text.substring(0,text.indexOf("+")) + storedTexts.get(index);
            }
            else if(text.contains("+"))
            {
                extension = text.substring(text.indexOf("+"),text.length());
                text = text.replace(extension,"");
                extension = extension.replace("+","");
            }
            else if(text.contains("+"))
            {
                addition = text.substring(text.indexOf("+"),text.length());
                text = text.replace(addition,"");
            }

            if(text.contains("Text"))
            {
                int index = Integer.parseInt(text.replaceAll("\\D+",""));
                text = storedTexts.get(index);
            }

            if(!(addition.equalsIgnoreCase("add"))) {
				text = text + addition;
			}
            if(!(extension.equalsIgnoreCase("ext"))) {
				text = text + extension;
			}

            return text;
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String storeText(String text, String TextX){
        try
        {
            int index = Integer.parseInt(TextX.replaceAll("\\D+",""));
            storedTexts.set(index,text);
            return "PASS";
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String getStoredText(String TextX){
        try
        {
            int index = Integer.parseInt(TextX.replaceAll("\\D+",""));
            return storedTexts.get(index);
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String getFileNameExt(String fileName)
    {
        try
        {
            String ext = fileName.substring(fileName.indexOf("."),fileName.length());
            return ext;
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String printMap(HashMap<String,String> map){
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String,String>> iter = map.entrySet().iterator();

        while(iter.hasNext())
        {
            Map.Entry<String,String> entry = iter.next();
            sb.append(entry.getKey());
            sb.append("=").append('"');
            sb.append(entry.getValue());
            sb.append('"');

            if(iter.hasNext()) {
				sb.append(',').append(' ');
			}
        }

        return(sb.toString());
    }

    public static String hashMapToXmlString(HashMap map){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        xmlEncoder.writeObject(map);
        xmlEncoder.flush();

        return bos.toString();
    }

    public static String hashMapToString(HashMap map){
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    XMLEncoder xmlEncoder = new XMLEncoder(bos);
    xmlEncoder.writeObject(map);
    xmlEncoder.flush();

    String serializedMap = bos.toString();

    return serializedMap;
	}

    public static boolean onlyDigits(String str){
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            } else {
				return false;
			}
        }
        return false;
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String generateRandomString(int length , String startWith){
      String datetimestring = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
      int random_lenght = length - startWith.length();
      int remove = datetimestring.length() - random_lenght;
      String random_string = (datetimestring.substring(1, remove));
      random_string = startWith + random_string;
      return random_string;
    }

    public static String generateRandomIntString(int min , int max){
      String randomIntString;
      Random rand = new Random();
      int randomInt = rand.nextInt((max - min) + 1) + min;
      randomIntString = Integer.toString(randomInt);
      return randomIntString;
    }

    public static String getDynamicDate(String dateString , String pattern){
        try
        {

            LocalDateTime newDate = LocalDateTime.now();
            dateString = dateString.replace("CurrentDate","");
            if(dateString.contains("+"))
            {
                int offset = Integer.parseInt(dateString.replace("+",""));
                newDate = newDate.plusDays(offset);
            }
            else if(dateString.contains("-"))
            {
                int offset = Integer.parseInt(dateString.replace("-",""));
                newDate = newDate.minusDays(offset);
            }

            return newDate.format(DateTimeFormatter.ofPattern(pattern));
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

}

