package common.data;

import static common.data.UtilHelp.storedTexts;

import java.io.File;

public class FileHelp {

    public static String checkDownLoad(String expectedName)
    {
        try
        {
            File downloadFolder = new File(System.getProperty("downloadPath"));
            File dir = downloadFolder;
            dir.mkdirs();
            File[] listOfFiles = downloadFolder.listFiles();
            boolean found = false;
            for(File fileX : listOfFiles)
            {
                if(fileX.getName().equalsIgnoreCase(expectedName)) {
					found = true;
				}{
                    fileX.delete();}
            }

            if(found) {
				return "PASS";
			} else {
				return "FAIL";
			}
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }

    public static String deleteFile(String fileName, String path)
    {
        try
        {
            File folder = new File(path);
            File dir = folder;
            dir.mkdirs();
            File[] listOfFiles = folder.listFiles();
            boolean found = false;
            for(File fileX : listOfFiles)
            {
                for(String nameX : storedTexts) {
                    if (nameX.equalsIgnoreCase(fileName))
                    {
                        found = true;
                        fileX.delete();
                    }
                }
            }

            if(found) {
				return "PASS";
			} else {
				return "FAIL";
			}
        }
        catch(Exception ex)
        {System.out.println(ex.toString()); return  ex.toString();}
    }
}
