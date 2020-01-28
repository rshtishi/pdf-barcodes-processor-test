package al.tirana;

import al.tirana.pdfBarcodesProcessor.PdfBarcodesProcessor;
import al.tirana.pdfBarcodesProcessor.PdfBarcodesProcessorImpl;
import al.tirana.pdfBarcodesProcessor.imageProcessor.ImageProcessor;
import al.tirana.pdfBarcodesProcessor.imageProcessor.OpenCVImageProcessor;
import al.tirana.pdfBarcodesProcessor.pdfprocessor.PdfDocument;

public class App {

	public static void main(String[] args) throws Exception {

		ImageProcessor imageProcessor = new OpenCVImageProcessor();
		imageProcessor.setBarcodeRatioToImage(0.4, 0.6);
		imageProcessor.setClassifierPath("src/main/resources/classifier-4/cascade.xml");

		PdfBarcodesProcessor pdfBarcodesProcessor = new PdfBarcodesProcessorImpl.Builder()
				.imageProcessor(imageProcessor).build();

		String filePath = "src/main/resources/test.pdf";

		PdfDocument resultDoc = pdfBarcodesProcessor.processPdfBarcodesPerPage(filePath);
		resultDoc.getPdfPageList().get(0).getDecodedBarcodeImageMap().keySet().forEach(System.out::println);
		resultDoc.getPdfPageList().get(1).getDecodedBarcodeImageMap().keySet().forEach(System.out::println);
		resultDoc.saveAllDecodedBarcodeImages("src/main/resources/decodedBarcodeImages");
	}
}
