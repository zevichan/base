package com.czw.search.lucene;


import com.czw.util.DateUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * https://lucene.apache.org/core/6_5_1/core/overview-summary.html#overview.description
 *
 * <p>
 * FSDirectory：在文件系统上存储索引文件，有六个子类，如下是三个常用的子类
 * SimpleFSDirectory：使用Files.newByteChannel实现，对并发支持不好，它会在多线程读取同一份文件时进行同步操作
 * NIOFSDirectory：使用Java NIO中的FileChannel去读取同一份文件，可以避免同步操作，但是由于Windows平台上存在Sun JRE bug，所以在Windows平台上不推荐使用
 * MMapDirectory：在读取的时候使用内存映射IO，如果你的虚拟内存足够容纳索引文件大小的话，这是一个很棒的选择
 * RAMDirectory：在内存中暂存索引文件，只对小索引好，大索引会出现频繁GC
 *
 * @author ZeviChen , 2017/5/22 10:22
 */
public class TestIndexFiles {


    public static String indexPath = "E:\\test\\files-index";
    public static String docsPath = "D:\\gopj\\src\\github.com\\zevichen\\dandelion";

    @Test
    public void testCreateIndex() throws IOException, URISyntaxException {

        Directory dir = FSDirectory.open(Paths.get(indexPath));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
//        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

//         iwc.setRAMBufferSizeMB(256.0);

        IndexWriter writer = new IndexWriter(dir, iwc);
        final Path docDir = Paths.get(docsPath);
        indexDocs(writer,docDir);
        writer.close();

    }

    static void indexDocs(final IndexWriter writer, Path path) throws IOException {
        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
                    } catch (IOException ignore) {
                        // don't index files that can't be read.
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        }
    }

    /** Indexes a single document */
    static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
        try (InputStream stream = Files.newInputStream(file)) {
            // make a new, empty document
            Document doc = new Document();

            // Add the path of the file as a field named "path".  Use a
            // field that is indexed (i.e. searchable), but don't tokenize
            // the field into separate words and don't index term frequency
            // or positional information:
            Field pathField = new StringField("path", file.toString(), Field.Store.YES);
            doc.add(pathField);

            // Add the last modified date of the file a field named "modified".
            // Use a LongPoint that is indexed (i.e. efficiently filterable with
            // PointRangeQuery).  This indexes to milli-second resolution, which
            // is often too fine.  You could instead create a number based on
            // year/month/day/hour/minutes/seconds, down the resolution you require.
            // For example the long value 2011021714 would mean
            // February 17, 2011, 2-3 PM.
            Date d = new Date();
            d.setTime(lastModified);
            doc.add(new TextField("modified", DateUtils.dtts(d),Field.Store.YES));

            // Add the contents of the file to a field named "contents".  Specify a Reader,
            // so that the text of the file is tokenized and indexed, but not stored.
            // Note that FileReader expects the file to be in UTF-8 encoding.
            // If that's not the case searching for special characters will fail.
            doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));
            doc.add(new TextField("fileName",file.getFileName().toString(),Field.Store.YES));
            doc.add(new TextField("fileSize",file.toFile().length()+"B",Field.Store.YES));


            if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                // New index, so we just add the document (no old document can be there):
                System.out.println("adding " + file);
                writer.addDocument(doc);
            } else {
                // Existing index (an old copy of this document may have been indexed) so
                // we use updateDocument instead to replace the old one matching the exact
                // path, if present:
                System.out.println("updating " + file);
                writer.updateDocument(new Term("path", file.toString()), doc);
            }
        }
    }
}


