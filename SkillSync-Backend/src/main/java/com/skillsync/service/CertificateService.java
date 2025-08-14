package com.skillsync.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.skillsync.model.Skill;
import com.skillsync.model.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

@Service
public class CertificateService {

    public ResponseEntity<Resource> generateCertificate(User user, Skill skill) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(); //used to stream the PDF content
            Document document = new Document(PageSize.A4, 60, 60, 60, 60);
            PdfWriter.getInstance(document, out);

            document.open();

            // Create a professional border
            Rectangle rect = new Rectangle(36, 36, 559, 806);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(3);
            rect.setBorderColor(BaseColor.GRAY);
            document.add(rect);

            // Organization title
            Paragraph orgTitle = new Paragraph("SkillSync Certifications",
                    new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.DARK_GRAY));
            orgTitle.setAlignment(Element.ALIGN_CENTER);
            orgTitle.setSpacingAfter(20);
            document.add(orgTitle);

            // Main heading
            Paragraph mainHeading = new Paragraph("Certificate of Achievement",
                    new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.BOLD));
            mainHeading.setAlignment(Element.ALIGN_CENTER);
            mainHeading.setSpacingAfter(10);
            document.add(mainHeading);

            // Subheading
            Paragraph subHeading = new Paragraph("Awarded by SkillSync",
                    new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC, BaseColor.DARK_GRAY));
            subHeading.setAlignment(Element.ALIGN_CENTER);
            subHeading.setSpacingAfter(40);
            document.add(subHeading);

            // Certificate text lines
            Paragraph text1 = new Paragraph("This is to certify that",
                    new Font(Font.FontFamily.HELVETICA, 14));
            text1.setAlignment(Element.ALIGN_CENTER);
            document.add(text1);

            Paragraph studentName = new Paragraph(user.getName(),
                    new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, BaseColor.BLUE));
            studentName.setAlignment(Element.ALIGN_CENTER);
            studentName.setSpacingAfter(10);
            document.add(studentName);

            Paragraph text2 = new Paragraph("has successfully completed the skill",
                    new Font(Font.FontFamily.HELVETICA, 14));
            text2.setAlignment(Element.ALIGN_CENTER);
            document.add(text2);

            Paragraph skillName = new Paragraph(skill.getName(),
                    new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD | Font.ITALIC));
            skillName.setAlignment(Element.ALIGN_CENTER);
            skillName.setSpacingAfter(30);
            document.add(skillName);

            // Date of issue
            Paragraph dateLine = new Paragraph("Date: " + LocalDate.now(),
                    new Font(Font.FontFamily.HELVETICA, 12));
            dateLine.setAlignment(Element.ALIGN_RIGHT);
            dateLine.setSpacingAfter(50);
            document.add(dateLine);

            // Footer
            Paragraph footer = new Paragraph("~ Issued by SkillSync Training Board ~",
                    new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GRAY));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            // Signature line
            Paragraph signature = new Paragraph("_________________________\nAuthorized Signature",
                    new Font(Font.FontFamily.HELVETICA, 12));
            signature.setAlignment(Element.ALIGN_LEFT);
            signature.setSpacingBefore(35);
            document.add(signature);

            document.close();

            // Sanitize file name by replacing spaces
            String fileName = "SkillSync_Certificate_" + skill.getName().replaceAll("\\s+", "") + "_"
                    + user.getName().replaceAll("\\s+", "") + ".pdf";

            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
