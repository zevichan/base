package com.czw.search.lucene;

import com.czw.util.ComUtils;
import com.czw.util.JdbcUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ZeviChen , 2017/5/23 09:20
 */
public class TestJdbcGoods {

    static String indexPath = "E:\\test\\db-index";
    static String docPath = "E:\\test\\db-index";

    public static void createIndex() throws SQLException, IOException {
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT goodsId,goodsName,goodsDetails,merchantId FROM mc_merchant_goods");
        ResultSet rs = ps.executeQuery();

        Path path = Paths.get(indexPath);
        Directory dir = FSDirectory.open(path);
        Analyzer analyzer = new CJKAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, iwc);

        //数据清除
        writer.deleteAll();
        while (rs.next()) {
            Document doc = new Document();
            Field goodsId = new TextField("goodsId", rs.getString("goodsId"), Field.Store.YES);
            Field goodsName = new TextField("goodsName", rs.getString("goodsName"), Field.Store.YES);
            Field goodsDetails = new TextField("goodsDetails", rs.getObject("goodsDetails") == null ? "" : rs.getString("goodsDetails"), Field.Store.YES);
            Field merchantId = new TextField("merchantId", rs.getString("merchantId"), Field.Store.YES);

            doc.add(goodsId);
            doc.add(goodsName);
            doc.add(goodsDetails);
            doc.add(merchantId);

            writer.addDocument(doc);
        }
        writer.close();
        JdbcUtils.free(rs, ps, conn);

    }

    public static void doSearch() throws IOException, ParseException, InvalidTokenOffsetsException {
        Analyzer analyzer = new CJKAnalyzer();
        // 使用QueryParser搜索时，需要指定分词器，搜索时的分词器要和索引时的分词器一致
        // 第一个参数：默认搜索的域的名称
//        QueryParser parser = new QueryParser("goodsDetails", analyzer);

        QueryParser parser = new MultiFieldQueryParser(new String[]{"goodsName", "goodsDetails"}, analyzer);

        // 通过queryparser来创建query对象
        // 参数：输入的lucene的查询语句(关键字一定要大写)
        Query query = parser.parse("防护乳");

        Path path = Paths.get(docPath);
        Directory directory = FSDirectory.open(path);
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        TopDocs topDocs = searcher.search(query, 10);

        System.out.println("查找的记录：" + topDocs.totalHits);

        //高亮
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer source = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, source);

        // 摘要
        Fragmenter fragmenter = new SimpleFragmenter(5);
        highlighter.setTextFragmenter(fragmenter);

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docId = scoreDoc.doc;


            Document doc = searcher.doc(docId);
            String text = highlighter.getBestFragment(analyzer, "goodsName", doc.get("goodsName"));
            if (text == null) {
                text = highlighter.getBestFragment(analyzer, "goodsDetails", doc.get("goodsDetails"));
            }
            System.out.println("高亮显示搜索结果:" + text);

            System.out.println("商品id：" + doc.get("goodsId"));
            System.out.println("商品名称：" + doc.get("goodsName"));
            System.out.println("商品描述：" + doc.get("goodsDetails"));
            System.out.println("商家id：" + doc.get("merchantId"));
            System.out.println("hit docs:" + scoreDoc.toString());
            System.out.println("-------------------------------------");
        }
    }

    public static void main(String[] args) throws IOException, SQLException, ParseException, InvalidTokenOffsetsException {
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "INFO");//log4j set trace log.
        ComUtils.start();
        createIndex();
//        doSearch();
        ComUtils.end();
    }


}
