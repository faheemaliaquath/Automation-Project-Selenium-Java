package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import page.FileUploadPage;

public class FileUploadTest extends BaseTest {

    // Folder that holds the files to upload: src/test/resources/test-files/
    String folder = System.getProperty("user.dir") + "/src/test/resources/test-files/";

    @Test(priority = 1)
    public void testSingleFileUpload() {
        FileUploadPage uploadPage = new FileUploadPage(driver, wait);

        uploadPage.uploadSingleFile(folder + "sample-upload.txt");

        Assert.assertTrue(uploadPage.getSingleFileName().contains("sample-upload.txt"),
                "The chosen file name should be shown in the file input");
    }

    @Test(priority = 2)
    public void testMultipleFilesUpload() {
        FileUploadPage uploadPage = new FileUploadPage(driver, wait);

        // Two file paths separated by a new line
        uploadPage.uploadMultipleFiles(folder + "sample-upload.txt" + "\n" + folder + "sample-second.txt");

        String status = uploadPage.getMultipleFilesStatus();
        Assert.assertTrue(status.contains("sample-upload.txt"), "Status should list sample-upload.txt. Actual: " + status);
        Assert.assertTrue(status.contains("sample-second.txt"), "Status should list sample-second.txt. Actual: " + status);
    }
}