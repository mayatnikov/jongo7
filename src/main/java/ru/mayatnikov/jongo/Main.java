package ru.mayatnikov.jongo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 30.11.11
 * Time: 17:20
 */
public class Main {
    public static void main(String[] args)
    {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/application-context-root.xml");

        iGeoJongoBean jb1 = ctx.getBean("geoATM",iGeoJongoBean.class);
        jb1.findNear(55.701, 37.709, 0.001);    // 55.701, 37.709
        System.out.println(jb1);

        iGeoJongoBean jb2 = ctx.getBean("geoPOS",iGeoJongoBean.class);
        jb2.findNear(55.703, 37.710, 0.001);
        System.out.println(jb2);
    }
}
