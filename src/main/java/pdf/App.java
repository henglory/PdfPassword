package pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class App {
  public static void main(String[] args) throws Exception {
    if(args.length < 3){
        throw new Exception("parameters are incorrect");
    }
    String input = args[0];
    String password = args[1];
    String output = args[2];
    try {
      PDDocument document = PDDocument.load(new File(input));
      AccessPermission ap = new AccessPermission();
        
      StandardProtectionPolicy standardPP = new StandardProtectionPolicy(password, password, ap);
      standardPP.setEncryptionKeyLength(128);
      document.protect(standardPP);
      document.save(output);
      document.close();
        
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}