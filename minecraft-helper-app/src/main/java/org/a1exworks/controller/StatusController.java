package org.a1exworks.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.a1exworks.status.MCQuery;
import org.a1exworks.status.QueryResponse;

public class StatusController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MCQuery           query;

    public StatusController(String address, int port) {
        super();
        query = new MCQuery(address, port);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QueryResponse response = null;
        try {
            response = query.basicStat();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedImage image = new BufferedImage(240, 30, BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = image.createGraphics();
        graph.setFont(new Font("BLACK", Font.PLAIN, 24));
        FontRenderContext context = graph.getFontRenderContext();
        graph.setColor(Color.gray);
        graph.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
        String text;
        if (null == response) {
            text = "OFFLINE";
            Rectangle2D rect = graph.getFont().getStringBounds(text, context);
            graph.setColor(Color.red);
            int x = -(int) rect.getX() + (image.getWidth() - (int) rect.getWidth()) / 2;
            int y = -(int) rect.getY() + (image.getHeight() - (int) rect.getHeight()) / 2;
            graph.drawString(text, x, y);
        } else {
            text = String.format("ONLINE %2d/%2d", response.getOnlinePlayers(), response.getMaxPlayers());
            Rectangle2D rect = graph.getFont().getStringBounds(text, context);
            graph.setColor(Color.green);
            int x = -(int) rect.getX() + (image.getWidth() - (int) rect.getWidth()) / 2;
            int y = -(int) rect.getY() + (image.getHeight() - (int) rect.getHeight()) / 2;
            graph.drawString(text, x, y);
        }
        ImageIO.write(image, "JPEG", resp.getOutputStream());
    }
}
