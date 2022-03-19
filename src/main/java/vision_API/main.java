package vision_API;


import com.google.api.Page;
import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class main {


    public static void main(String[] args) throws IOException, SQLException {

        DetectLabels obj1 = new DetectLabels();
        obj1.detectLabels();


        /***
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/vision_API";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "Pm@4GmLffym46h?");
        System.out.println("Connection established......");
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO image VALUES(?,?)");
        pstmt.setString(1, "sample2");
        //Inserting Blob type
        InputStream in = new FileInputStream("src/main/java/vision_API/dragon.jpg");
        pstmt.setBlob(2, in);
        //Executing the statement
        pstmt.execute();
        System.out.println("Record inserted......");
         */
    }



}
