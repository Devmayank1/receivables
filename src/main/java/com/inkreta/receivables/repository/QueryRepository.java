package com.inkreta.receivables.repository;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class QueryRepository {
    private static final Logger LOG = LoggerFactory.getLogger(QueryRepository.class);

    private Map<String, String> repository = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = QueryRepository.class.getClassLoader().getResourceAsStream("sql-queries.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("query");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String key = element.getElementsByTagName("key").item(0).getTextContent();
                    String value = element.getElementsByTagName("value").item(0).getTextContent();

                    if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                        repository.put(key, value);
                    }
                }

            }
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        } finally {
            if(Objects.nonNull(inputStream)){
                inputStream.close();
            }

        }

    }

    public String findQuery(String key) {
        return repository.get(key);
    }
}
