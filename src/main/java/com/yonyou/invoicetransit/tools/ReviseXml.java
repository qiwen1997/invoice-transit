package com.yonyou.invoicetransit.tools;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;


public class ReviseXml {


  public static String changeEncoding(String strXML) {

    Document doc = null;
    try {
      doc = DocumentHelper.parseText(strXML);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    doc.setXMLEncoding("GBK");
    return doc.asXML();
  }

}
