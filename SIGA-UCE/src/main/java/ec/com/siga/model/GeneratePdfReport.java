package ec.com.siga.model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.Entregable;
import ec.com.siga.entity.Informe;

public class GeneratePdfReport {
	public static byte[] auditoriesReport(Informe inf, List<CheckList> preguntas, Entregable en) {
		// variable data
		int numPregunta = 0;
		String respuestaPre;
		CheckList cl = new CheckList();

		// document instance
		Document document = new Document();

		// data table
		try {

			PdfPTable tData = new PdfPTable(5);
			tData.setWidthPercentage(90);
			tData.setWidths(new int[] { 1, 5, 1, 5, 3 });

			Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			Font replyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Nº", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Pregunta", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("R", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Evidencia", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Soporte", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			for (CheckList pregunta : preguntas) {
				cl = pregunta;
				numPregunta = numPregunta + 1;

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(numPregunta), replyFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tData.addCell(cell);

				cell = new PdfPCell(new Phrase(pregunta.getPreguntasId().getPreguntas(), replyFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				tData.addCell(cell);

				if (pregunta.getDatoEspecificoId().isRespuesta() == true) {
					respuestaPre = "SI";
				} else {
					respuestaPre = "NO";
				}

				cell = new PdfPCell(new Phrase(respuestaPre, replyFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				tData.addCell(cell);

				cell = new PdfPCell(new Phrase(pregunta.getDatoEspecificoId().getEvidencia(), replyFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				tData.addCell(cell);

				try {
					Image image = Image.getInstance(pregunta.getDatoEspecificoId().getFotoId().getFoto());
					tData.addCell(image);
				} catch (IOException e) {
					e.printStackTrace();
					cell = new PdfPCell(new Phrase("NA", replyFont));
					cell.setPaddingLeft(5);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tData.addCell(cell);

				}

			}
			// end data table

			// title table
			PdfPTable tTitle = new PdfPTable(1);
			tTitle.setWidthPercentage(90);

			Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 36, Font.BOLD, BaseColor.ORANGE);

			PdfPCell hcellTitle;
			hcellTitle = new PdfPCell(new Phrase("SIGA", fontTitle));
			hcellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
			tTitle.addCell(hcellTitle);
			// end title table

			// picHead table
			PdfPTable picHead = new PdfPTable(1);
			picHead.setWidthPercentage(90);


			Image image;
			try {
				image = Image.getInstance(en.getInforme());
				picHead.addCell(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// end picHead table

			// header table
			PdfPTable tHeader = new PdfPTable(2);
			tHeader.setWidthPercentage(90);

			Font fontHeaderL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.DARK_GRAY);
			Font fontHeaderR = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

			PdfPCell hcellHeader;

			hcellHeader = new PdfPCell(new Phrase("Razón Social  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(inf.getClienteId().getNombreEmpresa(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Dirección Oficina Principal  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(inf.getClienteId().getUserId().getDireccion(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Cuestionario  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(
					new Phrase(cl.getPreguntasId().getTipoAuditoriaId().getTipoAuditoria(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Teléfono  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(
					new Phrase(String.valueOf(inf.getClienteId().getUserId().getNumeroTelefono1()), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Correo Electrónico  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(inf.getClienteId().getUserId().getCorreoElectronico(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Nombre de Inspector  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(
					inf.getAuditorId().getUserId().getNombre() + " " + inf.getAuditorId().getUserId().getApellido(),
					fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Fecha de Visita  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(
					inf.getDatoComunId().getSolicitudAuditoriaId().getFechaInicio().toString(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Emitido el  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			Date date = new Date();
			hcellHeader = new PdfPCell(new Phrase(date.toString(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);
			// end header table

			// document into stream
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, stream);

			// get document
			document.open();
			document.add(picHead);
			document.add(new Phrase("-"));
			document.add(tTitle);
			document.add(new Phrase("	INFORMACIÓN GENERAL"));
			document.add(tHeader);
			document.add(new Phrase("	ACTIVIDAD"));
			document.add(tData);
			document.close();

			// return document in bytes matrix
			return stream.toByteArray();

		} catch (DocumentException ex) {

		}
		return null;
	}
}
