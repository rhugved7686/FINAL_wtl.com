package com.workshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.workshop.Service.SitemapService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sitemap")
public class SitemapController {

    @Autowired
    private SitemapService sitemapService;

    @GetMapping("")
    public void generateSitemap(HttpServletResponse response) throws IOException {
        // Set content type to XML
        response.setContentType("application/xml");

        // Initialize a StringBuilder to build the XML structure
        StringBuilder sitemap = new StringBuilder();

        // Write the XML header
        sitemap.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sitemap.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

        // Get all URLs dynamically from the service
        List<String> urls = sitemapService.getAllUrls();

        for (String url : urls) {
            sitemap.append("<url>");
            sitemap.append("<loc>").append(url).append("</loc>");
            sitemap.append("<lastmod>").append("2024-11-25").append("</lastmod>"); // Last modified date
            sitemap.append("<changefreq>").append("daily").append("</changefreq>"); // Change frequency
            sitemap.append("<priority>").append("0.8").append("</priority>"); // Priority
            sitemap.append("</url>");
        }

        // Close the URL set and the XML document
        sitemap.append("</urlset>");

        // Write the XML content to the HTTP response
        response.getWriter().write(sitemap.toString());
    }
    
    
}

