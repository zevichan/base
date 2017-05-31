package com.czw.search.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * @author ZeviChen , 2017/5/31 10:01
 */
public class TestQuery {

    String field = "contents";
    String text1 = "init";
    String text2 = "Engine";
    String text3 = "type";
    String text4 = "struct";

    @Test
    public void search() throws IOException {
        Query q1 = booleanQuerySearch();
        Query q2 = queryParserSearch();
        Query q3 = phraseQuerySearch();

        inputQuery(q3);
    }

    //PhrasePrefixQuery     主要用于查找同义词
    //SpanNearQuery         近似搜索
    //TermRangeQuery        ascll码字符比较

    void multiPharseQuery() {
        //new Term(..)
        //new Term(..)
        //Term[] ts = new Term[]{...}
        //multi.add(ts)
    }

    /**
     * blog:http://blog.csdn.net/rick_123/article/details/6708527
     *
     * @return
     */
    Query phraseQuerySearch() {
        PhraseQuery.Builder builder = new PhraseQuery.Builder();
        builder.add(new Term(field, text3), 0);
        builder.add(new Term(field, text4), 2);
        return builder.build();
    }


    Query booleanQuerySearch() {
        BooleanQuery.Builder booleanBuilder = new BooleanQuery.Builder()
                .add(new TermQuery(new Term(field, text1)), BooleanClause.Occur.SHOULD)
                .add(new TermQuery(new Term(field, text2)), BooleanClause.Occur.SHOULD);
        return booleanBuilder.build();
    }

    Query queryParserSearch() {
        QueryParser queryParser = new QueryParser(field, new StandardAnalyzer());
        Query query = null;
        try {
            query = queryParser.parse(text3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return query;
    }

    void inputQuery(Query query) throws IOException {

        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(TestIndexFiles.indexPath)));
        IndexSearcher searcher = new IndexSearcher(reader);

        TopDocs topDocs = searcher.search(query, 10);

        ScoreDoc[] hits = topDocs.scoreDocs;
        int total = topDocs.totalHits;
        System.out.println("totalHits:" + total);
        System.out.println("--------------------------------");
        for (int i = 0; i < total; i++) {
            Document document = searcher.doc(hits[i].doc);
            Iterator<IndexableField> it = document.iterator();
            while (it.hasNext()) {
                IndexableField index = it.next();
                System.out.println(index.name() + " : " + index.stringValue());
            }
            System.out.println("--------------------------------");

        }

        reader.close();
    }


}
