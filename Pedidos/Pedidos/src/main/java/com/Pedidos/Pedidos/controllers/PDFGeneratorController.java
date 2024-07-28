package com.Pedidos.Pedidos.controllers;

import com.Pedidos.Pedidos.model.OrderProducts;
import com.Pedidos.Pedidos.model.Products;
import com.Pedidos.Pedidos.repositories.OrderRepository;
import com.Pedidos.Pedidos.repositories.ProductsRepository;
import com.Pedidos.Pedidos.repositories.UserRepository;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import com.itextpdf.layout.properties.Background;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin/pdforder")
@RequiredArgsConstructor
public class PDFGeneratorController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired

    private UserRepository userRepository;


    @GetMapping("/generate/{id}")
    public ResponseEntity<String> generatePDF(@PathVariable Long id) {
        // Aqui você deve buscar a ordem do banco de dados pelo ID.
        // Esta é apenas uma simulação com dados estáticos.

        // Simulação de dados
        Optional<OrderProducts> order = orderRepository.findById(id);

        if (order == null) {
            return ResponseEntity.badRequest().build();
        }
        OrderProducts newOrder = order.get();


        String dest = "order" + newOrder.getId() + ".pdf";
        try {
            createPdf(dest, newOrder);
            return ResponseEntity.status(HttpStatus.OK).body("PDF GENERATED: "+dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



//    private void createPdf(String dest, OrderProducts order) throws FileNotFoundException {
//        PdfWriter writer = new PdfWriter(dest);
//        PdfDocument pdfDoc = new PdfDocument(writer);
//        Document document = new Document(pdfDoc);
//
//        // Add title
//        document.add(new Paragraph("Order Details")
//                .setFontSize(20)
//                .setBold());
//
//        // Add order information
//        document.add(new Paragraph("Order ID: " + order.getId()));
//        long idUser = order.getIdUser();
//        String username = userRepository.findUsernameById(idUser);
//
//        document.add(new Paragraph("Company: " + username));
//        document.add(new Paragraph("Order Date: " + order.getDateOrder()));
//        document.add(new Paragraph("Delivery Date: " + order.getDateDelivery()));
//
//        // Add a blank line
//        document.add(new Paragraph("\n"));
//
//        // Create a table for product details
//        Table table = new Table(new float[]{2, 2, 2, 2, 2, 2});
//        table.addHeaderCell("ID PRODUCT");
//        table.addHeaderCell("Quantity");
//        table.addHeaderCell("Type");
//        table.addHeaderCell("Size");
//        table.addHeaderCell("Weight");
//        table.addHeaderCell("Fill");
//
//
//
//        List<Products> products = new ArrayList<>();
//
//        for (int i = 0; i < order.getIdProducts().size(); i++){
//            System.out.println(order.getIdProducts().get(i));
//            Optional<Products> newProduct = productsRepository.findById(order.getIdProducts().get(i));
//            Products newProduct2 = newProduct.get();
//            products.add(newProduct2);
//        }
//        System.out.println(products);
//
//
//        // Adicionar dados dos produtos à tabela
//        for (int i = 0; i < products.size(); i++) {
//            Products product = products.get(i);
//            int quantity = order.getAmount().get(i);
//            table.addCell(String.valueOf(product.getId()));
//            table.addCell(String.valueOf(quantity));
//            table.addCell(product.getTypeProduct());
//            table.addCell(product.getSizeProduct());
//            table.addCell(product.getWeightProduct() + "g");
//            table.addCell(product.getFill());
//        }
//
//        document.add(table);
//
//        document.close();
//    }


    private void createPdf(String dest, OrderProducts order) throws FileNotFoundException, IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Font and Colors
        PdfFont font;
        try {
            font = PdfFontFactory.createFont("Helvetica");
        } catch (IOException e) {
            // Tratar a exceção se a fonte não puder ser criada
            System.err.println("Erro ao criar a fonte: " + e.getMessage());
            return;
        }


        Color headerColor = new DeviceRgb(63, 81, 181);
        Color cellColor = new DeviceRgb(240, 240, 240);

        document.add(new Paragraph("N.: " + order.getId())
                .setFont(font)
                .setBackgroundColor(ColorConstants.DARK_GRAY)
                .setWidth(30)
                .setFontColor(ColorConstants.WHITE)
                .setBold()
                .setFontSize(8));

        // Add title
        document.add(new Paragraph("Order Details")
                .setFont(font)
                .setBackgroundColor(ColorConstants.DARK_GRAY)
                .setFontSize(20)
                .setBold()
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER));

        // Add order information

        long idUser = order.getIdUser();
        String username = userRepository.findUsernameById(idUser);
        document.add(new Paragraph("Company: " + username)
                .setFont(font)
                .setBold()
                .setFontSize(12));

        document.add(new Paragraph("Order Date: " + order.getDateOrder())
                .setFont(font)
                .setBold()
                .setFontSize(12));
        document.add(new Paragraph("Delivery Date: " + order.getDateDelivery())
                .setFont(font)
                .setBold()
                .setFontSize(12));

        // Add a blank line
        document.add(new Paragraph("\n"));

        // Create a table for product details
        Table table = new Table(new float[]{2, 2, 2, 2, 2, 2, 2, 2});
        table.setWidth(100);
        table.setWidth(UnitValue.createPercentValue(100));

        // Add table header
        addTableHeader(table, headerColor, font,pdfDoc);

        // Fetch products
        List<Products> products = fetchProducts(order);

        // Add product details to the table
        addProductDetails(table, products, order, cellColor, font);

        calculationTotal(order, products);

        // Add table to document
        document.add(table);

        document.close();
    }

    private void addTableHeader(Table table, Color headerColor, PdfFont font, PdfDocument pdfDocument) {
        String[] headers = {"ID PRODUCT", "Quantity", "Type", "Size", "Weight", "Fill","Preço Unit.", "Total:"};
        for (String header : headers) {
            Cell headerCell = new Cell()
                    .add(new Paragraph(header)
                            .setFont(font)
                            .setFontSize(12)
                            .setFontColor(ColorConstants.WHITE))
                    .setBackgroundColor(headerColor)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addHeaderCell(headerCell);
        }
    }

    private List<Products> fetchProducts(OrderProducts order) {
        List<Products> products = new ArrayList<>();
        for (Long idProduct : order.getIdProducts()) {
            Optional<Products> newProduct = productsRepository.findById(idProduct);
            newProduct.ifPresent(products::add);
        }
        return products;
    }

    private void addProductDetails(Table table, List<Products> products, OrderProducts order, Color cellColor, PdfFont font) {

        for (int i = 0; i < products.size(); i++) {

            Products product = products.get(i);
            int quantity = order.getAmount().get(i);

            addTableCell(table, String.valueOf(product.getId()), cellColor, font);
            addTableCell(table, String.valueOf(quantity), cellColor, font);
            addTableCell(table, product.getTypeProduct(), cellColor, font);
            addTableCell(table, product.getSizeProduct(), cellColor, font);
            addTableCell(table, product.getWeightProduct() + "g", cellColor, font);
            addTableCell(table, product.getFill(), cellColor, font);
            addTableCell(table, "R$25,00", cellColor, font);
            addTableCell(table, "  ", cellColor, font);
            

            // CRIAR SISTEMA DE CALCULAR TOTAL E VALOR DO PEDIDO,  POSSIVELMENTE AQUI NESSE FOR
        }
        for (int i = 0; i < 7; i ++){
            addTableCell(table, "", cellColor, font);
        }
        addTableCell(table, calculationTotal(order, products).toString(), cellColor, font);


    }

    private void addTableCell(Table table, String content, Color cellColor, PdfFont font) {
        Cell cell = new Cell()
                .add(new Paragraph(content)
                        .setFont(font)
                        .setFontSize(12))
                .setBackgroundColor(cellColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
    }


    private String calculationTotal(OrderProducts order, List<Products> products){
        double totalWeight = 0;
        double valorPay = 0;

        List<Integer> weights = new ArrayList<>();

        for(int i =0; i < order.getAmount().size(); i++){
            System.out.println(order.getAmount().get(i));
            System.out.println(order.getIdProducts().get(i));
            weights.add(userRepository.findWeightProductById(order.getIdProducts().get(i).longValue()));
            totalWeight += order.getAmount().get(i) * weights.get(i);
        }

        System.out.println(weights);
        System.out.println(totalWeight);
        double forKilo = totalWeight/1000;
        System.out.println(forKilo);
        valorPay += forKilo*25;


        System.out.println("VALOR TOTAL DO PEDIDO: R$"+valorPay);
        return "R$"+valorPay;


    }
}

