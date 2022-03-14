package webapp8.webandtech.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import webapp8.webandtech.model.Product;

@Component("/checkout")
public class FacturaPdf extends AbstractPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
                List<Product> productos = (List<Product>) model.get("shopCar");
                float totalPrice = (float) model.get("priceCar");

                PdfPTable tablaProductos = new PdfPTable(3);
                productos.forEach(producto ->{
                    tablaProductos.addCell(producto.getDescription());
                    tablaProductos.addCell(producto.getNameproduct());
                    tablaProductos.addCell(producto.getProductType());
                });

                document.add(tablaProductos);
    }
    
}
