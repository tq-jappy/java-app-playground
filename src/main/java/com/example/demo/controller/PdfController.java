package com.example.demo.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.function.Consumer;

@Controller
@RequestMapping("pdf")
@RequiredArgsConstructor
public class PdfController {

    private final ResourceLoader resourceLoader;

    @GetMapping(value = "pdfbox", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String getPdfBox(HttpServletResponse response) throws IOException {
        downloadPdf(response, (out) -> {
            try (PDDocument doc = new PDDocument()) {
                PDPage page = new PDPage();
                doc.addPage(page);

                PDFont font = PDType1Font.COURIER_BOLD_OBLIQUE;

                try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(100, 70);
                    contents.showText("Hello");
                    contents.endText();
                }

                doc.save(out);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });

        return null;
    }

    @GetMapping(value = "flying-saucer", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String getFlyingSaucer(HttpServletResponse response) throws IOException {
        downloadPdf(response, (out) -> {
            try {
                File file = resourceLoader.getResource("classpath:templates/pdf/input.html").getFile();

                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocument(file);
                renderer.layout();
                renderer.createPDF(out);
            } catch (IOException | DocumentException e) {
                throw new RuntimeException(e);
            }
        });

        return null;
    }

    private void downloadPdf(HttpServletResponse response, Consumer<ByteArrayOutputStream> writer) throws IOException {
        byte[] content;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            writer.accept(out);

            content = out.toByteArray();
        }

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "sample.pdf");
        response.setContentLength(content.length);

        try (OutputStream out = response.getOutputStream()) {
            out.write(content);
            out.flush();
        }
    }
}
