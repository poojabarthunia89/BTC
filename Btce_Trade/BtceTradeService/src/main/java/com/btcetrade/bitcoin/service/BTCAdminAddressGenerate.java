package com.btcetrade.bitcoin.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import com.btcetrade.bitcoin.model.BTCResponse;
import com.btcetrade.bitcoin.service.address.UserAddress;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BTCAdminAddressGenerate {

	public static BTCResponse generateAdminAddress(String adminFileName,String pathName) throws Exception {
		
		String qrCodeText = "";
		
		if(adminFileName.equalsIgnoreCase("9166001205")){
			qrCodeText="@344$admin$werdf";
		}else{
			UserAddress userAddressGenrate = new UserAddress(16, ThreadLocalRandom.current());
			qrCodeText=userAddressGenrate.nextString();
		}
			
		
		
		
		
		BTCResponse response = new BTCResponse();
		System.out.println("PATH"+pathName);
		String filePath = pathName;
		
		File dirPath= new File(filePath);
		System.out.println("Directory"+dirPath.exists());
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
		
		System.out.println("Directory"+dirPath.getAbsolutePath());
		String qrCodeFile=dirPath+"\\"+adminFileName+".png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(qrCodeFile);
		createQRImage(qrFile, qrCodeText, size, fileType);
		response.setResultForResponse("DONE");
		response.setUserAddress(qrCodeText);
		return response;
		

	}
	private static void createQRImage(File qrFile, String qrCodeText, int size,String fileType) throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable hintMap = new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
				BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
				BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}

	
}
