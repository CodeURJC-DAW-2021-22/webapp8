package webapp8.webandtech.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.User;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import com.lowagie.text.pdf.*;

@Service
public class PDFGeneratorService {

    @Autowired
	private OrderService orderService;
    
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(3);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Id de Producto", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Producto", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Precio", font));
        table.addCell(cell);
       
    }

    private void writeTableData(PdfPTable table, List<Product> carts) {
        for (Product cart : carts) {
            table.addCell(String.valueOf(cart.getIdproduct()));
            table.addCell(cart.getNameproduct());
            table.addCell(Float.toString(cart.getPrice())+"€");
        }
    }

    public void export(HttpServletResponse response, Order order, List<Product> carts, User user) throws IOException {
        List<Order> ord = orderService.getAllOrders();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Factura", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
        
        // Image logo = Image.getInstance("backend/webandtech/src/main/resources/static/images/logo.jpg");
        // document.add(logo);

        Paragraph idOrder = new Paragraph("Factura: "+ (ord.get(0).getIdorder()+1));
        idOrder.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(idOrder);
        Paragraph client = new Paragraph("Cliente: "+ user.getCompletname());
        client.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(client);
        Paragraph email = new Paragraph("E-mail: "+ user.getEmail());
        email.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(email);
        Paragraph address = new Paragraph("Dirección: "+ user.getAddress());
        address.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(address);
         
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.5f, 4.0f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table, carts);
         
        document.add(table);

        Paragraph price = new Paragraph("Precio Total: "+ order.getPrice()+"€", font);
        price.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(price);
         
        document.close();
    }
    
}
