package com.example.tema04ejercicio01.modelos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DomParser {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private NodeList nodeList;
    private Document document;

    public DomParser() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }

    public void loadFile(Context context, int resourceID) throws IOException, SAXException {
        InputStream inputStream = context.getResources().openRawResource(resourceID);
        document = builder.parse(inputStream);
        document.getDocumentElement().normalize();
    }

    public List<Country> getCountries(String tagName, Context context) {
        List<Country> countries = new ArrayList<>();
        nodeList = document.getElementsByTagName(tagName);

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String countryCode = element.getAttribute("countryCode");
                    String countryName = element.getAttribute("countryName");
                    long population = Long.parseLong(element.getAttribute("population"));
                    String capital = element.getAttribute("capital");
                    String isoAlpha3 = element.getAttribute("isoAlpha3");
                    int flagResource = getFlagResourceByIsoAlpha3(countryCode, context);

                    Country country = new Country(countryCode, countryName, population, capital, isoAlpha3, flagResource);
                    countries.add(country);
                }
            }
        }
        return countries;
    }

    @SuppressLint("DiscouragedApi")
    private int getFlagResourceByIsoAlpha3(String countryCode, Context context) {
        String packageName = context.getPackageName();
        String resourceName = "_" + countryCode.toLowerCase();
        Log.d("CountryAdapter", "Generated resource name: " + resourceName);
        return context.getResources().getIdentifier(resourceName, "drawable", packageName);
    }

    public NodeList getNodesByTag(String tagName) {
        if (document != null) {
            nodeList = document.getElementsByTagName(tagName);
            return nodeList;
        }
        return null;
    }

    public List<String> getElementContent(String tagName) {
        List<String> contents = new ArrayList<>();
        nodeList = getNodesByTag(tagName);

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodeList.item(i);
                    contents.add(element.getTextContent().trim());
                }
            }
        }
        return contents;
    }
}
