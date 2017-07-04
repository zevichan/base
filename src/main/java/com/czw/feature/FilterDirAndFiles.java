package com.czw.feature;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZeviChen , 2017/7/4 17:15
 */
public class FilterDirAndFiles {

    final static Path imgPath = Paths.get("/opt/www/static/upload");
    //    final static Path imgPath = Paths.get("E:\\ttt\\file");
    static List<String> names = new ArrayList<>();

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/db_name";
        String username = "root";
        String password = "123456";
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = con.prepareStatement("SELECT loginName,merchantId FROM mc_merchant WHERE address like '%安徽%'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString(1) + "" + rs.getString(2);
            names.add(name);
        }

        File target = new File("merchantimg");
        if (!target.exists()) target.mkdir();
        final String i = imgPath.toAbsolutePath().toString();
        final String t = target.toPath().toAbsolutePath().toString();
        File[] fs = imgPath.toFile().listFiles();
        for (File f : fs) {
            if (f.isDirectory() && names.contains(f.getName())) {
                Files.walkFileTree(f.toPath(), new FileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        System.out.println("dir Path : " + dir.toAbsolutePath().toString());
                        Path path = Paths.get(dir.toAbsolutePath().toString().replace(i, t));
                        Files.createDirectories(path);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        String fp = file.toAbsolutePath().toString().replace(i, t);
                        Path p = Paths.get(fp);
                        if (!p.toFile().exists())
                            Files.copy(file, Paths.get(fp));
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.TERMINATE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        }

    }

      /* pom.xml cmd:package  注意jdk version问题

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>3.0.0</version>
        <configuration>
            <archive>
                <manifest>
                    <mainClass>com.lammal.filter.App</mainClass>
                </manifest>
            </archive>
            <descriptorRefs>
                <descriptorRef>jar-with-dependencies</descriptorRef>
            </descriptorRefs>
        </configuration>
        <executions>
            <execution>
                <id>make-assembly</id>
                <phase>package</phase>
                <goals>
                    <goal>single</goal>
                </goals>
            </execution>
        </executions>
    </plugin>*/


}
