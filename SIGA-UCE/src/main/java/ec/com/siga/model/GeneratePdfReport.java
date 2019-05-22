package ec.com.siga.model;

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
import java.util.List;

import ec.com.siga.entity.CheckList;

public class GeneratePdfReport {
	public static byte[] auditoriesReport(List<CheckList> preguntas) {

		int numPregunta = 0;
		String respuestaPre;
		String foto;

		Document document = new Document();

		try {

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(60);
			table.setWidths(new int[] { 1, 3, 1, 3, 3 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Numero_prefunta", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Pregunta", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Respuesta", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Evidencia", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Foto", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (CheckList pregunta : preguntas) {

				numPregunta = numPregunta + 1;

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(numPregunta)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(pregunta.getPreguntasId().getPreguntas()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				if (pregunta.getDatoEspecificoId().isRespuesta() == true) {
					respuestaPre = "SI";
				} else {
					respuestaPre = "NO";
				}

				cell = new PdfPCell(new Phrase(respuestaPre));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(pregunta.getDatoEspecificoId().getEvidencia()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				if (pregunta.getDatoEspecificoId().getFotoId() == null) {
					foto = "NO";
				} else {
					foto = "SI";
				}

				cell = new PdfPCell(new Phrase(foto));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

			}

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, stream);
						
			document.open();
			document.add(table);
			document.close();
			
			return stream.toByteArray();

		} catch (DocumentException ex) {

		}
		return null;
	}
}
