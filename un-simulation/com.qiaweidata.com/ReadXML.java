package com.fly.sample.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.fly.sample.bean.Dependency;

public class ReadXML
{
    
    public static List<Dependency> parse(String xmlDoc)
    {
        // 创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        // 创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        List<Dependency> dependencies = new ArrayList<>();
        try
        {
            // 通过输入源构造一个Document
            Document doc = sb.build(source);
            // 取的根元素
            Element root = doc.getRootElement();
            // 得到根元素所有子元素的集合
            List<?> node = root.getChildren();
            // 获得XML中的命名空间（XML中未定义可不写）
            Namespace ns = root.getNamespace();
            Element et = null;
            for (int i = 0; i < node.size(); i++)
            {
                et = (Element)node.get(i);// 循环依次得到子元素
                Dependency dependency = new Dependency();
                String groupId = et.getChild("groupId", ns).getText();
                String artifactId = et.getChild("artifactId", ns).getText();
                String version = et.getChild("version", ns).getText();
                String scope = et.getChild("scope", ns).getText();
                String systemPath = et.getChild("systemPath", ns).getText();
                dependency.setGroupId(groupId);
                dependency.setArtifactId(artifactId);
                dependency.setVersion(version);
                dependency.setScope(scope);
                dependency.setSystemPath(systemPath);
                dependencies.add(dependency);
            }
            
        }
        catch (JDOMException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return dependencies;
    }
}
