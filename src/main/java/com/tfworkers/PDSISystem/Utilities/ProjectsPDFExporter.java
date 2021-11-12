package com.tfworkers.PDSISystem.Utilities;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfStream;
import com.tfworkers.PDSISystem.Model.Entity.Project;


import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * The type Projects pdf exporter.
 */
public class ProjectsPDFExporter {
    final private List<Project> projectList;

    /**
     * Instantiates a new Projects pdf exporter.
     *
     * @param projectList the project list
     */
    public ProjectsPDFExporter(List<Project> projectList) {
        this.projectList = projectList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Project ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Project Types", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Timelines", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Project project : projectList) {
            table.addCell(String.valueOf(project.getProject_id()));
            table.addCell(project.getName());
            table.addCell(project.getDescription());
            table.addCell(project.getTags().toString());
            table.addCell(project.getBudgets().toString());
        }
    }

    /**
     * Export.
     *
     * @param response the response
     * @throws DocumentException the document exception
     * @throws IOException       the io exception
     */
    public void export(HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Projects", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
