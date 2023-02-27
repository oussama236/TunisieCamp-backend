package com.gladiators.pi_spring.export;

import com.gladiators.pi_spring.Entities.Activity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.var;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCode {
    public static void generateQRCode(Activity activity) throws WriterException, IOException {
        String qrCodePath = "D:\\blog-posts\\QRCode\\";
        String qrCodeName = qrCodePath+activity.getName ()+activity.getId()+"-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter ();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "ID: "+activity.getId()+ "\n"+
                        "Name: "+activity.getName ()+ "\n"+
                        "Description: "+activity.getDescription ()+ "\n"+
                        "Capacity: "+activity.getCapacity ()+ "\n" +
                        "StartTime: "+activity.getStartTime ()+ "\n" +
                        "EndTime: "+activity.getEndTime ()+ "\n" +
                        "Disponibility: "+activity.getDisponibility (), BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
}
