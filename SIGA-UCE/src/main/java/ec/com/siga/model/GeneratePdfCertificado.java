package ec.com.siga.model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import ec.com.siga.entity.Informe;

public class GeneratePdfCertificado {
	public static byte[] auditoriesCertificate(Informe inf) {
		// variable data
		String nivel;
		
		// document instance
		Document document = new Document();

		// data table
		try {

			PdfPTable tData = new PdfPTable(3);
			tData.setWidthPercentage(70);
			tData.setWidths(new int[] { 3, 1, 1 });

			Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			Font replyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("CUESTIONARIO", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PUNTAJE 1", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PUNTAJE 2", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			PdfPCell cell;

			cell = new PdfPCell(new Phrase(
					inf.getDatoComunId().getSolicitudAuditoriaId().getTipoAuditoriaId().getTipoAuditoria(), replyFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(inf.getDatoComunId().getCalificacion()), replyFont));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(inf.getDatoComunId().getCalificacionFinal()), replyFont));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(cell);
			// end data table

			// data2 table
			PdfPTable tDataCal = new PdfPTable(4);
			tDataCal.setWidthPercentage(70);

			Font fontCalT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			Font fontCal = new Font(Font.FontFamily.TIMES_ROMAN, 14);

			PdfPCell hcellCal;

			hcellCal = new PdfPCell(new Phrase("Puntaje Final:", fontCalT));
			hcellCal.setHorizontalAlignment(Element.ALIGN_CENTER);
			tDataCal.addCell(hcellCal);

			hcellCal = new PdfPCell(new Phrase(String.valueOf(inf.getDatoComunId().getCalificacionFinal()), fontCal));
			hcellCal.setHorizontalAlignment(Element.ALIGN_CENTER);
			tDataCal.addCell(hcellCal);

			hcellCal = new PdfPCell(new Phrase("Certifica:", fontCalT));
			hcellCal.setHorizontalAlignment(Element.ALIGN_CENTER);
			tDataCal.addCell(hcellCal);

			if (inf.getDatoComunId().getCalificacionFinal() >= 75) {
				nivel = "SI";
			}else {
				nivel = "NO";
			}
			
			hcellCal = new PdfPCell(new Phrase(nivel, fontCal));
			hcellCal.setHorizontalAlignment(Element.ALIGN_CENTER);
			tDataCal.addCell(hcellCal);
			// end data2 table

			// title table
			PdfPTable tTitle = new PdfPTable(1);
			tTitle.setWidthPercentage(90);

			Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 36, Font.BOLD, BaseColor.ORANGE);

			PdfPCell hcellTitle;

			hcellTitle = new PdfPCell(new Phrase("SIGA", fontTitle));
			hcellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
			tTitle.addCell(hcellTitle);
			// end title table

			// header table
			PdfPTable tHeader = new PdfPTable(1);
			tHeader.setWidthPercentage(90);

			Font fontSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			Font fontSubSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 16);

			PdfPCell hcellHeader;

			hcellHeader = new PdfPCell(new Phrase("Constancia de Auditoria", fontSubTitle));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(
					new Phrase("Nº " + inf.getInformeId() + inf.getDatoComunId().getDatoComunId(), fontSubSubTitle));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(
					new Phrase("SIGA, ha llevado a cabo el proceso de auditoria al proveedor:", fontSubSubTitle));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(inf.getClienteId().getNombreEmpresa(), fontSubTitle));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tHeader.addCell(hcellHeader);
			// end header table

			// document into stream
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, stream);

			// get document
			document.open();
			document.add(tTitle);
			document.add(new Phrase("-"));
			document.add(tHeader);
			document.add(new Phrase("-"));
			document.add(tData);
			document.add(new Phrase("-"));
			document.add(tDataCal);
			document.add(new Phrase("-"));
			Date date = new Date();
			document.add(new Phrase("Periodo de Validez por un año a partir de: "+date.toString()));
			document.close();

			// return document in bytes matrix
			return stream.toByteArray();

		} catch (DocumentException ex) {

		}
		return null;
	}
}
