package com.ytrsoft.util.img.svg;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class SvgLoader {

    private SVGDocument document;

    public SvgLoader(String name) {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        try (InputStream stream = getSVGAsStream(name)) {
            document = factory.createSVGDocument(null, stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private InputStream getSVGAsStream(String name) {
        String path = String.format("/svg/%s.svg", name);
        return SvgLoader.class.getResourceAsStream(path);
    }

    public ImageIcon toIcon() {
        BufferedImage image = toImage();
        return image != null ? new ImageIcon(image) : null;
    }

    public BufferedImage toImage() {
        if (document == null) {
            return null;
        }
        UserAgent userAgent = new UserAgentAdapter();
        BridgeContext ctx = new BridgeContext(userAgent);
        GVTBuilder builder = new GVTBuilder();
        GraphicsNode rootNode = builder.build(ctx, document);
        Rectangle bounds = rootNode.getBounds().getBounds();
        BufferedImage image = new BufferedImage(
            bounds.width,
            bounds.height,
            BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        rootNode.paint(g2d);
        g2d.dispose();
        return image;
    }
}
