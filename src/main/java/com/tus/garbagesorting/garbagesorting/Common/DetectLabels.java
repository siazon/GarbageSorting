package com.tus.garbagesorting.garbagesorting.Common;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DetectLabels {

    private static String label = "";
    private static String category = "";

    public static void detectLabels() throws IOException {
        // TODO(developer): Replace these variables before running the sample.
        String filePath = "src/main/java/vision_API/banana.jpg";
        detectLabels(filePath);
    }

    // Detects labels in the specified local image.
    public static String detectLabels(String filePath) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    break;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                int limit = 0;
                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                    label = annotation.getDescription();

                    if (label.toLowerCase().contains("glass")
                            || label.toLowerCase().contains("plastic")
                            || label.toLowerCase().contains("tin")
                            || label.toLowerCase().contains("metal")
                            || label.toLowerCase().contains("can")
                            || label.toLowerCase().contains("paper")
                            || label.toLowerCase().contains("foil")
                            || label.toLowerCase().contains("cardboard")
                            || label.toLowerCase().contains("newspaper")) {

                        category = "recycle";
                    } else if (label.toLowerCase().contains("plant")
                            || label.toLowerCase().contains("grass")
                            || label.toLowerCase().contains("food")
                            || label.toLowerCase().contains("fruit")
                            || label.toLowerCase().contains("vegetable")
                            || label.toLowerCase().contains("livestock")
                            || label.toLowerCase().contains("coffee")
                            || label.toLowerCase().contains("wood")
                            || label.toLowerCase().contains("leaves")
                            || label.toLowerCase().contains("leave")) {
                        category = "organic";
                    } else {
                        category = "trash";
                    }
                }
                //Organic: Plant, Food, Fish, Fruit, Vegetable,
            }
        }
        return category;
    }

}