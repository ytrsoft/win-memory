package com.ytrsoft;

import com.kitfox.svg.app.beans.SVGIcon;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public final class SVGLoader {

    private SVGLoader() {
        throw new UnsupportedOperationException();
    }

    public interface Images {
        Image JAVA = toImage("java");
    }

    public static Image toImage(String name) {
        SVGIcon svg = new SVGIcon();
        svg.setSvgURI(getResourcesURI(name));
        return svg.getImage();
    }

    private static URI getResourcesURI(String name) {
        name = String.format("/svg/%s.svg", name) ;
        try {
            return SVGLoader.class.getResource(name).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
