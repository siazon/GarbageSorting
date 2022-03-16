package vision_API;


import com.google.api.Page;
import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;

import java.io.IOException;

public class main {


    public static void main(String[] args) throws IOException {

        DetectLabels obj1 = new DetectLabels();
        obj1.detectLabels();

    }



}
